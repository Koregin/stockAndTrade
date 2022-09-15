package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.koregin.stockAndTrade.model.Arrival;
import ru.koregin.stockAndTrade.model.Item;
import ru.koregin.stockAndTrade.model.ItemFromArrival;
import ru.koregin.stockAndTrade.repository.ArrivalRepository;
import ru.koregin.stockAndTrade.repository.ItemRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
public class ArrivalController {

    private static Logger logger = Logger.getLogger(ItemController.class.getName());

    private final ArrivalRepository arrivalRepository;
    private final ItemRepository itemRepository;

    public ArrivalController(ArrivalRepository arrivalRepository, ItemRepository itemRepository) {
        this.arrivalRepository = arrivalRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * Create Arrival
     * @param arrival
     * @return void
     */
    @Transactional
    @PostMapping("/arrival")
    public ResponseEntity<Void> createArrival(@RequestBody Arrival arrival) {
        List<ItemFromArrival> items = arrival.getItemsFromArrival();
        for (ItemFromArrival item : items) {
            Item foundItem = itemRepository.findItemByArticleAndItemName(item.getArticle(), item.getItemName());
            if (foundItem == null) {
                throw new NoSuchElementException("Товар " + item.getItemName() + " не найден в базе данных");
            }
            foundItem.setPurchasePrice(item.getPrice());
            foundItem.setStockQuantity(item.getQuantity() + foundItem.getStockQuantity());
            itemRepository.save(foundItem);
        }
        arrivalRepository.save(arrival);
        logger.info("Поступление создано");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
