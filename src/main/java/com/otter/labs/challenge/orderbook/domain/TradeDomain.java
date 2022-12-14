package com.otter.labs.challenge.orderbook.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDomain {
    private int id;
    private String price;
    private String qty;
    private String quoteQty;
    private long time;
    @JsonProperty(value = "isBuyerMaker")
    private boolean isBuyerMaker;
    @JsonProperty(value = "isBestMatch")
    private boolean isBestMatch;
}
