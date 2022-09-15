package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koregin.stockAndTrade.model.Item;
import ru.koregin.stockAndTrade.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class ItemController {

    private static Logger logger = Logger.getLogger(ItemController.class.getName());

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping("/item")
    public ResponseEntity<Item> create(@RequestBody Item item) {

        logger.info("Created Item info: " + item.toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemRepository.save(item));
    }

    @PostMapping("/items")
    public ResponseEntity<List<Item>> createPack(@RequestBody List<Item> items) {
        List<Item> createdItems = new ArrayList<>();
        itemRepository.saveAll(items).forEach(createdItems::add);
        logger.info("Created Items info: " + createdItems);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdItems);
    }

    /**
     * Сhecking the availability of goods in stock
     *
     * @param payload
     * @return Quantity in stock
     */
    @GetMapping("/stock")
    public ResponseEntity<Integer> checkItemOnStock(@RequestBody Map<String, String> payload) {
        String article = payload.get("article");
        String itemName = payload.get("itemName");
        logger.info("Check quantity in stock for article=" + article + ", itemName=" + itemName);
        Item foundItem = itemRepository.findItemByArticleAndItemName(article, itemName);
        if (foundItem == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Integer quantityOnStock = foundItem.getStockQuantity();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(quantityOnStock);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> findById(@PathVariable("id") long id) {
        Item item = itemRepository.findItemById(id);
        logger.info("Found Item info: " + item);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(item);
    }

    @GetMapping("/item/article/{article}")
    public ResponseEntity<Item> findByArticle(@PathVariable("article") String article) {
        Item item = itemRepository.findItemByArticle(article);
        logger.info("Found Item by article info: " + item);
        if (item != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(item);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
