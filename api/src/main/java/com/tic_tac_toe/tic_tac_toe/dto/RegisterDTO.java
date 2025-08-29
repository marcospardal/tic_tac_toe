package com.tic_tac_toe.tic_tac_toe.dto;

import lombok.Getter;

/**
 * DTO used to register on system.
 */
@Getter
public class RegisterDTO {
    /**
     * New user username.
     */
    private String username;

    /**
     * New user password.
     */
    private String userPassword;
}
