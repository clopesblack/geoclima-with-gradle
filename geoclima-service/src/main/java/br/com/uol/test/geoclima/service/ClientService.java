package br.com.uol.test.geoclima.service;

import br.com.uol.test.geoclima.service.dto.ClientDTO;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
public interface ClientService {

    ClientDTO save(ClientDTO toClientDTO, String ip);
}
