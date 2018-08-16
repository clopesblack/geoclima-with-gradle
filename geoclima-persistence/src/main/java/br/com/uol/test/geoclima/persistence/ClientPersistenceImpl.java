package br.com.uol.test.geoclima.persistence;

import br.com.uol.test.geoclima.persistence.entity.ClientEntity;
import br.com.uol.test.geoclima.persistence.repository.ClientRepository;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import br.com.uol.test.geoclima.service.exceptions.ClientNotFoundException;
import br.com.uol.test.geoclima.service.exceptions.ErrorOnTryListAllClientsException;
import br.com.uol.test.geoclima.service.exceptions.ErrorOnTryRemovelClientException;
import br.com.uol.test.geoclima.service.exceptions.ErrorOnTrySaveClientException;
import br.com.uol.test.geoclima.service.persistence.ClientPersistence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Slf4j
@Repository
@AllArgsConstructor
public class ClientPersistenceImpl implements ClientPersistence {

    private ModelMapper mapper;
    private ClientRepository repository;

    @Override
    public ClientDTO save(ClientDTO client) {
        try {
            return mapper.map(repository.save(mapper.map(client, ClientEntity.class)), ClientDTO.class);
        } catch (RuntimeException e) {
            log.warn("c='ClientPersistenceImpl', m='save', client={}, msg='Erro ao salvar o cliente.', exception={}", client, e);
            throw new ErrorOnTrySaveClientException();
        }
    }

    @Override
    public ClientDTO update(ClientDTO client) {

        ClientEntity founded = repository.findById(client.getId()).orElseThrow(ClientNotFoundException::new);

        if (!StringUtils.isEmpty(client.getName())) {
            founded.setName(client.getName());
        }

        if (!StringUtils.isEmpty(client.getAge())) {
            founded.setAge(client.getAge());
        }

        try {
            return mapper.map(repository.save(founded), ClientDTO.class);
        } catch (RuntimeException e) {
            log.warn("c='ClientPersistenceImpl', m='update', client={}, msg='Erro ao atualizar o cliente.', exception={}", client, e);
            throw new ErrorOnTrySaveClientException();
        }
    }

    @Override
    public ClientDTO get(String id) {
        ClientEntity founded = repository.findById(id).orElseThrow(ClientNotFoundException::new);
        return mapper.map(founded, ClientDTO.class);
    }

    @Override
    public List<ClientDTO> list() {
        try {
            return mapper.map(repository.findAll(), new TypeToken<List<ClientDTO>>() {
            }.getType());
        } catch (RuntimeException e) {
            log.warn("c='ClientPersistenceImpl', m='list', msg='Erro ao listar clientes.', exception={}", e);
            throw new ErrorOnTryListAllClientsException();
        }
    }

    @Override
    public void delete(String id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            log.warn("c='ClientPersistenceImpl', m='list', msg='Erro ao remover cliente.', exception={}", e);
            throw new ErrorOnTryRemovelClientException();
        }
    }
}
