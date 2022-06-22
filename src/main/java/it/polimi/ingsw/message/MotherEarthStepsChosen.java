package it.polimi.ingsw.message;


public class MotherEarthStepsChosen extends ClientMessage{
    private int steps, maxSteps;
    private String string;

    public MotherEarthStepsChosen(String nickname, int steps, int maxSteps, String string) {
        super(nickname, MessageType.STEP_MOTHER_EARTH_CHOSEN);
        this.steps = steps;
        this.maxSteps = maxSteps;
        this.string = string;
    }

    public int getSteps() {
        return steps;
    }
    public int getMaxSteps() {return maxSteps;}

    public String getString() {
        return string;
    }
}
