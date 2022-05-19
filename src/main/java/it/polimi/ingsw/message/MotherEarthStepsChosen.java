package it.polimi.ingsw.message;


public class MotherEarthStepsChosen extends ClientMessage{
    private int steps, maxSteps;

    public MotherEarthStepsChosen(String nickname, int steps, int maxSteps) {
        super(nickname, MessageType.STEP_MOTHER_EARTH_CHOSEN);
        this.steps = steps;
        this.maxSteps = maxSteps;
    }

    public int getSteps() {
        return steps;
    }
    public int getMaxSteps() {return maxSteps;}
}
