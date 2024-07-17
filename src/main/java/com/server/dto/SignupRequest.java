package com.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "Email should not be blank")
    @NotNull(message = "Email should not be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Name should not be blank")
    @NotNull(message = "Name should not be null")
    private String name;

    @NotBlank(message = "Password should not be blank")
    @NotNull(message = "Password should not be null")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
