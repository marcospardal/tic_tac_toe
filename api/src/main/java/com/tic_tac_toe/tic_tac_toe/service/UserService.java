package com.tic_tac_toe.tic_tac_toe.service;

import com.tic_tac_toe.tic_tac_toe.dto.RegisterDTO;
import com.tic_tac_toe.tic_tac_toe.dto.UserDTO;
import com.tic_tac_toe.tic_tac_toe.entity.UserEntity;
import com.tic_tac_toe.tic_tac_toe.exception.UsernameAlreadyExistsException;
import com.tic_tac_toe.tic_tac_toe.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String registerNewUser(RegisterDTO newRegister) {
        if (this.userRepository.findByUsername(newRegister.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(newRegister.getUsername());
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(newRegister.getUsername());
        newUser.setUserPassword(passwordEncoder.encode(newRegister.getUserPassword()));

        this.userRepository.save(newUser);
        return jwtService.generateToken(newUser.getUsername());
    }


    private UserDTO convertToDto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
