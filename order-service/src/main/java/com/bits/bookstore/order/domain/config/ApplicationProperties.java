package com.bits.bookstore.order.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orders")
public record ApplicationProperties(
        String catalogServiceUrl,
        String exchange,
        String newQueue,
        String deliveredQueue,
        String cancelledQueue,
        String errorQueue) {}
