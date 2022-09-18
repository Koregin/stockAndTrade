package ru.koregin.stockAndTrade.services;

import org.springframework.stereotype.Service;
import ru.koregin.stockAndTrade.model.Item;
import ru.koregin.stockAndTrade.model.ItemSale;
import ru.koregin.stockAndTrade.model.Sale;
import ru.koregin.stockAndTrade.repository.ItemRepository;
import ru.koregin.stockAndTrade.repository.SaleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SaleService {

    private static Logger logger = Logger.getLogger(SaleService.class.getName());

    private final SaleRepository saleRepository;
    private final ItemRepository itemRepository;

    public SaleService(SaleRepository saleRepository, ItemRepository itemRepository) {
        this.saleRepository = saleRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void create(Sale sale) {
        List<ItemSale> items = sale.getItemsSale();
        for (ItemSale item : items) {
            Optional<Item> foundItem = itemRepository.findById(item.getItem().getId());
            if (foundItem.isEmpty()) {
                throw new NoSuchElementException("Товар " + item.getItem().getItemName() + " не найден в базе данных");
            }
            if (foundItem.get().getStockQuantity() < item.getQuantity()) {
                throw new ArithmeticException("На складе не достаточно количества для продажи товара " + item.getItem().getItemName());
            }
            foundItem.get().setStockQuantity(foundItem.get().getStockQuantity() - item.getQuantity());
            itemRepository.save(foundItem.get());
        }
        saleRepository.save(sale);
        logger.info("Продажа создана");
    }
}
