package br.com.uol.test.geoclima.api.util;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Data
@Builder
public class ApiError {

    private String code;
    private String msg;
}
