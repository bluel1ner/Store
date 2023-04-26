package com.example.userservice.entity;


import lombok.*;
import jakarta.persistence.*;
/**
 * @author Neevels
 * @version 1.0
 * @date 3/3/2023 1:40 AM
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
