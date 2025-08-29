package com.tic_tac_toe.tic_tac_toe.exception;

/**
 * Custom exception to invalid credentials.
 */
public class UserInvalidCredentials extends  RuntimeException {
    public UserInvalidCredentials(String username) {
        super("Invalid credentials for '" + username + "'");
    }
}
