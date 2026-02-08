package com.bits.bookstore.order.web.controller;

import com.bits.bookstore.order.domain.OrderNotFoundException;
import com.bits.bookstore.order.domain.OrderService;
import com.bits.bookstore.order.domain.SecurityService;
import com.bits.bookstore.order.domain.model.CreateOrderRequest;
import com.bits.bookstore.order.domain.model.CreateOrderResponse;
import com.bits.bookstore.order.domain.model.OrderDTO;
import com.bits.bookstore.order.domain.model.OrderSummary;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@SecurityRequirement(name = "security_auth")
class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        log.info("Creating order");
        String userName = securityService.getLoginUserName();
        log.info("Creating order for user: {}", userName);
        return orderService.createOrder(userName, request);
    }

    @GetMapping
    List<OrderSummary> getOrders() {
        String userName = securityService.getLoginUserName();
        log.info("Fetching orders for user: {}", userName);
        return orderService.findOrders(userName);
    }

    @GetMapping(value = "/{orderNumber}")
    OrderDTO getOrder(@PathVariable String orderNumber) {

        log.info("Get order by id: {}", orderNumber);
        String userName = securityService.getLoginUserName();
        return orderService
                .findByUserNameAndOrderNum(userName, orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }
}
