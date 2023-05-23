package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.PaymentType;
import com.example.userservice.entity.mongo.OrderProduct;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
    private String address;
    private PaymentType paymentOption;
    private Double finalPrice;
    private List<OrderProduct> products;
}
