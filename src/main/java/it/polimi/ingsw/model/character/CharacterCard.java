package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class CharacterCard {

    private final int idCharacter;
    private int costCharacter;
    private final CardEffect cardEffect;
    private boolean coinOnCard;
    private ArrayList<Student> studentsOnCard;
    private int xCardCounter = 0;
    private boolean playedMbriacone;
    private boolean playedCiccioPanza;
    private boolean playedAlzabandiera;

    public CharacterCard (int idCharacter, int costCharacter, CardEffect cardEffect){
        this.idCharacter = idCharacter;
        this.costCharacter = costCharacter;
        this.cardEffect = cardEffect;
        this.coinOnCard = false;


        this.playedCiccioPanza = false;

    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public int getCostCharacter() {
        return costCharacter;
    }

    public void setCostCharacter(int costCharacter) {
        this.costCharacter = costCharacter;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }

    public boolean getCoinOnCard() {    //o isCoinOnCard() ?
        return coinOnCard;
    }

    public void setCoinOnCard(boolean coinOnCard) {
        this.coinOnCard = coinOnCard;
    }

    public ArrayList<Student> getStudentsOnCard() {
        return studentsOnCard;
    }

    public void setStudentsOnCard(ArrayList<Student> studentsOnCard) {
        this.studentsOnCard = studentsOnCard;
    }

    public int getXCardCounter() {
        return xCardCounter;
    }

    public void setXCardCounter(int xCardCounter) {
        this.xCardCounter = xCardCounter;
    }
}
