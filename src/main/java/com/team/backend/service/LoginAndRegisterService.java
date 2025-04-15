package com.team.backend.service;


import com.team.backend.model.User;
import com.team.backend.model.dto.LoginRequest;
import com.team.backend.model.dto.LoginResponse;
import com.team.backend.model.mapper.UserMapper;
import com.team.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.mapper.Mapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Log4j2
@AllArgsConstructor
@Component
public class LoginAndRegisterService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public LoginResponse register(LoginRequest requestDto) {
        final User user = userMapper.mapToMedicalDoctor(requestDto);
        final User saved = userRepository.save(user);

        return userMapper.mapToUserResponse(saved);
    }

    public LoginResponse findByUsername(String username) {

        final User user =
                userRepository
                        .findByLogin(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
        log.info("User found: {}", user);

        return userMapper.mapToUserResponse(user);
    }


    public LoginResponse deleteUser(final String login) {
        final User deleted = userRepository
                        .findByLogin(login)
                        .orElseThrow(() -> new UsernameNotFoundException("User: " + login + " not found"));
        userRepository.deleteByLogin(deleted.getLogin());

        return userMapper.mapToUserResponse(deleted);
    }
}