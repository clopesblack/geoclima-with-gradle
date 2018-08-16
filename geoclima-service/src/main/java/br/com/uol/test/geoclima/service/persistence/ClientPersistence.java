package br.com.uol.test.geoclima.service.persistence;

import br.com.uol.test.geoclima.service.dto.ClientDTO;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
public interface ClientPersistence {

    ClientDTO save(ClientDTO client);
    ClientDTO update(ClientDTO client);
}
