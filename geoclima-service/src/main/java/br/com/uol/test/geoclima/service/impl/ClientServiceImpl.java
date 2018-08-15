package br.com.uol.test.geoclima.service.impl;

import br.com.uol.test.geoclima.ip.vigilante.client.IpVigilanteClient;
import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.meta.weather.client.MetaWeatherClient;
import br.com.uol.test.geoclima.meta.weather.client.dto.LocationDTO;
import br.com.uol.test.geoclima.meta.weather.client.dto.TempLocationDTO;
import br.com.uol.test.geoclima.service.ClientService;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final IpVigilanteClient ipVigilanteClient;
    private final MetaWeatherClient metaWeatherClient;

    @Override
    public void save(ClientDTO toClientDTO, String ip) {

        GeographicalInformationDTO geographicalInfo = ipVigilanteClient.getGeographicalInfo(ip);
        List<LocationDTO> locations = metaWeatherClient.searchLocation(geographicalInfo.getData().getCity_name());
        LocalDate now = LocalDate.now();
        List<TempLocationDTO> temperatures = metaWeatherClient.getTemperatureForLocationDate(locations.get(0).getWoeid(), now);

        List<TempLocationDTO> nowTemps = temperatures.stream().filter(temperature -> temperature.getCreated().contains(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).collect(Collectors.toList());

        OptionalDouble max = nowTemps.stream().mapToDouble(temperature -> Double.parseDouble(temperature.getMax_temp())).max();
        OptionalDouble min = nowTemps.stream().mapToDouble(temperature -> Double.parseDouble(temperature.getMin_temp())).min();

        System.out.println(geographicalInfo);

    }
}
