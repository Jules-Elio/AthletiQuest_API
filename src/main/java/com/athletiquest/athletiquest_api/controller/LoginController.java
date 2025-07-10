package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.service.UserService;
import com.athletiquest.athletiquest_api.utils.LoginRequest;
import com.athletiquest.athletiquest_api.utils.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token;
        if (!loginRequest.isValid()) {
            return new ResponseEntity<>("Values must not be null or empty", HttpStatus.BAD_REQUEST);
        }
        try {
            token = service.login(loginRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(convertTokenToJson(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpRequest signUpRequest) {
        String token;
        if (!signUpRequest.isValid()) {
            return new ResponseEntity<>("Values must not be null or empty", HttpStatus.BAD_REQUEST);
        }
        try {
            token = service.signUp(signUpRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertTokenToJson(token), HttpStatus.OK);
    }

    private String convertTokenToJson(String value) {
        return "{\"token\":\"" + value + "\"}";
    }

}
