type PlayerType = "X" | "O";

interface GameSession {
  gameId: string;
  currentTurn: PlayerType;
  board: PlayerType[][];
  winner: PlayerType;
  finished: boolean;
}

interface MoveRequest {
  gameId: string;
  row: number;
  column: number;
  playerType: PlayerType;
}

type MakeMoveFn = (row: number, column: number) => void;

type GameProps = {
  findMatch: () => void;
  makeMove: MakeMoveFn;
  quitGame: () => void;
  cancelWaiting: () => void;
  connected: boolean;
  gameData: GameSession | null;
}