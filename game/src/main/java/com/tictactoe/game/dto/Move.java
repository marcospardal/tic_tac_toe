package com.tictactoe.game.dto;

import lombok.Data;

@Data
public class Move {
    private String gameId;
    private int row;
    private int column;
    private PlayerType playerType;
}
