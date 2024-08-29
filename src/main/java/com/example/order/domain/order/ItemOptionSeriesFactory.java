package com.example.order.domain.order;

import com.example.order.domain.order.optiongroup.ItemOptionGroup;

import java.util.List;

public interface ItemOptionSeriesFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item);
}
