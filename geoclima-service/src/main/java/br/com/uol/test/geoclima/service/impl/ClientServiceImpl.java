package br.com.uol.test.geoclima.service.impl;

import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.service.ClientService;
import br.com.uol.test.geoclima.service.IpVigilanteService;
import br.com.uol.test.geoclima.service.MetaWeatherService;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import br.com.uol.test.geoclima.service.persistence.ClientPersistence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final IpVigilanteService ipVigilanteService;
    private final MetaWeatherService metaWeatherService;
    private final ClientPersistence persistence;

    @Override
    public ClientDTO save(ClientDTO client, String ip) {

        GeographicalInformationDTO geographicalInfo = ipVigilanteService.findGeographicalInformation(ip);
        client.setWeather(metaWeatherService.findWeather(geographicalInfo));
        return persistence.save(client);
    }
}
