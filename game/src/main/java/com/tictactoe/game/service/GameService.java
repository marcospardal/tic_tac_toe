package com.tictactoe.game.service;

import com.tictactoe.game.dto.GameSession;
import com.tictactoe.game.dto.Move;
import com.tictactoe.game.dto.Player;
import com.tictactoe.game.dto.PlayerType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    private final Map<String, GameSession> activeGames = new ConcurrentHashMap<>();
    private final List<Player> waitingPlayers = new ArrayList<>();

    public synchronized GameSession matchmaking(Player player) {
        if (waitingPlayers.isEmpty()) {
            waitingPlayers.add(player);
            return null;
        }

        if (waitingPlayers.get(0).getPlayerId().equals(player.getPlayerId())) return null;

        if (waitingPlayers.get(0).getPlayerType() == player.getPlayerType()) {
            waitingPlayers.add(player);
            return null;
        } else {
            GameSession newGame = new GameSession(waitingPlayers.get(0));
            newGame.addPlayer(player);
            activeGames.put(newGame.getGameId(), newGame);

            waitingPlayers.remove(0);
            return newGame;
        }
    }

    public GameSession makeMove(Move playerMove) {
        GameSession game = activeGames.get(playerMove.getGameId());
        game.getBoard()[playerMove.getRow()][playerMove.getColumn()] = playerMove.getPlayerType();

        game.setCurrentTurn(game.gameIsOver() ? null : game.getCurrentTurn() == PlayerType.X ? PlayerType.O : PlayerType.X);

        if(game.gameIsOver()) {
            activeGames.remove(game.getGameId());
        }
        return game;
    }

    public void cancelWaiting(String playerId) {
        waitingPlayers.removeIf(player -> player.getPlayerId().equals(playerId));
    }

    public GameSession quitGame(String gameId) {
        GameSession game = activeGames.get(gameId);
        game.setFinished(true);
        activeGames.remove(gameId);
        return game;
    }
}
