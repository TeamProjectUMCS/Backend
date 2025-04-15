package com.team.backend.model.mapper;


import com.team.backend.model.User;
import com.team.backend.model.dto.LoginRequest;
import com.team.backend.model.dto.LoginResponseDto;
import com.team.backend.model.dto.RegisterRequest;
import com.team.backend.model.dto.RegisterResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(RegisterRequest userRequest) {
        return User.of(userRequest.username() , userRequest.login(), userRequest.password(),userRequest.sex(),userRequest.preference());
    }

    public RegisterResponseDto mapToRegisterResponse(User user) {
        String responseMessage = "REGISTERED";
        return new RegisterResponseDto(user.getUsername(),user.getLogin(), "REGISTERED");
    }

    public LoginResponseDto mapToUserResponse(User user) {
        return new LoginResponseDto(user.getUsername(),user.getLogin(), user.getPassword());
    }
}