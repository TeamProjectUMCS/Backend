package com.team.backend.model.mapper;

import com.team.backend.model.dto.LoginRequest;
import com.team.backend.model.dto.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginAndRegisterMapper {
    private final PasswordEncoder passwordEncoder;

    public LoginRequest fromRegisterRequestDto(RegisterRequestDto dto) {
        return new LoginRequest(dto.login(), passwordEncoder.encode(dto.password()));
    }

    public RegisterResponseDto fromUserResponseDto(LoginResponse dto, String message) {
        return new RegisterResponseDto(dto.login(), message);
    }
}