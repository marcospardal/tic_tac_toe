package com.tic_tac_toe.tic_tac_toe.service;

import com.tic_tac_toe.tic_tac_toe.dto.AuthenticationResponseDTO;
import com.tic_tac_toe.tic_tac_toe.dto.RegisterDTO;
import com.tic_tac_toe.tic_tac_toe.dto.UserDTO;
import com.tic_tac_toe.tic_tac_toe.entity.UserEntity;
import com.tic_tac_toe.tic_tac_toe.exception.UserInvalidCredentials;
import com.tic_tac_toe.tic_tac_toe.exception.UserNotFoundException;
import com.tic_tac_toe.tic_tac_toe.exception.UsernameAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserService userService;

    public AuthService(PasswordEncoder passwordEncoder, JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public AuthenticationResponseDTO registerNewUser(RegisterDTO newRegister) {
        if (this.userService.findByUsername(newRegister.getUsername()) != null) {
            throw new UsernameAlreadyExistsException(newRegister.getUsername());
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(newRegister.getUsername());
        newUser.setUserPassword(passwordEncoder.encode(newRegister.getUserPassword()));

        this.userService.createNewUser(newUser);
        return generateTokens(newUser.getUsername());
    }

    public AuthenticationResponseDTO login(RegisterDTO userCredentials) {
        UserDTO user = this.userService.findByUsername(userCredentials.getUsername());

        if (user == null) {
            throw new UserNotFoundException(userCredentials.getUsername());
        }

        if (passwordEncoder.matches(userCredentials.getUserPassword(), user.getUserPassword())) {
            return generateTokens(user.getUsername());
        } else {
            throw new UserInvalidCredentials(userCredentials.getUsername());
        }
    }

    private AuthenticationResponseDTO generateTokens(String username) {
        String accessToken = jwtService.generateToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        return new AuthenticationResponseDTO(username, accessToken, refreshToken);
    }
}
