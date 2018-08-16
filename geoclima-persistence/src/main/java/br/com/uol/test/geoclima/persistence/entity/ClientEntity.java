package br.com.uol.test.geoclima.persistence.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by Caroline Lopes on 15/08/18.
 */
@Data
public class ClientEntity {

    @Id
    private ObjectId id;
    private String name;
    private String age;
    private WeatherEntity weather;
}
