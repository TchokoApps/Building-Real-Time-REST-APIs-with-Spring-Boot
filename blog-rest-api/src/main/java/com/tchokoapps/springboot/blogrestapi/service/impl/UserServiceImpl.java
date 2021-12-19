package com.tchokoapps.springboot.blogrestapi.service.impl;

import com.tchokoapps.springboot.blogrestapi.entity.User;
import com.tchokoapps.springboot.blogrestapi.exception.ResourceNotFoundException;
import com.tchokoapps.springboot.blogrestapi.repository.UserRepository;
import com.tchokoapps.springboot.blogrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User createUser(@NotNull User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(@NotNull String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User findByUsername(@NotNull String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public Boolean existsByUsername(@NotNull String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(@NotNull String email) {
        return userRepository.existsByEmail(email);
    }
}
