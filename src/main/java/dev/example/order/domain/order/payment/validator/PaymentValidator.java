package dev.example.order.domain.order.payment.validator;

import dev.example.order.domain.order.Order;
import dev.example.order.domain.order.OrderCommand;

public interface PaymentValidator {
    void validate(Order order, OrderCommand.PaymentRequest paymentRequest);
}