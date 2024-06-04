package com.brenocidade.agregadorfinanceiro.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brenocidade.agregadorfinanceiro.controller.CreateUserDto;
import com.brenocidade.agregadorfinanceiro.entity.User;
import com.brenocidade.agregadorfinanceiro.repository.UserRepository;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}