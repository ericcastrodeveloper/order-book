package com.otter.labs.challenge.orderbook.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SymbolPrice {
    private String symbol;
    private BigDecimal price;
}
