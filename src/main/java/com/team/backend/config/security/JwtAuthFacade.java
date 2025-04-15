package com.team.backend.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team.backend.config.security.dto.JwtResponseDto;
import com.team.backend.config.security.dto.TokenRequestDto;
import com.team.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.*;

@Log4j2
@Component
@AllArgsConstructor
public class JwtAuthFacade {

    private final AuthenticationManager authenticatorManager;
    private final JwtConfigProperties properties;
    private final UserRepository userRepository;

    public JwtResponseDto authenticateAndGenerateToken(TokenRequestDto tokenRequestDto){
        Authentication authenticate = authenticatorManager.authenticate(
                new UsernamePasswordAuthenticationToken(tokenRequestDto.login(), tokenRequestDto.password())
        );
        final User principal = (User) authenticate.getPrincipal();
        String token = createToken(principal);
        String login = principal.getUsername();
        Long id = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

        return new JwtResponseDto(login, token, id);
    }

    private String createToken(final User user) {
        String secretKey =  properties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        ZonedDateTime localTime = LocalDateTime.now().atZone(ZoneId.of("Europe/Warsaw"));
        Instant now = localTime.toInstant();
        Instant expireAt = now.plus(Duration.ofHours(12));
        String issuer = "TeamProject Backend";

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expireAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }

}