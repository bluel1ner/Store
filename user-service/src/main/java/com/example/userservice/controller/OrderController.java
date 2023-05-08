package com.example.userservice.controller;

import com.example.userservice.dto.request.OrderRequest;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 9:47 PM
 */
@Slf4j
@RestController()
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Adding {} dto to order", orderRequest);
        return ResponseEntity.ok()
                .body(orderService.addOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllUserOrder() {
        log.info("Get all user orders");
        return ResponseEntity.ok()
                .body(orderService.getAllUserOrder());
    }

    @GetMapping("/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getAllByOrderStatus(@PathVariable OrderStatus orderStatus) {
        log.info("Get all by {} status", orderStatus);
        return ResponseEntity.ok()
                .body(orderService.getAllByOrderStatus(orderStatus));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        log.info("Get all by");
        return ResponseEntity.ok()
                .body(orderService.getAllOrders());
    }

    @PutMapping("/{id}/{orderStatus}")
    public ResponseEntity<OrderResponse> changeOrderStatus(@PathVariable String id,
                                                           @PathVariable OrderStatus orderStatus) {
        log.info("Change order status with id {}", id);
        return ResponseEntity.ok()
                .body(orderService.changeOrderStatus(id, orderStatus));
    }

}
