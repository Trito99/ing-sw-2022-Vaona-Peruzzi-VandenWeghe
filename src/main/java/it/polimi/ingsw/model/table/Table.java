package it.polimi.ingsw.model.table;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.TColour;

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

    public Player playerIsWinning(){  //calcola influenza torri sul tavolo e restituisce quello con più influenza
        int countGrey = 0;
        int countWhite = 0;
        int countBlack = 0;
        Player winner = null;
        Player alsoWinner = null ;

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
        if (countBlack > countGrey && countBlack > countWhite){
            for(Player player : Game.getListOfPlayer()){
                if(player.getTColour().equals(TColour.BLACK)){
                    return winner = player;
                }
            }
        }

        else if(countGrey > countBlack && countGrey > countWhite){
                for(Player player : Game.getListOfPlayer()) {
                    if (player.getTColour().equals(TColour.BLACK)) {
                        return winner = player;
                    }
                }
        }

        else if(countWhite > countBlack && countWhite > countGrey){
            for(Player player : Game.getListOfPlayer()) {
                if (player.getTColour().equals(TColour.BLACK)) {
                    return winner = player;
                }
            }
        }

        /** in caso di parità, confronto i player e vince quello con più prof */
        else if(countBlack == countGrey && countBlack > countWhite){
            for(Player player : Game.getListOfPlayer()) {
                if (player.getTColour().equals(TColour.BLACK)) {
                    winner = player;
                }
                else if(player.getTColour().equals(TColour.GREY)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return winner = alsoWinner;
            /** METTERE CASO PAREGGIO PROF ??? */
        }

        else if(countGrey == countWhite && countGrey > countBlack){
            for(Player player : Game.getListOfPlayer()) {
                if (player.getTColour().equals(TColour.GREY)) {
                    winner = player;
                }
                else if(player.getTColour().equals(TColour.WHITE)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return winner = alsoWinner;
        }

        else if(countWhite == countBlack && countWhite > countGrey){
            for(Player player : Game.getListOfPlayer()) {
                if (player.getTColour().equals(TColour.WHITE)) {
                    winner = player;
                }
                else if(player.getTColour().equals(TColour.BLACK)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return winner = alsoWinner;
        }

        return winner;
    }

}
