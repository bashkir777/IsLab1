package com.bashkir777.rc.lab1.data.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDTO {
    private Double price;
    private String currency;
    private String symbol;
    private Long timestamp;
}