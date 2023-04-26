package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.entity.mongo.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:07 AM
 */
@NoArgsConstructor
@Component
public class ProductMapper {
    public ProductResponse toResponseDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .colors(product.getColors())
                .powerAndBattery(product.getPowerAndBattery())
                .price(product.getPrice())
                .type(product.getType())
                .name(product.getName())
                .configurations(product.getConfigurations())
                .video(product.getVideo())
                .build();
    }

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.getId())
                .colors(productRequest.getColors())
                .configurations(productRequest.getConfigurations())
                .display(productRequest.getDisplay())
                .name(productRequest.getName())
                .powerAndBattery(productRequest.getPowerAndBattery())
                .price(productRequest.getPrice())
                .type(productRequest.getType())
                .video(productRequest.getVideo())
                .build();
    }
}
