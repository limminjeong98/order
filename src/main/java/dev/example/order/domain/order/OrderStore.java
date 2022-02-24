package dev.example.order.domain.order;

import dev.example.order.domain.order.item.OrderItem;
import dev.example.order.domain.order.item.OrderItemOption;
import dev.example.order.domain.order.item.OrderItemOptionGroup;

public interface OrderStore {
    Order store(Order order);

    OrderItem store(OrderItem orderItem);

    OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);

    OrderItemOption store(OrderItemOption orderItemOption);
}