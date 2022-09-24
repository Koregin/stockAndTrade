package ru.koregin.stockAndTrade.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koregin.stockAndTrade.model.Vendor;
import ru.koregin.stockAndTrade.repository.VendorRepository;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private static Logger logger = Logger.getLogger(ItemController.class.getName());

    public final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @PostMapping
    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) {
        logger.info("Created Vendor info: " + vendor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vendorRepository.save(vendor));
    }

    @GetMapping
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }
}
