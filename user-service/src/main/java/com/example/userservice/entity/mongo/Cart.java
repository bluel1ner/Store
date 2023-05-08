package com.example.userservice.entity.mongo;

import com.example.userservice.entity.enums.ProductType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 7:26 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
@ToString
public class Cart {
    @MongoId
    private String id;
    private Long userId;
    private String name;
    private String preview;
    private Double price;
    private String color;
    private ProductType type;
    private List<Configuration> configurations;
}
