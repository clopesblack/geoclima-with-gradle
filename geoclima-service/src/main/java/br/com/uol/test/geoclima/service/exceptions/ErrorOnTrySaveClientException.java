package br.com.uol.test.geoclima.service.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@ResponseStatus(value = NOT_FOUND)
public class ErrorOnTrySaveClientException extends GenericErrorException {

    public ErrorOnTrySaveClientException() {
        super("00001", "Erro ao tentar buscar a localizaçao pelo IP");
    }
}
