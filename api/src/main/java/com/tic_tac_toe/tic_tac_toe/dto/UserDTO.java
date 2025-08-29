package com.tic_tac_toe.tic_tac_toe.dto;

import lombok.Data;

@Data
public class UserDTO {
    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Unique username.
     */
    private String username;

    /**
     * Encrypted user password.
     */
    private String userPassword;

    /**
     * Player's number of victories.
     */
    private Long numberOfVictories;

    /**
     * Player's number of games.
     */
    private Long numberOfGames;
}
