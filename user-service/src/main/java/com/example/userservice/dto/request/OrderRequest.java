package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.PaymentType;
import com.example.userservice.entity.mongo.OrderProduct;
import lombok.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 9:45 PM
 */
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
