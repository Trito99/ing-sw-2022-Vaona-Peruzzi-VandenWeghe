package it.polimi.ingsw.message;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.table.Table;


public class ChooseMotherEarthSteps extends  ServerMessage {
    private int maxSteps;
    private Table table;
    private Difficulty difficulty;

    public ChooseMotherEarthSteps(int maxSteps, Table table, Difficulty difficulty){
        super(MessageType.CHOOSE_MOTHER_EARTH_STEPS);
        this.maxSteps = maxSteps;
        this.table = table;
        this.difficulty = difficulty;
    }

    public int getMaxSteps(){
        return maxSteps;
    }

    public Table getTable() {
        return table;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
