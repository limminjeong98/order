package com.example.order.infrastructure.item.option;

import com.example.order.domain.order.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
