package com.example.userservice.repository;

//import org.springframework.stereotype.Repository;

import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.mongo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(Long id);

    List<Order> findAllByStatus(OrderStatus orderStatus);
}
