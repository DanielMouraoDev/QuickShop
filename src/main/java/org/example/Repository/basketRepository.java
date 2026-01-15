package org.example.Repository;

import org.example.Entity.basket;
import org.example.Entity.status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface basketRepository extends MongoRepository<basket, String> {

    Optional<basket> findByClientAndStatus (Long client, status status);
}
