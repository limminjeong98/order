package com.example.order.infrastructure.order;

import com.example.order.domain.item.ItemReader;
import com.example.order.domain.order.Order;
import com.example.order.domain.order.OrderCommand;
import com.example.order.domain.order.OrderItemSeriesFactory;
import com.example.order.domain.order.OrderStore;
import com.example.order.domain.order.item.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderItemSeriesFactoryImpl implements OrderItemSeriesFactory {

    private final ItemReader itemReader;
    private final OrderStore orderStore;

    @Override
    public List<OrderItem> store(Order order, OrderCommand.RegisterOrder requestOrder) {
        return requestOrder.getOrderItemList().stream().map(registerOrderItem -> {
            var item = itemReader.getItemBy(registerOrderItem.getItemToken());
            var initOrderItem = registerOrderItem.toEntity(order, item);
            var orderItem = orderStore.store(initOrderItem);

            registerOrderItem.getOrderItemOptionGroupList().forEach(registerOrderItemOptionGroup -> {
                var initOrderItemOptionGroup = registerOrderItemOptionGroup.toEntity(orderItem);
                var orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

                registerOrderItemOptionGroup.getOrderItemOptionList().forEach(registerOrderItemOption -> {
                    var initOrderItemOption = registerOrderItemOption.toEntity(orderItemOptionGroup);
                    orderStore.store(initOrderItemOption);
                });
            });
            return orderItem;
        }).collect(Collectors.toList());
    }
}
