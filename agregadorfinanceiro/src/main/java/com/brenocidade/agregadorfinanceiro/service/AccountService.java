package com.brenocidade.agregadorfinanceiro.service;


import com.brenocidade.agregadorfinanceiro.client.BrapiClient;
import com.brenocidade.agregadorfinanceiro.controller.dto.AccountResponseDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.AccountStockResponseDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.AssociateAccountStockDto;
import com.brenocidade.agregadorfinanceiro.entity.AccountStock;
import com.brenocidade.agregadorfinanceiro.entity.AccountStockId;
import com.brenocidade.agregadorfinanceiro.repository.AccountRepository;
import com.brenocidade.agregadorfinanceiro.repository.AccountStockRepository;
import com.brenocidade.agregadorfinanceiro.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.Token}")
    private String Token;
    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;
    private BrapiClient brapiClient;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }


    public void associateStock(String accountId, AssociateAccountStockDto createAccountDto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(createAccountDto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                createAccountDto.quantity()
        );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDto> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStocks()
                .stream()
                .map(as -> new AccountStockResponseDto(
                        as.getStock().getStockId(),
                        as.getQuantity(),
                        getTotal(as.getQuantity(), as.getStock().getStockId())
                ))
                .toList();
    }

    private double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(Token, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;
    }
}
