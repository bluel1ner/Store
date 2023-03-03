package com.example.userservice.entity;

import com.example.userservice.entity.enums.ProductType;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/3/2023 1:35 AM
 */
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
}
