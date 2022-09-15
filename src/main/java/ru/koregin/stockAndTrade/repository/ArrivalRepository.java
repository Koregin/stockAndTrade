package ru.koregin.stockAndTrade.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koregin.stockAndTrade.model.Arrival;

public interface ArrivalRepository extends CrudRepository<Arrival, Long> {
}
