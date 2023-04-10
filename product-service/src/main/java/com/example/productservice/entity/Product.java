package com.example.productservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:12 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
public class Product {
    @Transient
    public static final String SEQUENCE_PRODUCT = "products_sequence";
    @Id
    private Long id;
    private String name;
    private Type type;
    private Double price;
    private String display;
    private String powerAndBattery;
    private List<Configuration> configurationList;
    private List<Color> colorList;

    public enum Type {
        iPhone,
        Mac,
        iPad,
        Watch,
        AirPods
    }


}
