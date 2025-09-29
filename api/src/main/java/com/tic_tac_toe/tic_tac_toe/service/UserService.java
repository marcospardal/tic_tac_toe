package com.tic_tac_toe.tic_tac_toe.service;

import com.tic_tac_toe.tic_tac_toe.dto.UserDTO;
import com.tic_tac_toe.tic_tac_toe.entity.UserEntity;
import com.tic_tac_toe.tic_tac_toe.exception.UserNotFoundException;
import com.tic_tac_toe.tic_tac_toe.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to expose user related methods.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public UserDTO findByUsername(String username) {
        Optional<UserEntity> entity = this.userRepository.findByUsername(username);

        return entity.map(this::convertToDto).orElse(null);
    }

    @Transactional
    public void createNewUser(UserEntity newUser) {
        this.userRepository.save(newUser);
    }

    public UserDTO findByUserId(Long userId) {
        return this.userRepository.findById(userId).map(this::convertToDto).orElse(null);
    }

    private UserDTO convertToDto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
