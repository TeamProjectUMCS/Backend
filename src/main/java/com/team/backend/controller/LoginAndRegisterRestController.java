package com.team.backend.controller;


import com.team.backend.model.dto.LoginResponse;
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
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        final LoginResponse medicalDoctorResponse =
                loginAndRegisterService.register(mapper.fromRegisterRequestDto(registerRequestDto));
        String responseMessage = "REGISTERED";
        final RegisterResponseDto registered =
                mapper.fromUserResponseDto(medicalDoctorResponse, responseMessage);
        log.info("User registered: {}", registered);

        return ResponseEntity.status(HttpStatus.CREATED).body(registered);
    }

    @GetMapping("/find/{login}")
    public ResponseEntity<LoginResponse> findUser(@PathVariable String login) {
        final LoginResponse byUsername = loginAndRegisterService.findByUsername(login);
        log.info("User found: {}", byUsername);

        return ResponseEntity.ok(byUsername);
    }

    @PutMapping("/update/{login}")
    public ResponseEntity<LoginResponse> updateUser(
            @PathVariable String login, @RequestBody @Valid RegisterRequestDto registerRequestDto) {
        final LoginResponse body =
                loginAndRegisterService.updateByLogin(
                        login, mapper.fromRegisterRequestDto(registerRequestDto));
        log.info("User updated: {}", body);

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<LoginResponse> deleteUser(@PathVariable String login) {
        log.info("Deleting user: {}", login);

        return ResponseEntity.ok(loginAndRegisterService.deleteUser(login));
    }
}