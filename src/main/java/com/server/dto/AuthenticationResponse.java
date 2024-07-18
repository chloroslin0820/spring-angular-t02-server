package com.server.dto;

import com.server.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;
}
