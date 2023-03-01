package com.example.userservice.repository;


import com.example.userservice.entity.Address;
import com.example.userservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
