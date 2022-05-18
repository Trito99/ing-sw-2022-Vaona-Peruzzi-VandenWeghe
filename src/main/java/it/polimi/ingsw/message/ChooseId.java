package it.polimi.ingsw.message;


public class ChooseId extends ServerMessage {

    boolean choice;

    public ChooseId(boolean choice) {
        super(MessageType.CHOOSE_ID);
        this.choice = choice;
    }

    public boolean getChoice(){
        return choice;
    }
}
