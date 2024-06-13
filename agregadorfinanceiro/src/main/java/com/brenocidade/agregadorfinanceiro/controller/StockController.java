package com.brenocidade.agregadorfinanceiro.controller;


import com.brenocidade.agregadorfinanceiro.controller.dto.CreateStockDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.CreateUserDto;
import com.brenocidade.agregadorfinanceiro.entity.User;
import com.brenocidade.agregadorfinanceiro.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/stocks")
public class StockController{

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping()
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto) {

        stockService.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
