package com.example.userservice.service;

import com.example.userservice.dto.request.OrderRequest;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.entity.enums.OrderStatus;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 9:44 PM
 */
public interface OrderService {
    OrderResponse addOrder(OrderRequest orderRequest);
    OrderResponse changeOrderStatus(String id, OrderStatus orderStatus);
    List<OrderResponse> getAllUserOrder();
    List<OrderResponse> getAllByOrderStatus(OrderStatus orderStatus);

    List<OrderResponse> getAllOrders();
}
