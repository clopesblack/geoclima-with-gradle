package br.com.uol.test.geoclima.api.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Caroline Lopes on 09/08/18.
 */
@Data
public class ClientRequestVO {

    @Valid
    @NotNull
    private ClientVO client;
}
