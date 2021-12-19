package com.tchokoapps.springboot.blogrestapi.controller;

import com.tchokoapps.springboot.blogrestapi.dto.LoginDto;
import com.tchokoapps.springboot.blogrestapi.dto.SignUpDto;
import com.tchokoapps.springboot.blogrestapi.entity.Role;
import com.tchokoapps.springboot.blogrestapi.entity.User;
import com.tchokoapps.springboot.blogrestapi.enums.RoleEnum;
import com.tchokoapps.springboot.blogrestapi.service.RoleService;
import com.tchokoapps.springboot.blogrestapi.service.UserService;
import com.tchokoapps.springboot.blogrestapi.utils.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;
//    private JwtTockenProvider jwtTockenProvider;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody @NotNull LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed successfully", HttpStatus.OK);
    }

//    @PostMapping("/signin/jwt")
//    public ResponseEntity<JwtAuthResponse> authenticateUserByJwt(@RequestBody @NotNull LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
//                loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = jwtTockenProvider.generateTocken(authentication);
//
//        return ResponseEntity.ok(new JwtAuthResponse(token));
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@NotNull @RequestBody SignUpDto signUpDto) {

        if (userService.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username exist already!", HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email exist already!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(PasswordEncoder.encodePassword(signUpDto.getPassword()));
        Role role = roleService.findByName(RoleEnum.USER.name());
        user.setRoles(Collections.singleton(role));

        userService.createUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);


    }


}
