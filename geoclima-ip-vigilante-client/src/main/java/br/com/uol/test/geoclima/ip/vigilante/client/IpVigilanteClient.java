package br.com.uol.test.geoclima.ip.vigilante.client;

import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
public interface IpVigilanteClient {

    GeographicalInformationDTO getGeographicalInfo(String ip);
}
