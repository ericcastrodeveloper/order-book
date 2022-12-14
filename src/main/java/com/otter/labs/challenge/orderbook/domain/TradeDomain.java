package com.otter.labs.challenge.orderbook.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDomain {
    public int id;
    public String price;
    public String qty;
    public String quoteQty;
    public long time;
    public boolean isBuyerMaker;
    public boolean isBestMatch;
}
