package com.otter.labs.challenge.orderbook.controller;

import com.otter.labs.challenge.orderbook.domain.SymbolPrice;
import com.otter.labs.challenge.orderbook.domain.TradeDomain;
import com.otter.labs.challenge.orderbook.service.BinanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/binance")
@CrossOrigin(origins = "http://localhost:3000")
public class BinanceController {

    private BinanceService service;
    private SimpMessagingTemplate template;

    public BinanceController(BinanceService service, SimpMessagingTemplate template) {
        this.service = service;
        this.template = template;
    }

    @GetMapping("send/trades")
    public ResponseEntity<?> sendMessage(@RequestParam String symbol){
        List<TradeDomain> response = service.getTrades(symbol);
        template.convertAndSend("/topic/trades", response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("send/symbolPrice")
    public ResponseEntity<?> getAveragePrice(@RequestParam String symbol){
        SymbolPrice response = service.getPrice(symbol);
        template.convertAndSend("/topic/symbolPrice", response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SendTo("/topic/trades")
    public List<TradeDomain> getTrades(@Payload List<TradeDomain> tradeDomains){
        return tradeDomains;
    }

    @SendTo("/topic/symbolPrice")
    public SymbolPrice getSymbolPrice(@Payload SymbolPrice symbolPrice){
        return symbolPrice;
    }
}
