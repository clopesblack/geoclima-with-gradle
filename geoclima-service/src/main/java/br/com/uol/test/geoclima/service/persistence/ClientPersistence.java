package br.com.uol.test.geoclima.service.persistence;

import br.com.uol.test.geoclima.service.dto.ClientDTO;

import java.util.List;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
public interface ClientPersistence {

    ClientDTO save(ClientDTO client);
    ClientDTO update(ClientDTO client);
    ClientDTO get(String id);
    List<ClientDTO> list();
}
