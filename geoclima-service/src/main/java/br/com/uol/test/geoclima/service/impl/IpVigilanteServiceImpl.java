package br.com.uol.test.geoclima.service.impl;

import br.com.uol.test.geoclima.ip.vigilante.client.IpVigilanteClient;
import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.service.IpVigilanteService;
import br.com.uol.test.geoclima.service.exceptions.ErrorOnTrySearchGeographicalInfoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Slf4j
@Service
@AllArgsConstructor
public class IpVigilanteServiceImpl implements IpVigilanteService {

    private final IpVigilanteClient ipVigilanteClient;

    @Override
    public GeographicalInformationDTO findGeographicalInformation(String ip) {
        try {
            GeographicalInformationDTO geographicalInfo = ipVigilanteClient.getGeographicalInfo(ip);
            geographicalInformationValidate(geographicalInfo);
            return geographicalInfo;
        } catch (RuntimeException e) {
            log.warn("c='IpVigilanteServiceImpl', m='getGeographicalInformation', ip={}, msg='Erro ao consultar localizaçao pelo IP', exception={}", ip, e);
            throw new ErrorOnTrySearchGeographicalInfoException();
        }
    }

    private void geographicalInformationValidate(GeographicalInformationDTO geographicalInfo) {
        if (geographicalInfo == null || geographicalInfo.getData() == null || StringUtils.isEmpty(geographicalInfo.getData().getCity_name())) {
            throw new ErrorOnTrySearchGeographicalInfoException();
        }
    }
}
