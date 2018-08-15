package br.com.uol.test.geoclima.meta.weather.client.dto;

import lombok.Data;

/**
 * Created by Caroline Lopes on 14/08/18.
 */
@Data
public class TempLocationDTO {

    private String min_temp;
    private String max_temp;
    private String created;
}
