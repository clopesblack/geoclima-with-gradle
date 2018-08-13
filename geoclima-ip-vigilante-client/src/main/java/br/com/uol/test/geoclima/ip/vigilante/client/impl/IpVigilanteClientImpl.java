package br.com.uol.test.geoclima.ip.vigilante.client.impl;

import br.com.uol.test.geoclima.ip.vigilante.client.IpVigilanteClient;
import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.ip.vigilante.client.exceptions.IpVigilanteIntegrationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Component
@AllArgsConstructor
public class IpVigilanteClientImpl implements IpVigilanteClient {

    private static final String GET_INFO_URL = "https://ipvigilante.com/{ip}";

    private final RestTemplate restTemplate;

    @Override
    public GeographicalInformationDTO getGeographicalInfo(String ip) {

        String uri = fromUriString(GET_INFO_URL).buildAndExpand(ip).toString();

        ResponseEntity<GeographicalInformationDTO> response = restTemplate.getForEntity(uri, GeographicalInformationDTO.class);

        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new IpVigilanteIntegrationException();
        }
    }
}
