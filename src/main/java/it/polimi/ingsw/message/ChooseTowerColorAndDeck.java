package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class ChooseTowerColorAndDeck extends ServerMessage {
    private ArrayList<TColor> towerColors;
    private ArrayList<AssistantDeckName> assistantDeckNames;

    public ChooseTowerColorAndDeck(ArrayList<TColor> towerColors, ArrayList<AssistantDeckName> assistantDeckNames) {
        super(MessageType.CHOOSE_TOWER_COLOR_AND_DECK);
        this.towerColors = towerColors;
        this.assistantDeckNames = assistantDeckNames;
    }

    public ArrayList<TColor> getTowerColors(){
        return towerColors;
    }

    public ArrayList<AssistantDeckName> getAssistantDeckNames(){
        return assistantDeckNames;
    }

}
