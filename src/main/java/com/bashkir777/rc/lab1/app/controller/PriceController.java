package com.bashkir777.rc.lab1.app.controller;

import com.bashkir777.rc.lab1.app.service.Asset;
import com.bashkir777.rc.lab1.app.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/price/{symbol}")
    public ResponseEntity<Double> getPrice(@PathVariable String symbol) {
        Asset asset = null;
        for (Asset a : Asset.values()) {
            if (a.getSymbol().equalsIgnoreCase(symbol)) {
                asset = a;
                break;
            }
        }
        if (asset == null) {
            return ResponseEntity.badRequest().build();
        }
        Double price = priceService.getPrice(asset);
        return ResponseEntity.ok(price);
    }

    @GetMapping("/data")
    public ResponseEntity<?> getAllPrices() {
        return ResponseEntity.ok(Arrays.stream(Asset.values())
                .collect(java.util.stream.Collectors.toMap(
                        Asset::getSymbol,
                        priceService::getPrice
                )));
    }
}

