package com.tic_tac_toe.tic_tac_toe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "games")
@Data
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "player_1_id")
    private Long player1Id;

    @Column(name = "player_2_id")
    private Long player2Id;

    @Column(name = "winner_id")
    private Long winnerId;

    @Column(name = "game_duration")
    private Integer gameDuration;
}
