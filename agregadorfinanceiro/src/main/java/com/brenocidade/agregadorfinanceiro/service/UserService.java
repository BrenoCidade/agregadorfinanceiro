package com.brenocidade.agregadorfinanceiro.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.brenocidade.agregadorfinanceiro.controller.dto.AccountResponseDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.CreateAccountDto;
import com.brenocidade.agregadorfinanceiro.entity.Account;
import com.brenocidade.agregadorfinanceiro.entity.BillingAddress;
import com.brenocidade.agregadorfinanceiro.repository.AccountRepository;
import com.brenocidade.agregadorfinanceiro.repository.BillingAddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.brenocidade.agregadorfinanceiro.controller.dto.CreateUserDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.UpdateUserDto;
import com.brenocidade.agregadorfinanceiro.entity.User;
import com.brenocidade.agregadorfinanceiro.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository,
                       BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        var entity = new User(UUID.randomUUID(), 
        createUserDto.username(),  
        createUserDto.email(), 
        createUserDto.password(),
        Instant.now(),
        null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            if (updateUserDto.email() != null) {
                user.setEmail(updateUserDto.email());
            }

            userRepository.save(user);
        }
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
           userRepository.deleteById(id); 
        }
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var account = new Account(
                UUID.randomUUID(),
                user,
                null,
                createAccountDto.description(),
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    public List<AccountResponseDto> listAccounts(String userId) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                .toList();
    }
}
