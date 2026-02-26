import { useCallback, useEffect, useState } from "react";
import { Client, type IMessage } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import type { PlayerTypeEnum } from "../enums/PlayerTypeEnum";

const apiUrl = import.meta.env.VITE_API_URL;

const useGame = (playerId: string, playerType: PlayerTypeEnum): GameProps => {
  const [stompClient, setStompClient] = useState<Client | null>(null);
  const [gameData, setGameData] = useState<GameSession | null>(null);
  const [connected, setConnected] = useState(false);

  useEffect(() => {
    const socket = new SockJS(apiUrl);
    const client = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000,
      onConnect: () => {
        setConnected(true);

        client.subscribe(`/topic/match/${playerId}`, (message: IMessage) => {
          const session: GameSession = JSON.parse(message.body);
          setGameData(session);

          client.subscribe(`/topic/game/${session.gameId}`, (update: IMessage) => {
            const gameSession: GameSession = JSON.parse(update.body)
            setGameData(gameSession);

            if (gameSession.winner || gameSession.finished) {
              client.unsubscribe(`/topic/game/${gameSession.gameId}`);
            }
          });
        });
      },
      onDisconnect: () => setConnected(false),
    });

    client.activate();
    setStompClient(client);

    return () => {
      client.deactivate();
    };
  }, []);

  const findMatch = useCallback(() => {
    setGameData(null);
    stompClient?.publish({
      destination: "/app/matchmaking",
      body: JSON.stringify({
        playerId,
        playerType,
      }),
    });
  }, [stompClient]);

  const makeMove = useCallback(
    (row: number, column: number) => {
      const move: MoveRequest = { gameId: gameData!.gameId, row, column, playerType };
      stompClient?.publish({
        destination: "/app/move",
        body: JSON.stringify(move),
      });
    },
    [stompClient, gameData],
  );

  const cancelWaiting = useCallback(() => {
    stompClient?.publish({
      destination: "/app/cancel-waiting",
      body: playerId,
    });
  }, [stompClient]);

  const quitGame = useCallback(() => {
    stompClient?.publish({
      destination: "/app/quit-game",
      body: gameData?.gameId,
    });
  }, [stompClient, gameData]);

  return { findMatch, makeMove, cancelWaiting, quitGame, connected, gameData };
};

export default useGame;
