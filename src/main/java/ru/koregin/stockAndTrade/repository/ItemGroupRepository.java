package ru.koregin.stockAndTrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koregin.stockAndTrade.model.ItemGroup;

public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {
}
