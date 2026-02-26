package com.tictactoe.game.dto;

import lombok.Data;

@Data
public class Player {
    private String playerId;
    private PlayerType playerType;
}
