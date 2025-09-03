package com.tic_tac_toe.tic_tac_toe.controller;

import com.tic_tac_toe.tic_tac_toe.dto.AuthenticationResponseDTO;
import com.tic_tac_toe.tic_tac_toe.dto.RegisterDTO;
import com.tic_tac_toe.tic_tac_toe.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody RegisterDTO body){
        AuthenticationResponseDTO token = this.authService.login(body);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> registerNewUser(@RequestBody RegisterDTO body){
        AuthenticationResponseDTO token = this.authService.registerNewUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
