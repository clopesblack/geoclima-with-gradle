package br.com.uol.test.geoclima.persistence.entity;

import lombok.Data;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Data
public class WeatherEntity {

    private String city;
    private String min;
    private String max;
}