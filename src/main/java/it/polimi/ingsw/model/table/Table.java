package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.bag.Bag;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColour;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.Collections;

public class Table {

    private ArrayList<CloudCard> cloudNumber;
    private ArrayList<IslandCard> listOfIsland;
    private ArrayList<CharacterCard> characterCardsOnTable;
    private int coinsOnTable;


    public void generateCloudNumber(Table table,GameMode gm){
        int x;
        int maxNumberOfStudents;

        if(gm.equals(GameMode.TWOPLAYERS)){
            x=2;
            maxNumberOfStudents = 3;
        }else if(gm.equals(GameMode.THREEPLAYERS)){
            x=3;
            maxNumberOfStudents = 4;
        }else{
            x=4;
            maxNumberOfStudents = 3;
        }
        for(int i=0;i<x;i++){
            table.getCloudNumber().add(new CloudCard(i, maxNumberOfStudents));
        }
    }

    public void generateIslandCards(Table table){
        for(int s=1;s<13;s++) {
            table.getListOfIsland().add(new IslandCard(s));
        }
    }

    public ArrayList<CloudCard> getCloudNumber() {
        return cloudNumber;
    }

    public ArrayList<IslandCard> getListOfIsland() {
        return listOfIsland;
    }

    public int getCoinsOnTable() {
        return coinsOnTable;
    }

    public ArrayList<CharacterCard> getCharacterCardsOnTable() {
        return characterCardsOnTable;
    }

    public ArrayList<IslandCard> joinIsland(){
        //da fare

    }

    /* public boolean checkListOfIsland(){  // Se ListOfIsland=3, finisce la partita?
        if(listOfIsland.size()==3)
            return true;
    } */

    public Player playerIsWinning(IslandCard islandCard, Player player){  //calcola influenza torri sul tavolo e restituisce quello con più influenza
        int countGrey = 0;
        int countWhite = 0;
        int countBlack = 0;
        Player winner = null;
        Player alsoWinner = null ;

        listOfIsland = getListOfIsland();

        /** conto il numero di torri presenti sul tavolo per ogni colore */
        for (int s=1;s<13;s++){
            if((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColour.GREY)){
                countGrey++;
            }
            else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColour.WHITE)){
                countWhite++;
            }
            else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColour.BLACK)){
                countBlack++;
            }
        }

        /** confronto e cerco chi ha maggior influenza */
        if (countBlack > countGrey){
            winner.equals(player.getTColour().BLACK);
            return winner;
        }
        else if(countBlack > countWhite){
            winner.equals(player.getTColour().BLACK);
            return winner;
        }
        else if(countGrey > countBlack){
            winner.equals(player.getTColour().GREY);
            return winner;
        }
        else if(countGrey > countWhite){
            winner.equals(player.getTColour().GREY);
            return winner;
        }
        else if(countWhite > countBlack){
            winner.equals(player.getTColour().WHITE);
            return winner;
        }
        else if(countWhite > countGrey){
            winner.equals(player.getTColour().WHITE);
            return winner;
        }

        /** in caso di parità, confronto i player e vince quello con più prof */
        else if(countBlack == countGrey){
            winner.equals(player.getTColour().BLACK);
            alsoWinner.equals(player.getTColour().GREY);
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return alsoWinner;
        }

        else if(countGrey == countWhite){
            winner.equals(player.getTColour().GREY);
            alsoWinner.equals(player.getTColour().WHITE);
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return alsoWinner;
        }

        else if(countWhite == countBlack){
            winner.equals(player.getTColour().WHITE);
            alsoWinner.equals(player.getTColour().BLACK);
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return alsoWinner;
        }

    }

}
