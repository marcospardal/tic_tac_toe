package com.tictactoe.game.dto;

import lombok.Data;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class GameSession {
    private String gameId;
    private List<Player> players = new ArrayList<>();
    private PlayerType[][] board = new PlayerType[3][3];
    private PlayerType currentTurn = PlayerType.X;
    private PlayerType winner;
    private Boolean finished;

    public GameSession(Player firstPlayer) {
        this.gameId = UUID.randomUUID().toString();
        this.players.add(firstPlayer);
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }

    public boolean gameIsOver() {
        return boardIsFull() || checkWinner();
    }

    private boolean boardIsFull() {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .noneMatch(Objects::isNull);
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                setWinner(board[i][0]);
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] != null && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                setWinner(board[0][i]);
            }
        }

        if (board[0][0] != null && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            setWinner(board[0][0]);
        }

        if (board[0][2] != null && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            setWinner(board[0][2]);
        }

        return winner != null;
    }
}
