package com.tictactoe.game.controller;

import com.tictactoe.game.dto.GameSession;
import com.tictactoe.game.dto.Move;
import com.tictactoe.game.dto.Player;
import com.tictactoe.game.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameController(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/matchmaking")
    public void joinGame(@Payload Player player) {
        GameSession game = gameService.matchmaking(player);

        if (game != null) {
            messagingTemplate.convertAndSend("/topic/match/" + game.getPlayers().get(0).getPlayerId(), game);
            messagingTemplate.convertAndSend("/topic/match/" + game.getPlayers().get(1).getPlayerId(), game);
        }
    }

    @MessageMapping("/cancel-waiting")
    public void cancelWaiting(@Payload String playerId) {
        gameService.cancelWaiting(playerId);
    }

    @MessageMapping("/quit-game")
    public void quitGame(@Payload String gameId) {
        GameSession game = gameService.quitGame(gameId);
        messagingTemplate.convertAndSend("/topic/game/" + gameId, game);
    }

    @MessageMapping("/move")
    public void processMove(@Payload Move playerMove) {
        GameSession updatedGame = gameService.makeMove(playerMove);

        messagingTemplate.convertAndSend("/topic/game/" + playerMove.getGameId(), updatedGame);
    }
}
