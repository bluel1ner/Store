package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/7/2023 12:38 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findById(int id);

    Optional<User> findByEmail(String email);
    


}
