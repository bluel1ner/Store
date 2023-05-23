package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.PaymentType;
import com.example.userservice.entity.mongo.OrderProduct;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OrderResponse(
        String id,
        String address,
        PaymentType paymentOption,
        LocalDate date,
        LocalDate dateDone,
        OrderStatus status,
        Double finalPrice,
        List<OrderProduct> products,
        String fullName,
        String phoneNumber,
        String email
        ) {
}
