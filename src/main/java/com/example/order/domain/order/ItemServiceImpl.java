package com.example.order.domain.order;

import com.example.order.domain.order.option.ItemOption;
import com.example.order.domain.order.option.ItemOptionStore;
import com.example.order.domain.order.optiongroup.ItemOptionGroup;
import com.example.order.domain.order.optiongroup.ItemOptionGroupStore;
import com.example.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    public String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken) {
        var partner = partnerReader.getPartner(partnerToken);
        var initItem = request.toEntity(partner.getId());
        var item = itemStore.store(initItem);
        itemOptionSeriesFactory.store(request, item);
        return item.getItemToken();
    }

    @Override
    public void changeOnSale(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeOnSale();
    }

    @Override
    public void changeEndOfSale(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeEndOfSale();
    }

    @Override
    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        return null;
    }
}
