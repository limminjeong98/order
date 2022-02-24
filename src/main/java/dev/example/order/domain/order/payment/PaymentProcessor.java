package dev.example.order.domain.order.payment;

import dev.example.order.domain.order.Order;
import dev.example.order.domain.order.OrderCommand;

public interface PaymentProcessor {
    void pay(Order order, OrderCommand.PaymentRequest request);
}