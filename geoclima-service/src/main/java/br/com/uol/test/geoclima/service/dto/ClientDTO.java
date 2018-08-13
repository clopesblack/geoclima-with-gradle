package br.com.uol.test.geoclima.service.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
@Data
@Builder
public class ClientDTO {

    private String name;
    private String age;
}
