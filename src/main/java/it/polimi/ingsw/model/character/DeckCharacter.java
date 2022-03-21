package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards;

    public DeckCharacter(ArrayList<CharacterCard> characterCards){
        this.characterCards = new ArrayList<>(12);
    }

    public ArrayList<CharacterCard> getAssistantCard(){
        return this.characterCards;
    }

    public ArrayList<CharacterCard> drawCard(){    //Pesca tre carte

        Difficulty difficulty = Game.getDifficulty();
        Table table = Table.getCharacterCardsOnTable();

        if(difficulty.equals(Difficulty.EXPERTMODE)){
            for (int i = characterCards.size(); i <= characterCards.size()-3; i--) {           //DA RIVEDERE
                table.addCharacterCard(int idCharacter, int costCharacter,CardEffect cardEffect)
                characterCards[i].remove(new CharacterCard(int idCharacter, int costCharacter,CardEffect cardEffect));
            }
        }

    }

    public ArrayList<CharacterCard> shuffleCard(){

    }

}
