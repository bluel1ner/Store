package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.ProductType;
import com.example.userservice.entity.mongo.Configuration;
import lombok.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 8:23 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartRequest {
    private String id;
    private String name;
    private String preview;
    private ProductType type;
    private Double price;
    private String color;
    private List<Configuration> configurations;
}
