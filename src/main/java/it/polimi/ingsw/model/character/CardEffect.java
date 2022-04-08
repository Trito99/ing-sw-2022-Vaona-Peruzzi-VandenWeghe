package it.polimi.ingsw.model.character;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public enum CardEffect {
    EFFECTONE,
    CICCIOPANZA,
    EFFECTTHREE,
    EFFECTFOUR,
    EFFECTFIVE,
    EFFECTSIX,
    EFFECTSEVEN,
    EFFECTEIGHT,
    EFFECTNINE,
    EFFECTTEN,
    EFFECTELEVEN,
    EFFECTTWELVE;



    public void playCiccioPanza(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players) {
        /** gestisce costo carta */
        if(card.isNeverUsed()){
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
            card.setNeverUsed(false);
        }
        else{
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
        }

        // prendi il controllo dei prof se hai lo stesso numero di studenti nella scuola
        int max = 0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        for (Player player : players) {
            if (player.getPersonalSchool().numberOfStudents(player, SColor.GREEN) > max) {
                max = player.getPersonalSchool().numberOfStudents(player, SColor.GREEN);
            } else player.getPersonalSchool().getProfOfPlayer().get(0).setInHall(false);
        }
        for (Player player : players) {
            if (player.getPersonalSchool().numberOfStudents(player, SColor.GREEN) == max) {
                playerWithMax++;
                maxPlayer = player;
            }
        }
        if (playerWithMax == 2) {
            maxPlayer.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true);
        }

        }


}





