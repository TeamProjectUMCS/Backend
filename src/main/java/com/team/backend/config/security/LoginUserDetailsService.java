package com.team.backend.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginAndRegisterService loginAndRegisterService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final LoginResponse byUsername = loginAndRegisterService.findByUsername(username);
        return getUser(byUsername);
    }

    private org.springframework.security.core.userdetails.User getUser(LoginResponse dto) {
        return new org.springframework.security.core.userdetails.User(
                dto.login(),
                dto.password(),
                Collections.emptyList());
    }
}