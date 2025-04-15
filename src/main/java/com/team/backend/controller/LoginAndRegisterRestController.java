package com.team.backend.controller;


import com.team.backend.model.dto.LoginResponseDto;
import com.team.backend.model.dto.RegisterRequest;
import com.team.backend.model.dto.RegisterResponseDto;
import com.team.backend.model.mapper.LoginAndRegisterMapper;
import com.team.backend.service.LoginAndRegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@AllArgsConstructor
public class LoginAndRegisterRestController {

    private final LoginAndRegisterService loginAndRegisterService;
    private final LoginAndRegisterMapper mapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        final LoginResponseDto medicalDoctorResponse =
                loginAndRegisterService.register(mapper.fromRegisterRequestDto(registerRequest));
        String responseMessage = "REGISTERED";
        final RegisterResponseDto registered =
                mapper.fromUserResponseDto(medicalDoctorResponse, responseMessage);
        log.info("User registered: {}", registered);

        return ResponseEntity.status(HttpStatus.CREATED).body(registered);
    }

    @GetMapping("/find/{login}")
    public ResponseEntity<LoginResponseDto> findUser(@PathVariable String login) {
        final LoginResponseDto byUsername = loginAndRegisterService.findByLogin(login);
        log.info("User found: {}", byUsername);

        return ResponseEntity.ok(byUsername);
    }


    @DeleteMapping("/delete/{login}")
    public ResponseEntity<LoginResponseDto> deleteUser(@PathVariable String login) {
        log.info("Deleting user: {}", login);

        return ResponseEntity.ok(loginAndRegisterService.deleteUser(login));
    }
}