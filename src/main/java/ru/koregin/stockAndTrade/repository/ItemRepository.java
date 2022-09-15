package ru.koregin.stockAndTrade.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koregin.stockAndTrade.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findItemById(long id);
    Item findItemByArticle(String article);
    Item findItemByArticleAndItemName(String article, String itemName);
}
