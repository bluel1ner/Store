package com.example.userservice.entity;

import com.example.userservice.entity.enums.Role;
import com.example.userservice.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/4/2023 2:17 PM
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @Column(length = 1024)
    private String message;
    private Role role;
}
