package com.example.userservice.entity.mongo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 10:10 AM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
public class Product {
    @MongoId
    private String id;

    private String name;
    private Type type;
    private Double price;
    private String display;
    private String powerAndBattery;
    private List<Configuration> configurations;
    private List<Color> colors;

    public enum Type {
        iPhone,
        Mac,
        iPad,
        Watch,
        AirPods
    }


}
