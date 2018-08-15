package br.com.uol.test.geoclima.ip.vigilante.client.dto;

import lombok.Data;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Data
public class DataInformation {

    private String ipv4;
    private String continent_name;
    private String country_name;
    private String subdivision_1_name;
    private String subdivision_2_name;
    private String city_name;
    private String latitude;
    private String longitude;
}
