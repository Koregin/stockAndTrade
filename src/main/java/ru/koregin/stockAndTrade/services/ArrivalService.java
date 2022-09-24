package ru.koregin.stockAndTrade.services;

import org.springframework.stereotype.Service;
import ru.koregin.stockAndTrade.model.Arrival;
import ru.koregin.stockAndTrade.model.Item;
import ru.koregin.stockAndTrade.model.ItemArrival;
import ru.koregin.stockAndTrade.repository.ArrivalRepository;
import ru.koregin.stockAndTrade.repository.ItemRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
        List<ItemArrival> items = arrival.getItemsArrival();
        for (ItemArrival item : items) {
            Optional<Item> foundItem = itemRepository.findById(item.getItem().getId());
            if (foundItem.isEmpty()) {
                logger.info("Товар ID: " + item.getItem().getId() + " не найден в базе данных");
                throw new NoSuchElementException("Товар ID: " + item.getItem().getId() + " не найден в базе данных");
            }
            foundItem.get().setPurchasePrice(item.getPrice());
            foundItem.get().setStockQuantity(item.getQuantity() + foundItem.get().getStockQuantity());
            itemRepository.save(foundItem.get());
        }
        arrivalRepository.save(arrival);
        logger.info("Поступление создано");
    }
}
