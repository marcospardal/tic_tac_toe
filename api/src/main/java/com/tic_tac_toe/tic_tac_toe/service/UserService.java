package com.tic_tac_toe.tic_tac_toe.service;

import com.tic_tac_toe.tic_tac_toe.dto.AuthenticationResponseDTO;
import com.tic_tac_toe.tic_tac_toe.dto.RegisterDTO;
import com.tic_tac_toe.tic_tac_toe.dto.UserDTO;
import com.tic_tac_toe.tic_tac_toe.entity.UserEntity;
import com.tic_tac_toe.tic_tac_toe.exception.UserInvalidCredentials;
import com.tic_tac_toe.tic_tac_toe.exception.UserNotFoundException;
import com.tic_tac_toe.tic_tac_toe.exception.UsernameAlreadyExistsException;
import com.tic_tac_toe.tic_tac_toe.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to expose user related methods.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponseDTO registerNewUser(RegisterDTO newRegister) {
        if (this.userRepository.findByUsername(newRegister.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(newRegister.getUsername());
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(newRegister.getUsername());
        newUser.setUserPassword(passwordEncoder.encode(newRegister.getUserPassword()));

        this.userRepository.save(newUser);
        return generateTokens(newUser.getUsername());
    }

    public AuthenticationResponseDTO login(RegisterDTO userCredentials) {
        Optional<UserEntity> user = this.userRepository.findByUsername(userCredentials.getUsername());

        if (user.isEmpty()) {
            throw new UserNotFoundException(userCredentials.getUsername());
        }

        UserDTO userDto = convertToDto(user.get());

        if (passwordEncoder.matches(userCredentials.getUserPassword(), userDto.getUserPassword())) {
            return generateTokens(userDto.getUsername());
        } else {
            throw new UserInvalidCredentials(userCredentials.getUsername());
        }
    }

    private AuthenticationResponseDTO generateTokens(String username) {
        String accessToken = jwtService.generateToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        return new AuthenticationResponseDTO(username, accessToken, refreshToken);
    }


    private UserDTO convertToDto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
