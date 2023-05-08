package com.example.userservice.service.impl;

import com.example.userservice.dto.response.AmountStatisticResponse;
import com.example.userservice.dto.response.PriceStatisticResponse;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.ProductType;
import com.example.userservice.entity.mongo.Order;
import com.example.userservice.repository.OrderRepository;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.StatisticService;
import com.example.userservice.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/8/2023 9:11 PM
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    private final OrderRepository orderRepository;
    private final UserUtils userUtils;
    private final ProductRepository productRepository;

    public StatisticServiceImpl(OrderRepository orderRepository, UserUtils userUtils, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userUtils = userUtils;
        this.productRepository = productRepository;
    }

    @Override
    public List<AmountStatisticResponse> getProfitByProductType() {
        List<Order> allByStatus = orderRepository.findAllByStatus(OrderStatus.COMPLETED);
        return List.of(ProductType.values())
                .stream()
                .collect(Collectors.toMap(
                        value -> value,
                        value -> (int) allByStatus.stream()
                                .map(order -> {
                                    return order.getProducts()
                                            .stream()
                                            .filter(orderProduct -> Objects.equals(productRepository.findById(orderProduct.getProduct()
                                                                    .getId())
                                                            .get()
                                                            .getType(),
                                                    value))
//                                            .reduce((orderProduct, res) -> res + orderProduct.getAmount(), 0)
                                            .count();
                                })
                                .count()
                ))
                .entrySet()
                .stream()
                .map(map -> AmountStatisticResponse.builder()
                        .key(map.getKey())
                        .value(map.getValue())
                        .build())
                .toList();
    }

    @Override
    public List<PriceStatisticResponse> getPriceStatistic() {
        List<Order> allByStatus = orderRepository.findAllByStatus(OrderStatus.COMPLETED);
        return List.of(ProductType.values())
                .stream()
                .collect(Collectors.toMap(
                        value -> value,
                        value -> allByStatus.stream()
                                .filter(order -> Objects.equals(order.getStatus(), value))
                                .mapToDouble(Order::getFinalPrice)
                                .sum()
                ))
                .entrySet()
                .stream()
                .map(map -> PriceStatisticResponse.builder()
                        .key(map.getKey())
                        .value(map.getValue())
                        .build())
                .toList();
    }
}
