package com.example.order.infrastructure.item;

import com.example.order.common.exception.EntityNotFoundException;
import com.example.order.domain.order.Item;
import com.example.order.domain.order.ItemInfo;
import com.example.order.domain.order.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {

    private final ItemRepository itemRepository;

    @Override
    public Item getItemBy(String itemToken) {
        return itemRepository.findByItemToken(itemToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Item> findItemAllBy(List<String> itemTokenList) {
        return null;
    }

    @Override
    public List<ItemInfo.ItemOptionGroup> getItemOptionSeries(Item item) {
        return List.of();
    }
}
