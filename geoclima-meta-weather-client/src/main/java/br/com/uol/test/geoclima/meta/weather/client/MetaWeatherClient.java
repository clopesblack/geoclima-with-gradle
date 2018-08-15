package br.com.uol.test.geoclima.meta.weather.client;

import br.com.uol.test.geoclima.meta.weather.client.dto.LocationDTO;
import br.com.uol.test.geoclima.meta.weather.client.dto.TempLocationDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
public interface MetaWeatherClient {

    List<LocationDTO> searchLocation(String city);
    List<TempLocationDTO> getTemperatureForLocationDate(String idLocation, LocalDate day);
}
