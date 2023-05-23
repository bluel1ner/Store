package com.example.userservice.entity.mongo;

import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.PaymentType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
@ToString
public class Order {
    @MongoId
    private String id;
    private Long userId;
    private String address;
    private PaymentType paymentOption;
    private LocalDate date;
    private LocalDate dateDone;
    private OrderStatus status;
    private Double finalPrice;
    private List<OrderProduct> products;

}
