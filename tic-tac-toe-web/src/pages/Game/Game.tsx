import { useEffect } from "react";
import useGame from "../../hooks/useGame";
import { useLocation, useNavigate } from "react-router-dom";
import type { PlayerTypeEnum } from "../../enums/PlayerTypeEnum";

const Game: React.FC = () => {
  const { state } = useLocation();
  const navigate = useNavigate();
  const { playerName, playerType } = state;

  const { connected, findMatch, gameData, makeMove, cancelWaiting, quitGame } = useGame(playerName, playerType);

  useEffect(() => {
    if (connected) {
      findMatch();
    }
  }, [connected]);

  const playAgain = () => <button onClick={findMatch}>Play again</button>;

  if (!gameData?.gameId)
    return (
      <div>
        <div>Fiding match...</div>
        <button
          onClick={() => {
            cancelWaiting();
            navigate("/");
          }}
        >
          cancel
        </button>
      </div>
    );
  if (gameData.finished)
    return (
      <div>
        Your opponent has left! <br />
        You win <br />
        {playAgain()}
      </div>
    );
  if (gameData.currentTurn == null)
    return (
      <div>
        <p>You {playerType === gameData.winner ? "Win" : "Lost"}</p>
        {playAgain()}
      </div>
    );
  else
    return (
      <div>
        <Board {...gameData} playerType={state.playerType} makeMove={makeMove} />
        <button
          onClick={() => {
            quitGame();
            navigate("/");
          }}
        >
          Quit game
        </button>
      </div>
    );
};

const Board: React.FC<GameSession & { playerType: PlayerTypeEnum; makeMove: MakeMoveFn }> = ({
  board,
  currentTurn,
  makeMove,
  playerType,
}) => {
  return (
    <div>
      <p>It's {playerType === currentTurn ? "your" : "opponent's"} turn</p>
      {board.map((row, rowIndex) => (
        <div style={{ display: "flex" }}>
          {row.map((rowColumn, rowColumnIndex) => (
            <button
              style={{
                borderRight: rowColumnIndex < 2 ? "1px #000 solid" : undefined,
                borderBottom: "1px solid #000",
                height: "40px",
                width: "40px",
              }}
              onClick={() => (currentTurn === playerType && !rowColumn ? makeMove(rowIndex, rowColumnIndex) : null)}
            >
              {rowColumn || " "}
            </button>
          ))}
        </div>
      ))}
    </div>
  );
};

export default Game;
