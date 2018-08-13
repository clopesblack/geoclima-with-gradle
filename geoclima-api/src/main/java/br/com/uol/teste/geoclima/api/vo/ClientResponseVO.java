package br.com.uol.teste.geoclima.api.vo;


import lombok.Data;

/**
 * Created by Caroline Lopes on 10/08/18.
 */
@Data
public class ClientResponseVO {

    private ClientVO client;
    private WeatherVO weather;
}
