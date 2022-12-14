package com.otter.labs.challenge.orderbook.service;

import com.otter.labs.challenge.orderbook.domain.TradeDomain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BinanceService {

    private RestTemplate restTemplate;
    @Value("${binance.api.host}")
    private String binanceAPI;
    @Value("${binance.api.trade.path}")
    private String tradePath;

    public BinanceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<TradeDomain> getTrades(String symbol){
        Map<String, String> params = new HashMap<>();
        params.put("symbol", symbol);
        ResponseEntity<TradeDomain[]> response = restTemplate.exchange(binanceAPI.concat(tradePath) + "?symbol={symbol}", HttpMethod.GET, null, TradeDomain[].class, params);
        if(response != null)
        return Arrays.asList(response.getBody());
        else
            return new ArrayList<>();
    }
}
