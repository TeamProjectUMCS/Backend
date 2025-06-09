package com.team.backend.config.security;

import com.team.backend.model.dto.LoginResponseDto;
import com.team.backend.service.LoginAndRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService
{
    private final LoginAndRegisterService loginAndRegisterService;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException
    {
        final LoginResponseDto byUsername = loginAndRegisterService.findByLogin(login);
        return getUser(byUsername);
    }

    private org.springframework.security.core.userdetails.User getUser(LoginResponseDto dto)
    {
        return new org.springframework.security.core.userdetails.User(
                dto.login(),
                dto.password(),
                Collections.emptyList());
    }
}