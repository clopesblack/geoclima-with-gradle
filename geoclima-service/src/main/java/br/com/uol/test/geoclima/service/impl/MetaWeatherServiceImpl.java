package br.com.uol.test.geoclima.service.impl;

import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.meta.weather.client.MetaWeatherClient;
import br.com.uol.test.geoclima.meta.weather.client.dto.LocationDTO;
import br.com.uol.test.geoclima.meta.weather.client.dto.TempLocationDTO;
import br.com.uol.test.geoclima.service.MetaWeatherService;
import br.com.uol.test.geoclima.service.dto.WeatherDTO;
import br.com.uol.test.geoclima.service.exceptions.ErrorOnTrySearchWeatherException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Slf4j
@Service
@AllArgsConstructor
public class MetaWeatherServiceImpl implements MetaWeatherService {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final MetaWeatherClient metaWeatherClient;

    @Override
    public WeatherDTO findWeather(GeographicalInformationDTO geographicalInfo) {
        final WeatherDTO weather = getWeather(getLocation(geographicalInfo));
        weather.setCity(geographicalInfo.getData().getCity_name());
        return weather;
    }

    private List<LocationDTO> getLocation(GeographicalInformationDTO geographicalInfo) {
        try {
            List<LocationDTO> locations = metaWeatherClient.searchLocation(geographicalInfo.getData().getCity_name());
            validateLocations(locations);
            return locations;
        } catch (RuntimeException e) {
            log.warn("c='MetaWeatherServiceImpl', m='getLocation', geographicalInfo={}, msg='Erro ao consultar dados meteorologicos', exception={}", geographicalInfo, e);
            throw new ErrorOnTrySearchWeatherException();
        }
    }

    private WeatherDTO getWeather(List<LocationDTO> locations) {
        try {
            return getMinAndMaxTemps(getTodayWeather(locations));
        } catch (RuntimeException e) {
            log.warn("c='MetaWeatherServiceImpl', m='getWeather', msg='Erro ao consultar dados meteorologicos', exception={}", e);
            throw new ErrorOnTrySearchWeatherException();
        }
    }

    private void validateLocations(List<LocationDTO> locations) {
        if (CollectionUtils.isEmpty(locations) || StringUtils.isEmpty(locations.get(0).getWoeid())) {
            throw new ErrorOnTrySearchWeatherException();
        }
    }

    private List<TempLocationDTO> getTodayWeather(List<LocationDTO> locations) {
        LocalDate now = LocalDate.now();
        List<TempLocationDTO> temperatures = metaWeatherClient.getTemperatureForLocationDate(locations.get(0).getWoeid(), now);
        if (CollectionUtils.isEmpty(temperatures)) {
            throw new ErrorOnTrySearchWeatherException();
        }

        List<TempLocationDTO> nowTemps = temperatures.stream().filter(temperature -> temperature.getCreated().contains(now.format(DATE_PATTERN))).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(nowTemps)) {
            throw new ErrorOnTrySearchWeatherException();
        }
        return nowTemps;
    }

    private WeatherDTO getMinAndMaxTemps(List<TempLocationDTO> nowTemps) {
        OptionalDouble max = nowTemps.stream().filter(temperature -> !StringUtils.isEmpty(temperature.getMax_temp())).mapToDouble(temperature -> Double.parseDouble(temperature.getMax_temp())).max();
        OptionalDouble min = nowTemps.stream().filter(temperature -> !StringUtils.isEmpty(temperature.getMin_temp())).mapToDouble(temperature -> Double.parseDouble(temperature.getMin_temp())).min();

        WeatherDTO weather = new WeatherDTO();
        weather.setMax(max.orElseThrow(ErrorOnTrySearchWeatherException::new));
        weather.setMin(min.orElseThrow(ErrorOnTrySearchWeatherException::new));
        return weather;
    }
}
