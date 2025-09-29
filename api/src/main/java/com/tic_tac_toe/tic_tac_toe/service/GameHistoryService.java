package com.tic_tac_toe.tic_tac_toe.service;

import com.tic_tac_toe.tic_tac_toe.dto.GameResult;
import com.tic_tac_toe.tic_tac_toe.dto.PlayerSummaryDTO;
import com.tic_tac_toe.tic_tac_toe.dto.UserDTO;
import com.tic_tac_toe.tic_tac_toe.entity.GameEntity;
import com.tic_tac_toe.tic_tac_toe.exception.UserNotFoundException;
import com.tic_tac_toe.tic_tac_toe.repository.GamesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameHistoryService {
    private final UserService userService;
    private final GamesRepository gamesRepository;

    public GameHistoryService(UserService userService, GamesRepository gamesRepository) {
        this.userService = userService;
        this.gamesRepository = gamesRepository;
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

    public List<GameResult> getPlayerGamesHistory(String username) {
        UserDTO user = userService.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        } else {
            List<GameResult> list = new ArrayList<>();
            for (GameEntity gameEntity : this.gamesRepository.findUserGames(user.getId())) {
                GameResult dto = new GameResult();
                UserDTO player1 = userService.findByUserId(gameEntity.getPlayer1Id());
                UserDTO player2 = userService.findByUserId(gameEntity.getPlayer2Id());

                if (player1.getUsername().equals(user.getUsername())) {
                    dto.setOpponentUsername(player2.getUsername());
                } else {
                    dto.setOpponentUsername(player1.getUsername());
                }

                dto.setGameDuration(gameEntity.getGameDuration());
                dto.setWinnerUsername(player2.getId() == gameEntity.getWinnerId() ? player2.getUsername() : player1.getUsername());
                list.add(dto);
            }
            return list;
        }
    }
}
