package com.example.userservice.dto.request;

import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Configuration;
import com.example.userservice.entity.mongo.Product;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 10:21 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductRequest {
    private String id;
    private String name;
    private Product.Type type;
    private Integer price;
    private String display;
    private String powerAndBattery;
    private List<Configuration> configurations;
    private List<Color> colors;
}
