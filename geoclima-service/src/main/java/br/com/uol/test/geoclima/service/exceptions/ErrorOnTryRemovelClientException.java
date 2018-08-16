package br.com.uol.test.geoclima.service.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Caroline Lopes on 16/08/18.
 */
@ResponseStatus(NOT_FOUND)
public class ErrorOnTryRemovelClientException extends GenericErrorException {

    public ErrorOnTryRemovelClientException() {
        super("00005", "Ocorreu um erro ao tentar remover o cliente.");
    }
}
