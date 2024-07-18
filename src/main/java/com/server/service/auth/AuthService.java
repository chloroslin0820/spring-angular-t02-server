package com.server.service.auth;

import com.server.dto.SignupRequest;
import com.server.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean validateEmailExists(String email);
}
