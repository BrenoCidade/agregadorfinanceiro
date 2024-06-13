package com.brenocidade.agregadorfinanceiro.service;


import com.brenocidade.agregadorfinanceiro.controller.dto.AccountResponseDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.AccountStockResponseDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.AssociateAccountStockDto;
import com.brenocidade.agregadorfinanceiro.entity.AccountStock;
import com.brenocidade.agregadorfinanceiro.entity.AccountStockId;
import com.brenocidade.agregadorfinanceiro.repository.AccountRepository;
import com.brenocidade.agregadorfinanceiro.repository.AccountStockRepository;
import com.brenocidade.agregadorfinanceiro.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
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
                .map(as -> new AccountStockResponseDto(as.getStock().getStockId(), as.getQuantity(), 0.0))
                .toList();
    }
}
