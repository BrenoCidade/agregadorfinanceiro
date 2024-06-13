package com.brenocidade.agregadorfinanceiro.controller;

import java.net.URI;
import java.util.List;

import com.brenocidade.agregadorfinanceiro.controller.dto.AccountResponseDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.CreateAccountDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.CreateUserDto;
import com.brenocidade.agregadorfinanceiro.controller.dto.UpdateUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brenocidade.agregadorfinanceiro.entity.User;
import com.brenocidade.agregadorfinanceiro.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId  = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.listUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserById(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) { 
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccountById(@PathVariable("userId") String userId,
                                           @RequestBody CreateAccountDto createAccountDto) {

        userService.createAccount(userId, createAccountDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> listAccounts(@PathVariable("userId") String userId){

        var accounts = userService.listAccounts(userId);

        return ResponseEntity.ok(accounts);
    }
}
