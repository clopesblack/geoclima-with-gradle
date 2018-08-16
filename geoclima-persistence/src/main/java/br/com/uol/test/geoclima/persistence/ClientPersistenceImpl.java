package br.com.uol.test.geoclima.persistence;

import br.com.uol.test.geoclima.persistence.entity.ClientEntity;
import br.com.uol.test.geoclima.persistence.repository.ClientRepository;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import br.com.uol.test.geoclima.service.persistence.ClientPersistence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Repository
@AllArgsConstructor
public class ClientPersistenceImpl implements ClientPersistence {

    private ModelMapper mapper;
    private ClientRepository repository;

    @Override
    public ClientDTO save(ClientDTO client) {
        ClientEntity saved = repository.save(mapper.map(client, ClientEntity.class));
        return mapper.map(saved, ClientDTO.class);
    }
}
