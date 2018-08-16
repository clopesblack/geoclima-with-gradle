package br.com.uol.test.geoclima.service;

import br.com.uol.test.geoclima.ip.vigilante.client.dto.GeographicalInformationDTO;
import br.com.uol.test.geoclima.service.dto.WeatherDTO;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
public interface MetaWeatherService {

    WeatherDTO findWeather(GeographicalInformationDTO geographicalInfo);
}
