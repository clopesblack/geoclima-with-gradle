package br.com.uol.test.geoclima.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Data
@NoArgsConstructor
public class ClientDTO {

    private String id;
    private String name
            ;
    private String age;
    private WeatherDTO weather;
}
