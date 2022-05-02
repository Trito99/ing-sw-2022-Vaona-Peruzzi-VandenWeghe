package it.polimi.ingsw.message;

import it.polimi.ingsw.model.game.GameMode;

public class GameModeChoosen extends ClientMessage{
    private GameMode gm;

    public GameModeChoosen(GameMode gameMode) {
        super(MessageType.GAME_MODE);
        this.gm = gameMode;
    }

    public GameMode getGameMode() {
        return gm;
    }
}
