package com.example.productservice.entity;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:29 PM
 */
public class Configuration {
    private String name;
    private ProductConfiguration productConfiguration;
    private Double extraPrice;


    enum ProductConfiguration {
        STORAGE,
        MEMORY,
        PROCESSOR
    }
}
