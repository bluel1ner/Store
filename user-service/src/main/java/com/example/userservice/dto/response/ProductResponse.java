package com.example.userservice.dto.response;

import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Configuration;
import com.example.userservice.entity.mongo.Product;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        String id,
        String name,
        Product.Type type,
        Double price,
        String display,
        String powerAndBattery,
        List<Configuration> configurationList,
        List<Color> colorList
) {
}
