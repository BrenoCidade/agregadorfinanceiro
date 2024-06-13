package com.brenocidade.agregadorfinanceiro.service;

import com.brenocidade.agregadorfinanceiro.controller.dto.CreateStockDto;
import com.brenocidade.agregadorfinanceiro.entity.Stock;
import com.brenocidade.agregadorfinanceiro.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
