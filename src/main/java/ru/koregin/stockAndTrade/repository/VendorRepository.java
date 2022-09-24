package ru.koregin.stockAndTrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koregin.stockAndTrade.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
