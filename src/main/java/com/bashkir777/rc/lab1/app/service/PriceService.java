package com.bashkir777.rc.lab1.app.service;


import com.bashkir777.rc.lab1.data.dto.PriceResponseDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<Asset, Double> cache = new EnumMap<>(Asset.class);

    private static final String API_URL = "https://api.gold-api.com/price/{symbol}";

    public Double getPrice(Asset asset) {
        return cache.get(asset);
    }

    @PostConstruct
    public void init() {
        log.info("Fetching initial prices...");
        updatePrices();
    }

    @Scheduled(fixedRate = 60_000)
    public void updatePrices() {
        for (Asset asset : Asset.values()) {
            try {
                ResponseEntity<PriceResponseDTO> response = restTemplate.getForEntity(API_URL, PriceResponseDTO.class, asset.getSymbol());
                if (response.getBody() != null && response.getBody().getPrice() != null) {
                    cache.put(asset, response.getBody().getPrice());
                    log.info("Updated price for {}: {}", asset, response.getBody().getPrice());
                }
            } catch (Exception e) {
                log.error("Failed to fetch price for {}: {}", asset, e.getMessage());
            }
        }
    }
}


