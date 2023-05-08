package com.example.userservice.service.impl;

import com.example.userservice.dto.response.ProfitByProductTypeRequest;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.ProductType;
import com.example.userservice.entity.mongo.Order;
import com.example.userservice.repository.OrderRepository;
import com.example.userservice.service.StatisticService;
import com.example.userservice.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/8/2023 9:11 PM
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    private final OrderRepository orderRepository;
    private final UserUtils userUtils;

    public StatisticServiceImpl(OrderRepository orderRepository, UserUtils userUtils) {
        this.orderRepository = orderRepository;
        this.userUtils = userUtils;
    }

    @Override
    public List<ProfitByProductTypeRequest> getProfitByProductType() {
        List<Order> allByStatus = orderRepository.findAllByStatus(OrderStatus.COMPLETED);
        return List.of(ProductType.values()).stream()
                .collect(Collectors.toMap(
                        value -> value,
                        value -> (int) allByStatus.stream()
                                .filter(order -> Objects.equals(order.getStatus(), value))
                                .count()
                ))
                .entrySet()
                .stream()
                .map(map -> ProfitByProductTypeRequest.builder()
                        .key(map.getKey())
                        .value(map.getValue())
                        .build())
                .toList();
    }
}
