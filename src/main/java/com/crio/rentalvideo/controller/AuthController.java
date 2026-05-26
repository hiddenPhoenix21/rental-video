package com.crio.rentalvideo.controller;

import com.crio.rentalvideo.dto.LoginRequest;
import com.crio.rentalvideo.dto.RegisterRequest;
import com.crio.rentalvideo.entity.Role;
import com.crio.rentalvideo.entity.User;
import com.crio.rentalvideo.repository.UserRepository;
import com.crio.rentalvideo.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setRole(
                request.getRole() == null
                        ? Role.CUSTOMER
                        : request.getRole()
        );

        userRepository.save(user);

        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        return JwtUtil.generateToken(user.getEmail());
    }
}