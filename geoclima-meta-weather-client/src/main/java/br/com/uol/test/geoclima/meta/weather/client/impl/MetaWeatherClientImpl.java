package br.com.uol.test.geoclima.meta.weather.client.impl;

import br.com.uol.test.geoclima.meta.weather.client.dto.LocationDTO;
import br.com.uol.test.geoclima.meta.weather.client.dto.TempLocationDTO;
import br.com.uol.test.geoclima.meta.weather.client.exceptions.WeatherLocationIntegrationException;
import br.com.uol.test.geoclima.meta.weather.client.exceptions.WeatherTempLocationIntegrationException;
import br.com.uol.test.geoclima.meta.weather.client.MetaWeatherClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Component
@AllArgsConstructor
public class MetaWeatherClientImpl implements MetaWeatherClient {

    private static final String METAWEATHER_URL = "https://www.metaweather.com/api";
    private static final String LOCATION_SEARCH_PATH = METAWEATHER_URL + "/location/search";
    private static final String CITY_TEMPERATURE_PATH = METAWEATHER_URL + "/location/{city}/{date}";

    private final RestTemplate restTemplate;

    @Override
    public List<LocationDTO> searchLocation(String city) {

        String uri = fromUriString(LOCATION_SEARCH_PATH).queryParam("query", city).build().toString();

        ResponseEntity<LocationDTO[]> response = restTemplate.getForEntity(uri, LocationDTO[].class);

        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            return Arrays.asList(response.getBody());
        } else {
            throw new WeatherLocationIntegrationException();
        }

    }

    @Override
    public List<TempLocationDTO> getTemperatureForLocationDate(String idLocation, LocalDate date) {
        String uri = fromUriString(CITY_TEMPERATURE_PATH).buildAndExpand(idLocation, date.toString().replaceAll("-","/")).toString();

        ResponseEntity<TempLocationDTO[]> response = restTemplate.getForEntity(uri, TempLocationDTO[].class);

        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            return Arrays.asList(response.getBody());
        } else {
            throw new WeatherTempLocationIntegrationException();
        }
    }
}
