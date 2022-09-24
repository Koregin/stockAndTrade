package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koregin.stockAndTrade.model.ItemGroup;
import ru.koregin.stockAndTrade.repository.ItemGroupRepository;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/group")
public class ItemGroupController {

    private static Logger logger = Logger.getLogger(ItemController.class.getName());

    private final ItemGroupRepository itemGroupRepository;

    public ItemGroupController(ItemGroupRepository itemGroupRepository) {
        this.itemGroupRepository = itemGroupRepository;
    }

    @PostMapping
    public ResponseEntity<ItemGroup> create(@RequestBody ItemGroup itemGroup) {
        logger.info("Created Item group info: " + itemGroup);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemGroupRepository.save(itemGroup));
    }

    @GetMapping
    public List<ItemGroup> findAll() {
        return itemGroupRepository.findAll();
    }
}
