package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.school.TColor;

public class TowerColorAndDeckChosen extends ClientMessage {
    private TColor towerColor;
    private AssistantDeckName assistantDeckName;

    public TowerColorAndDeckChosen(String nickname, TColor towerColor, AssistantDeckName assistantDeckName) {
        super(nickname, MessageType.TOWER_COLOR_AND_DECK_CHOSEN);
        this.towerColor = towerColor;
        this.assistantDeckName = assistantDeckName;
    }

    public TColor getTowerColor() { return  towerColor; }
    public AssistantDeckName getAssistantDeckName() { return assistantDeckName; }
}
