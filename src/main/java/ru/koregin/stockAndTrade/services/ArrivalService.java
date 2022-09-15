package ru.koregin.stockAndTrade.services;

import org.springframework.stereotype.Service;
import ru.koregin.stockAndTrade.model.Arrival;
import ru.koregin.stockAndTrade.model.Item;
import ru.koregin.stockAndTrade.model.ItemFromArrival;
import ru.koregin.stockAndTrade.repository.ArrivalRepository;
import ru.koregin.stockAndTrade.repository.ItemRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class ArrivalService {

    private static Logger logger = Logger.getLogger(ArrivalService.class.getName());

    private final ArrivalRepository arrivalRepository;
    private final ItemRepository itemRepository;

    public ArrivalService(ArrivalRepository arrivalRepository, ItemRepository itemRepository) {
        this.arrivalRepository = arrivalRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void create(Arrival arrival) {
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
    }
}
