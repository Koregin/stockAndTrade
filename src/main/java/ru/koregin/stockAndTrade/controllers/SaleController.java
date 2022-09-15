package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.koregin.stockAndTrade.model.Sale;
import ru.koregin.stockAndTrade.repository.ItemRepository;
import ru.koregin.stockAndTrade.repository.SaleRepository;

import java.util.logging.Logger;

@RestController
public class SaleController {

    private static Logger logger = Logger.getLogger(ItemController.class.getName());

    private final SaleRepository saleRepository;
    private final ItemRepository itemRepository;

    public SaleController(SaleRepository saleRepository, ItemRepository itemRepository) {
        this.saleRepository = saleRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping("/sale")
    public ResponseEntity<Void> createSale(@RequestBody Sale sale) {
        // Проверить остатки каждого товара на складе и сравнить с документом
    }
}
