package com.tic_tac_toe.tic_tac_toe.service;

import com.tic_tac_toe.tic_tac_toe.dto.PlayerSummaryDTO;
import com.tic_tac_toe.tic_tac_toe.dto.UserDTO;
import com.tic_tac_toe.tic_tac_toe.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GameHistoryService {
    private final UserService userService;

    public GameHistoryService(UserService userService) {
        this.userService = userService;
    }

    public PlayerSummaryDTO getPlayerSummary(String username) {
        UserDTO user = userService.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        } else {
            Long userTotalGames = user.getNumberOfGames();
            Long userTotalVictories = user.getNumberOfVictories();

            PlayerSummaryDTO playerSummary = new PlayerSummaryDTO();
            playerSummary.setNumberOfGames(userTotalGames);
            playerSummary.setNumberOfVictories(userTotalVictories);
            playerSummary.setNumberOfLosses(userTotalGames - userTotalVictories);

            if (userTotalGames != 0L) {
                playerSummary.setPercentageOfWinning((userTotalGames * 100) / userTotalGames);
            } else {
                playerSummary.setPercentageOfWinning(0L);
            }


            return playerSummary;
        }
    }
}
