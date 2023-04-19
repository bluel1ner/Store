package com.example.userservice.dto.request;

import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Configuration;
import com.example.userservice.entity.mongo.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ProductRequest {
    private String name;
    private Product.Type type;
    private Double price;
    private String display;
    private String powerAndBattery;
    private List<Configuration> configurationList;
    private List<Color> colorList;
}
