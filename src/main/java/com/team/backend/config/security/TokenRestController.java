package com.team.backend.config.security;


import com.team.backend.config.security.dto.JwtResponseDto;
import com.team.backend.config.security.dto.TokenRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/login")
@AllArgsConstructor
class TokenRestController {

    private final JwtAuthFacade  jwtAuthFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<JwtResponseDto> fetchToken(@RequestBody @Valid TokenRequestDto dto)
    {
        final JwtResponseDto body = jwtAuthFacade.authenticateAndGenerateToken(dto);
        log.info("Token generated: {}", body);

        return ResponseEntity.ok(body);
    }
}
