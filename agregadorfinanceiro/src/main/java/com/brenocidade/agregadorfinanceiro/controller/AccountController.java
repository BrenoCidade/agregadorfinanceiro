package com.brenocidade.agregadorfinanceiro.controller;

import com.brenocidade.agregadorfinanceiro.controller.dto.AccountStockResponseDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.AssociateAccountStockDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.CreateAccountDto;
import com.brenocidade.agregadorfinanceiro.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                                  @RequestBody AssociateAccountStockDto createAccountDto) {

        accountService.associateStock(accountId, createAccountDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> ListAssociateStock(@PathVariable("accountId") String accountId) {

        var stocks = accountService.listStocks(accountId);
        return ResponseEntity.ok(stocks);
    }
}
