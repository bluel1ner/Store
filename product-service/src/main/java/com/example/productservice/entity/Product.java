package com.example.productservice.entity;

import java.nio.DoubleBuffer;
import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:12 PM
 */

public class Product {
    private Integer id;
    private String name;
    private Type type;
    private Double price;
    //    private String display;
    private String powerAndBattery;
    private List<Configuration> configurationList;
    private List<Color> colorList;

    enum Type {
        iPhone,
        Mac,
        iPad,
        Watch,
        AirPods
    }


}
