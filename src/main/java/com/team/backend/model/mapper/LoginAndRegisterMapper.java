package com.team.backend.model.mapper;

import com.team.backend.model.dto.LoginRequest;
import com.team.backend.model.dto.LoginResponseDto;
import com.team.backend.model.dto.RegisterRequest;
import com.team.backend.model.dto.RegisterResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginAndRegisterMapper {
    private final PasswordEncoder passwordEncoder;

    public LoginRequest fromRegisterRequestDto(RegisterRequest dto) {
        return new LoginRequest(dto.username(), dto.login(), passwordEncoder.encode(dto.password()));
    }

    public RegisterResponseDto fromUserResponseDto(LoginResponseDto dto, String message) {
        return new RegisterResponseDto(dto.username(),dto.login(), message);
    }
}