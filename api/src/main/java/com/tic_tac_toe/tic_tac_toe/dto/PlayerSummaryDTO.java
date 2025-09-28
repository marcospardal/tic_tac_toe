package com.tic_tac_toe.tic_tac_toe.dto;

import lombok.Data;

@Data
public class PlayerSummaryDTO {

    /**
     * Player's number of victories.
     */
    private Long numberOfVictories;

    /**
     * Total games played.
     */
    private Long numberOfGames;

    /**
     * Total games lost.
     */
    private Long numberOfLosses;

    /**
     * Winning percentage.
     */
    private Long percentageOfWinning;
}
