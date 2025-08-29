package com.tic_tac_toe.tic_tac_toe.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * This represents a user on tic tac toe game.
 */
@Entity
@Table(name = "users")
@Data
public class UserEntity {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /**
     * Unique username.
     */
    @Column(name = "username")
    private String username;

    /**
     * Encrypted user password.
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * Player's number of victories.
     */
    @Column(name = "number_of_victories")
    private Long numberOfVictories = 0L;

    /**
     * Player's number of games.
     */
    @Column(name = "number_of_games")
    private Long numberOfGames = 0L;
}
