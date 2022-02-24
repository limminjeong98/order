package dev.example.order.infrastructure.order.payment;

import dev.example.order.domain.order.OrderCommand;
import dev.example.order.domain.order.payment.PayMethod;

public interface PaymentApiCaller {
    boolean support(PayMethod payMethod);
    void pay(OrderCommand.PaymentRequest request);
}