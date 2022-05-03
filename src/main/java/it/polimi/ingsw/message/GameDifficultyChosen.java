package it.polimi.ingsw.message;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;

public class GameDifficultyChosen extends ClientMessage{
    private Difficulty difficulty;

    public GameDifficultyChosen(Difficulty difficulty) {
        super(MessageType.GAME_DIFFICULTY);
        this.difficulty = difficulty;
    }

    public Difficulty getGameDifficulty() {
        return difficulty;
    }
}
