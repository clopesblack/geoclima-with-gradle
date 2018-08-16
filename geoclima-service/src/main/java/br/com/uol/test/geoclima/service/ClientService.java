package br.com.uol.test.geoclima.service;

import br.com.uol.test.geoclima.service.dto.ClientDTO;

import java.util.List;

/**
 * Created by Caroline Lopes on 13/08/18.
 */
public interface ClientService {

    ClientDTO save(ClientDTO client, String ip);

    ClientDTO update(ClientDTO client);

    ClientDTO getClient(String id);

    List<ClientDTO> list();

    void remove(String id);
}
