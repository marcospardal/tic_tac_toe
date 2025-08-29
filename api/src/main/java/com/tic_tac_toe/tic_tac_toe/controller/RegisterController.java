package com.tic_tac_toe.tic_tac_toe.controller;

import com.tic_tac_toe.tic_tac_toe.dto.RegisterDTO;
import com.tic_tac_toe.tic_tac_toe.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle register methods.
 */
@RestController
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService service) {
        this.userService = service;
    }

    @PermitAll
    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody RegisterDTO body){
        String token = this.userService.registerNewUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

}
