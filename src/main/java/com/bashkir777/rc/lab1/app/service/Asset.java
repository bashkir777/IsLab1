package com.bashkir777.rc.lab1.app.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Asset {
    SILVER("XAG"),
    GOLD("XAU"),
    BITCOIN("BTC"),
    ETHEREUM("ETH"),
    PALLADIUM("XPD"),
    COPPER("HG");

    private final String symbol;
}
