package com.tic_tac_toe.tic_tac_toe.dto;

import lombok.Data;

@Data
public class GameResult {
    private String opponentUsername;
    private Integer gameDuration;
    private String winnerUsername;
}
