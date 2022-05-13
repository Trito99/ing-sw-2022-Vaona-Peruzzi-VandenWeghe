package it.polimi.ingsw.message;


public class MotherEarthStepsChosen extends ClientMessage{
    private int steps;

    public MotherEarthStepsChosen(String nickname, int steps) {
        super(nickname, MessageType.STEP_MOTHER_EARTH_CHOSEN);
        this.steps=steps;
    }

    public int getSteps() {
        return steps;
    }
}
