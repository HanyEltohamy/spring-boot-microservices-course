package com.bits.bookstore.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "notifications")
public record ApplicationProperties(
        String supportEmail,
        String catalogServiceUrl,
        String exchange,
        String newQueue,
        String deliveredQueue,
        String cancelledQueue,
        String errorQueue) {}
