package com.example.order.domain.order;

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
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken) {

        // 1. get partnerId
        var partner = partnerReader.getPartner(partnerToken);
        var partnerId = partner.getId();

        // 2. item store
        var initItem = request.toEntity(partnerId);
        var item = itemStore.store(initItem);

        // 3. itemOptionGroup + itemOption store
        request.getItemOptionGroupRequestList().forEach(requestItemOptionGroup -> {
            // itemOptionGroup store
            var initItemOptionGroup = ItemOptionGroup.builder()
                    .item(item)
                    .ordering(requestItemOptionGroup.getOrdering())
                    .itemOptionGroupName(requestItemOptionGroup.getItemOptionGroupName())
                    .build();
            var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

            // itemOption store
            requestItemOptionGroup.getItemOptionRequestList().forEach(
                    requestItemOption -> {
                        var initItemOption = ItemOption.builder()
                                .itemOptionGroup(itemOptionGroup)
                                .ordering(requestItemOption.getOrdering())
                                .itemOptionName(requestItemOption.getItemOptionName())
                                .itemOptionPrice(requestItemOption.getItemOptionPrice())
                                .build();

                        itemOptionStore.store(initItemOption);
                    });
        });

        // 4. return itemToken
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
