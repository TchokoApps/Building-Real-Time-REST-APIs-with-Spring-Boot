package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.entity.User;

public interface UserService {

    User createUser(User user);

    User findByEmail(String email);

    User findByUsernameOrEmail(String username, String email);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
