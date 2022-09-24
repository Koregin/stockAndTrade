package ru.koregin.stockAndTrade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.koregin.stockAndTrade.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findItemById(long id);
    List<Item> findItemByArticle(String article);
    Item findItemByArticleAndItemName(String article, String itemName);

    @Query(nativeQuery = true, value = "SELECT * FROM items WHERE item_name LIKE %?%")
    Page<Item> findItemByItemName(String regex, Pageable pageable);

    List<Item> findItemByBarcode(String barcode);
}
