package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koregin.stockAndTrade.model.Sale;
import ru.koregin.stockAndTrade.services.SaleService;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private static Logger logger = Logger.getLogger(SaleController.class.getName());

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<String> createSale(@RequestBody Sale sale) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        String response = "CREATED";
        try {
            saleService.create(sale);
        } catch (NoSuchElementException ex) {
            httpStatus = HttpStatus.NOT_FOUND;
            response = ex.getMessage();
        } catch (ArithmeticException ex) {
            httpStatus = HttpStatus.INSUFFICIENT_STORAGE;
            response = ex.getMessage();
        } finally {
            logger.info(response);
        }
        return ResponseEntity
                .status(httpStatus)
                .body(response);
    }
}
