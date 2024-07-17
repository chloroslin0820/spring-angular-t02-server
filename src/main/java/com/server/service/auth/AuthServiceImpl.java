package com.server.service.auth;

import org.springframework.stereotype.Service;
import com.server.dto.SignupRequest;
import com.server.dto.UserDto;
import com.server.entity.User;
import com.server.enums.UserRole;
import com.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("Email has already been registered");
        }

        User user = new User();

        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.Customer);

        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();

        userDto.setId(createdUser.getId());

        return userDto;
    }
}
