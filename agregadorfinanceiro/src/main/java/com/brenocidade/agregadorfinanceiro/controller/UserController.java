package com.brenocidade.agregadorfinanceiro.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
