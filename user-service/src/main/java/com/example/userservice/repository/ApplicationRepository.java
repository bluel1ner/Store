package com.example.userservice.repository;

import com.example.userservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/4/2023 2:21 PM
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
