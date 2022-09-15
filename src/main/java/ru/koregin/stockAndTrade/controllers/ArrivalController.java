package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.koregin.stockAndTrade.model.Arrival;
import ru.koregin.stockAndTrade.services.ArrivalService;

import java.util.logging.Logger;

@RestController
public class ArrivalController {

    private static Logger logger = Logger.getLogger(ArrivalController.class.getName());

    private final ArrivalService arrivalService;

    public ArrivalController(ArrivalService arrivalService) {
        this.arrivalService = arrivalService;
    }

    @PostMapping("/arrival")
    public ResponseEntity<Void> createArrival(@RequestBody Arrival arrival) {
        arrivalService.create(arrival);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
