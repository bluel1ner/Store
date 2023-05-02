package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.PaymentType;
import com.example.userservice.entity.mongo.OrderProduct;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 9:45 PM
 */
@Builder
public record OrderResponse (
         String id,
         String address,
         PaymentType paymentOption,
         LocalDate date,
         LocalDate dateDone,
         OrderStatus status,
         Double finalPrice,
         List<OrderProduct>products
) {
}
