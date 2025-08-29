package com.tic_tac_toe.tic_tac_toe.controller;

import com.tic_tac_toe.tic_tac_toe.dto.AuthenticationResponseDTO;
import com.tic_tac_toe.tic_tac_toe.dto.RegisterDTO;
import com.tic_tac_toe.tic_tac_toe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService service) {
        this.userService = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody RegisterDTO body){
        AuthenticationResponseDTO token = this.userService.login(body);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }
}
