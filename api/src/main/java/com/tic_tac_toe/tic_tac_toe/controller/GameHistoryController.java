package com.tic_tac_toe.tic_tac_toe.controller;

import com.tic_tac_toe.tic_tac_toe.dto.PlayerSummaryDTO;
import com.tic_tac_toe.tic_tac_toe.service.GameHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameHistoryController {
    private final GameHistoryService gameHistoryService;

    public GameHistoryController(GameHistoryService service) {
        this.gameHistoryService = service;
    }

    @GetMapping("/player-summary")
    public ResponseEntity<PlayerSummaryDTO> playerSummary() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        PlayerSummaryDTO playerSummaryDTO = this.gameHistoryService.getPlayerSummary(username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerSummaryDTO);
    }
}
