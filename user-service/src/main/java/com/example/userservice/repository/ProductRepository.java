package com.example.userservice.repository;

import com.example.userservice.entity.mongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 9:19 PM
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByName(String name);
}
