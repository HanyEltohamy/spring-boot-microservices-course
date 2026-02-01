package com.bits.bookstore.order.domain;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forCode(String code) {
        return new OrderNotFoundException("Order with code " + code + " not found");
    }
}
