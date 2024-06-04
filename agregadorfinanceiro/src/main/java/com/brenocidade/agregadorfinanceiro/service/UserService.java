package com.brenocidade.agregadorfinanceiro.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brenocidade.agregadorfinanceiro.controller.CreateUserDto;
import com.brenocidade.agregadorfinanceiro.controller.UpdateUserDto;
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
}
