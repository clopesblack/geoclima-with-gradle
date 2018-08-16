package br.com.uol.test.geoclima.service.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Caroline Lopes on 16/08/18.
 */
@ResponseStatus(NOT_FOUND)
public class ClientNotFoundException extends GenericErrorException {

    public ClientNotFoundException() {
        super("00003", "Cliente nao encontrado.");
    }
}
