package com.tchokoapps.springboot.blogrestapi.service.impl;

import com.tchokoapps.springboot.blogrestapi.entity.User;
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
}
