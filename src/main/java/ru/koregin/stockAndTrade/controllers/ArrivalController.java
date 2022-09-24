package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koregin.stockAndTrade.model.Arrival;
import ru.koregin.stockAndTrade.services.ArrivalService;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/arrival")
public class ArrivalController {

    private static Logger logger = Logger.getLogger(ArrivalController.class.getName());

    private final ArrivalService arrivalService;

    public ArrivalController(ArrivalService arrivalService) {
        this.arrivalService = arrivalService;
    }

    @PostMapping
    public ResponseEntity<String> createArrival(@RequestBody Arrival arrival) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        String response = "CREATED";
        try {
            arrivalService.create(arrival);
        } catch (NoSuchElementException ex) {
            httpStatus = HttpStatus.NOT_FOUND;
            response = ex.getMessage();
            logger.info(response);
        }
        return ResponseEntity
                .status(httpStatus)
                .body(response);
    }
}
