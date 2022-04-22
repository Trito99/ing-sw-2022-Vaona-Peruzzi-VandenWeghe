package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class CharacterCard {

    private int costCharacter;
    private final CardEffect cardEffect;
    private boolean coinOnCard;
    private ArrayList<Student> studentsOnCard = new ArrayList<>();

    public CharacterCard (int costCharacter, CardEffect cardEffect){
        this.costCharacter = costCharacter;
        this.cardEffect = cardEffect;
        this.coinOnCard = false;

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

}
