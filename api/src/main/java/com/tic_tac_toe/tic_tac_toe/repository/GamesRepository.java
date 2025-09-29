package com.tic_tac_toe.tic_tac_toe.repository;

import com.tic_tac_toe.tic_tac_toe.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GamesRepository extends JpaRepository<GameEntity, Long> {
    @Query("SELECT g FROM GameEntity g WHERE g.player1Id = :userId OR g.player2Id = :userId")
    List<GameEntity> findUserGames(Long userId);
}
