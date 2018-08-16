package br.com.uol.test.geoclima.persistence.repository;

import br.com.uol.test.geoclima.persistence.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Repository
public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}
