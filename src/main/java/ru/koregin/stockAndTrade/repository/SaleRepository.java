package ru.koregin.stockAndTrade.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koregin.stockAndTrade.model.Sale;

public interface SaleRepository extends CrudRepository<Sale, Long> {
}
