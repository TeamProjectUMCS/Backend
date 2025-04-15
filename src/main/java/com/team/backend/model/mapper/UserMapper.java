package com.team.backend.model.mapper;


import com.team.backend.model.User;
import com.team.backend.model.dto.LoginRequest;
import com.team.backend.model.dto.LoginResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(LoginRequest userRequest) {
        return User.of(userRequest.username() , userRequest.login(), userRequest.password());
    }

    public LoginResponseDto mapToUserResponse(User user) {
        return new LoginResponseDto(user.getUsername(),user.getLogin(), user.getPassword());
    }
}