package com.bits.bookstore.order.domain;

import com.bits.bookstore.order.domain.config.ApplicationProperties;
import com.bits.bookstore.order.domain.model.OrderCancelledEvent;
import com.bits.bookstore.order.domain.model.OrderCreatedEvent;
import com.bits.bookstore.order.domain.model.OrderDeliveredEvent;
import com.bits.bookstore.order.domain.model.OrderErrorEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    OrderEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void publish(OrderCreatedEvent event) {
        this.send(properties.newQueue(), event);
    }

    public void publish(OrderDeliveredEvent event) {
        this.send(properties.deliveredQueue(), event);
    }

    public void publish(OrderCancelledEvent event) {
        this.send(properties.cancelledQueue(), event);
    }

    public void publish(OrderErrorEvent event) {
        this.send(properties.errorQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.exchange(), routingKey, payload);
    }
}
