package br.com.uol.test.geoclima.service;

import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
public interface IpVigilanteService {

    GeographicalInformationDTO findGeographicalInformation(String ip);
}
