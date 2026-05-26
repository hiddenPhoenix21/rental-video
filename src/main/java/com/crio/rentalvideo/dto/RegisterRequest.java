package com.crio.rentalvideo.dto;

import com.crio.rentalvideo.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}