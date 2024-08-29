package com.example.order.domain.order;

import com.example.order.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStore orderStore;
    private final ItemReader itemReader;

    @Override
    public String registerOrder(OrderCommand.RegisterOrder registerOrder) {
        Order order = orderStore.store(registerOrder.toEntity());
        registerOrder.getOrderItemList().forEach(
                registerOrderItem -> {
                    var item = itemReader.getItemBy(registerOrderItem.getItemToken());
                    var initOrderItem = registerOrderItem.toEntity(order, item);
                    var orderItem = orderStore.store(initOrderItem);

                    registerOrderItem.getOrderItemOptionGroupList().forEach(
                            registerOrderItemOptionGroup -> {
                                var initOrderItemOptionGroup = registerOrderItemOptionGroup.toEntity(orderItem);
                                var orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

                                registerOrderItemOptionGroup.getOrderItemOptionList().forEach(
                                        registerOrderItemOption -> {
                                            var initOrderItemOption = registerOrderItemOption.toEntity(orderItemOptionGroup);
                                            orderStore.store(initOrderItemOption);
                                        });
                            });
                });
        return order.getOrderToken();
    }
}
