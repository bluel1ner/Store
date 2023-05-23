package com.example.userservice.entity;

import com.example.userservice.entity.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private String brand;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column(length = 1000)
    private String description;
    private String color;
    private String originCountry;
    private Integer amount;
    private Float averageRate;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Cart cart;
}
