package ru.koregin.stockAndTrade.services;

import org.springframework.stereotype.Service;
import ru.koregin.stockAndTrade.model.Item;
import ru.koregin.stockAndTrade.model.ItemFromSale;
import ru.koregin.stockAndTrade.model.Sale;
import ru.koregin.stockAndTrade.repository.ItemRepository;
import ru.koregin.stockAndTrade.repository.SaleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
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
        List<ItemFromSale> items = sale.getItemsFromSale();
        for (ItemFromSale item : items) {
            Item foundItem = itemRepository.findItemByArticleAndItemName(item.getArticle(), item.getItemName());
            if (foundItem == null) {
                throw new NoSuchElementException("Товар " + item.getItemName() + " не найден в базе данных");
            }
            if (foundItem.getStockQuantity() < item.getQuantity()) {
                throw new ArithmeticException("На складе не достаточно количества для продажи товара " + item.getItemName());
            }
            foundItem.setStockQuantity(foundItem.getStockQuantity() - item.getQuantity());
            itemRepository.save(foundItem);
        }
        saleRepository.save(sale);
        logger.info("Продажа создана");
    }
}
