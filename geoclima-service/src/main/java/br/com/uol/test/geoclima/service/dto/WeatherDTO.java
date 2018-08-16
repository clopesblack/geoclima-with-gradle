package br.com.uol.test.geoclima.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Data
@NoArgsConstructor
public class WeatherDTO {

    private String city;
    private Double min;
    private Double max;
}
