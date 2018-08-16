package br.com.uol.test.geoclima.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Caroline Lopes on 09/08/18.
 */
@Data
public class ClientVO {

    @NotNull(message = "O campo name é obrigatorio.")
    @Size(min=4, max=30, message = "O campo name deve conter entre 4 a 30 caracteres.")
    private String name;

    @NotNull(message = "O campo age é obrigatorio.")
    @Size(min=1, max=3, message = "O campo name deve conter entre 1 a 3 caracteres.")
    private String age;
}
