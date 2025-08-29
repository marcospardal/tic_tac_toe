package com.tic_tac_toe.tic_tac_toe.exception;

/**
 * Custom exception to handle user not found.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User '" + username + "' not found");
    }
}
