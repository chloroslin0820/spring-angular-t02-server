package com.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.SignupRequest;
import com.server.dto.UserDto;
import com.server.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody @Valid SignupRequest signupRequest) {
        try {
            
            UserDto createdCustomerDto = authService.createCustomer(signupRequest);

            if(createdCustomerDto == null) {
                return ResponseEntity.badRequest().body("Failed to create account, please try again");
            }

            return ResponseEntity.ok().body(createdCustomerDto);
            
        } catch (IllegalArgumentException e) {

            String errorMessage = e.getMessage();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("email", errorMessage);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
