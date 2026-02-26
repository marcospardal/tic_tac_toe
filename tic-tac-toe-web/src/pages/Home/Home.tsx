import React, { useState } from "react";
import { PlayerTypeEnum } from "../../enums/PlayerTypeEnum";
import { useNavigate } from "react-router-dom";

const Home: React.FC = () => {
  const navigate = useNavigate();
  const [playerName, setPlayerName] = useState<string>("");
  const [playerType, setPlayerType] = useState<PlayerTypeEnum>();

  return (
    <>
      <div>

        <input placeholder="Insert player id" value={playerName} onChange={(e) => setPlayerName(e.target.value)} />
        <div>
          <button onClick={() => setPlayerType(PlayerTypeEnum.X)}>X</button>
          <button onClick={() => setPlayerType(PlayerTypeEnum.O)}>O</button>
        </div>
      </div>
      <ul>
        <li onClick={() => navigate(`/game`, { state: { playerName, playerType } })}>Play game</li>
      </ul>
    </>
  )
}

export default Home;