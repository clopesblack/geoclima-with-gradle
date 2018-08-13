package br.com.uol.test.geoclima.api.vo.mapper;

import br.com.uol.test.geoclima.api.vo.ClientVO;
import br.com.uol.test.geoclima.service.dto.ClientDTO;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
public class ClientVOMapper {

    public static ClientDTO toClientDTO(ClientVO clientVO) {
        return ClientDTO.builder()
                .name(clientVO.getName())
                .age(clientVO.getAge())
                .build();
    }
}
