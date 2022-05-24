package it.polimi.ingsw.message;


import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class ChooseId extends ServerMessage {

    private boolean choice;
    private CharacterCard characterCard;
    private int index;
    private ArrayList<Student> entry;

    public ChooseId(boolean choice, CharacterCard characterCard, int index, ArrayList<Student> entry) {
        super(MessageType.CHOOSE_ID);
        this.choice = choice;
        this.characterCard = characterCard;
        this.index = index;
        this.entry = entry;
    }

    public CharacterCard getCharacterCard(){
        return characterCard;
    }

    public boolean getChoice(){
        return choice;
    }

    public int getIndex(){ return index; }

    public ArrayList<Student> getEntry() {return entry;}
}
