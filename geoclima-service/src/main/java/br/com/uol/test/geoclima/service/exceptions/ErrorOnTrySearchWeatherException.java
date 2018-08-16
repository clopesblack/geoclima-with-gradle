package br.com.uol.test.geoclima.service.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@ResponseStatus(value = NOT_FOUND)
public class ErrorOnTrySearchWeatherException extends GenericErrorException {

    public ErrorOnTrySearchWeatherException() {
        super("00002", "Erro ao consultar dados meteorologicos");
    }
}
