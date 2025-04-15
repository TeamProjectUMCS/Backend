package com.team.backend.model.mapper;


import com.team.backend.model.User;
import com.team.backend.model.dto.LoginRequest;
import com.team.backend.model.dto.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(LoginRequest userRequest) {
        return User.of(userRequest.username() , userRequest.login(), userRequest.password());
    }

    public LoginResponse mapToUserResponse(User user) {
        return new LoginResponse(user.getUsername(),user.getLogin(), user.getPassword());
    }
}