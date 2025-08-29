package com.tic_tac_toe.tic_tac_toe.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to manage user tokens.
 */
@Getter
@Setter
public class AuthenticationResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String username;

    public AuthenticationResponseDTO(String username, String accessToken, String refreshToken) {
        this.username = username;

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
