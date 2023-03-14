package com.example.userservice.repository;


import com.example.userservice.entity.Address;
import com.example.userservice.entity.Card;
import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findAllByUserId(User userById);
}
