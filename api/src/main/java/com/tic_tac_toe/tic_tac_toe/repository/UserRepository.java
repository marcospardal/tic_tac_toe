package com.tic_tac_toe.tic_tac_toe.repository;

import com.tic_tac_toe.tic_tac_toe.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository to access users table on database.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
