package com.crio.rentalvideo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crio.rentalvideo.dto.RegisterRequest;
import com.crio.rentalvideo.entity.Role;
import com.crio.rentalvideo.entity.User;
import com.crio.rentalvideo.repository.UserRepository;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        user.setRole(
                request.getRole() != null
                        ? request.getRole()
                        : Role.CUSTOMER
        );

        userRepository.save(user);

        return "User registered successfully";
    }
}