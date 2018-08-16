package br.com.uol.test.geoclima.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ErrorOnTrySearchWeather extends GenericErrorException {

    public ErrorOnTrySearchWeather() {
        super("00002", "Erro ao consultar dados meteorologicos");
    }
}
