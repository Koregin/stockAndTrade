package ru.koregin.stockAndTrade.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/item")
public class ItemController {

    private static Logger logger = Logger.getLogger(ItemController.class.getName());

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping
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

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable("id") long id) {
        logger.info("Find Item by ID: " + id);
        Item item = itemRepository.findItemById(id);
        HttpStatus httpStatus = HttpStatus.OK;
        if (item == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(item);
    }

    @GetMapping("/article/{article}")
    public ResponseEntity<List<Item>> findByArticle(@PathVariable("article") String article) {
        logger.info("Found Item by article: " + article);
        List<Item> items = itemRepository.findItemByArticle(article);
        HttpStatus httpStatus = HttpStatus.OK;
        if (items.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(items);
    }

    @GetMapping("/name/{namePattern}")
    public ResponseEntity<List<Item>> findByName(@PathVariable("namePattern") String pattern,
                                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        logger.info("Find Item by pattern: " + pattern + " and page No = " + pageNo + ", page size = " + pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Item> items = itemRepository.findItemByItemName(pattern, pageable);
        HttpStatus httpStatus = HttpStatus.OK;
        if (items.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(items.getContent());
    }

    @GetMapping("/barcode/{code}")
    public ResponseEntity<List<Item>> findByBarcode(@PathVariable("code") String code) {
        logger.info("Find Item by barcode: " + code);
        List<Item> items = itemRepository.findItemByBarcode(code);
        HttpStatus httpStatus = HttpStatus.OK;
        if (items.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(items);
    }
}
