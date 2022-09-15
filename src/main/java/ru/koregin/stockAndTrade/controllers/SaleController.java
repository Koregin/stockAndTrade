package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.koregin.stockAndTrade.model.Sale;
import ru.koregin.stockAndTrade.services.SaleService;

import java.util.logging.Logger;

@RestController
public class SaleController {

    private static Logger logger = Logger.getLogger(SaleController.class.getName());

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sale")
    public ResponseEntity<Void> createSale(@RequestBody Sale sale) {
        saleService.create(sale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
