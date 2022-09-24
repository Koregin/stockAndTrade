package ru.koregin.stockAndTrade.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.koregin.stockAndTrade.model.ItemDTO;
import ru.koregin.stockAndTrade.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Page<ItemDTO> findByName(String pattern, Pageable pageable) {
        Page item = repository.findItemByItemName(pattern, pageable);
        return null;
    }
}
