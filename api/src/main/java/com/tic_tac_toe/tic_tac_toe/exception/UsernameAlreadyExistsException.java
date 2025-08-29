package com.tic_tac_toe.tic_tac_toe.exception;

/**
 * Custom exception class for username already taken.
 */
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Username '" + username + "' is already taken");
    }
}
