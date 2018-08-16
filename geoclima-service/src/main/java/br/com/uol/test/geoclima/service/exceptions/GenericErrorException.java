package br.com.uol.test.geoclima.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Data
@AllArgsConstructor
public class GenericErrorException extends RuntimeException {

    private String code;
    private String msg;
}
