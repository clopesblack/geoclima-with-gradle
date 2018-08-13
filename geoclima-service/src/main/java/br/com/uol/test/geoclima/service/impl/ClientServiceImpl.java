package br.com.uol.test.geoclima.service.impl;

import br.com.uol.test.geoclima.ip.vigilante.client.IpVigilanteClient;
import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.service.ClientService;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final IpVigilanteClient ipVigilanteClient;

    @Override
    public void save(ClientDTO toClientDTO, String ip) {

        GeographicalInformationDTO geographicalInfo = ipVigilanteClient.getGeographicalInfo(ip);

    }
}
