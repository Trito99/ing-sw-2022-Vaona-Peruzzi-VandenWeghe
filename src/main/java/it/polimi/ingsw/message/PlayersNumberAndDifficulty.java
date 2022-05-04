package it.polimi.ingsw.message;

import it.polimi.ingsw.model.game.Difficulty;

public class PlayersNumberAndDifficulty extends ClientMessage {
    private int playersNumber;
    private Difficulty difficulty;

    public PlayersNumberAndDifficulty(String nickname, int playersNumber, Difficulty difficulty) {
        super(nickname, MessageType.PLAYERS_NUMBER_AND_DIFFICULTY);
        this.playersNumber=playersNumber;
        this.difficulty=difficulty;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public Difficulty getDifficulty() { return difficulty; }
}
