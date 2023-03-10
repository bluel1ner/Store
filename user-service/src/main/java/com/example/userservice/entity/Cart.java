package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/10/2023 11:58 PM
 */

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //TODO: think about ManyToMany relationshil
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "product_id")
//    private List<Product> productList;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
