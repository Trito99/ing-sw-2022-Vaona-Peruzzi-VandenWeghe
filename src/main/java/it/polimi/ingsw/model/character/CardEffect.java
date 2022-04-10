package it.polimi.ingsw.model.character;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public enum CardEffect {
    MBRIACONE,
    CICCIOPANZA,
    ALZABANDIERA,
    CEPOSTAPERTE,
    SCIURA,
    TAURO,
    JOKER,
    SILVIO,
    FUNGAIOLO,
    MENESTRELLO,
    DAMA,
    TOSSICO;

    private int xCardOnSciura = 0;


    public void playMbriacone(Player player){
        player.setMbriaconePlayed(true);
    }

    public void playCiccioPanza(Player player) {
        player.setCiccioPanzaPlayed(true);
    }

    public void playAlzabandiera(Player player, ArrayList<Player> listOfPlayers, IslandCard islandChosen){

        ArrayList<Player> playersList= new ArrayList<>(listOfPlayers);

        islandChosen.calculateInfluence(playersList, player);
        islandChosen.buildTowerOnIsland(playersList);
        islandChosen.changeTowerColour(playersList);
    }

    public void playCepostaperte(Player player){
        player.setCePostaPerTePlayed(true);
    }

    public void playSciura(Player player, IslandCard islandChosen){

        islandChosen.setXCardOnIsland(true);
        if(islandChosen.getXCardCounter() < 4 && SCIURA.xCardOnSciura > 0){
            islandChosen.setXCardCounter(islandChosen.getXCardCounter() + 1);
        }
        else
            System.out.println(" Non puoi!!!");

        /** effetto */

    }

    public void playTauro(Player player){
        player.setTauroPlayed(true);
    }

    public void playJoker(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){



        //card.setJoker(true);

        /** effetto */

    }

    public void playSilvio(Player player){
        player.setSilvioPlayed(true);
    }

    public void playFungaiolo(Player player, SColor colorChosen){
        colorChosen.lockColor();
    }

    public void playDama(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){


        //card.setDama(true);

        /** effetto */

    }

    public void playTossico(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){


        //card.setTossico(true);

        /** effetto */

    }

    public void setXCardOnSciura(int xCardOnSciura) {
        this.xCardOnSciura = xCardOnSciura;
    }
}





