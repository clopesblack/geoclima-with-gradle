package br.com.uol.test.geoclima.service.impl;

import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.service.ClientService;
import br.com.uol.test.geoclima.service.IpVigilanteService;
import br.com.uol.test.geoclima.service.MetaWeatherService;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import br.com.uol.test.geoclima.service.persistence.ClientPersistence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Slf4j
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

    @Override
    public ClientDTO update(ClientDTO client) {
        return persistence.update(client);
    }

    @Override
    public ClientDTO getClient(String id) {
        return persistence.get(id);
    }

    @Override
    public List<ClientDTO> list() {
        return persistence.list();
    }

    @Override
    public void remove(String id) {
        persistence.delete(id);
    }
}
