package it.polimi.ingsw.model.character;

import it.polimi.ingsw.controller.GameController;
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


    public void playMbriacone(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 1);
            table.increaseCoinsOnTable(1);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
        }

        //card.setMbriacone(true);

        /** effetto */

    }

    public void playCiccioPanza(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players) {
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
        }

        card.setPlayedCiccioPanza(true);

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

    public void playAlzabandiera(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 4);
            table.increaseCoinsOnTable(4);
        }

        //card.setAlzabandiera(true);

        /** effetto */

    }

    public void playCepostaperte(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 1);
            table.increaseCoinsOnTable(1);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
        }

        //card.setCepostaperte(true);

        /** effetto */

    }

    public void playSciura(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
        }

        //card.setSciura(true);

        /** effetto */

    }

    public void playTauro(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 4);
            table.increaseCoinsOnTable(4);
        }

        //card.setTauro(true);

        /** effetto */

    }

    public void playJoker(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 1);
            table.increaseCoinsOnTable(1);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
        }

        //card.setJoker(true);

        /** effetto */

    }

    public void playSilvio(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
        }

        //card.setSilvio(true);

        /** effetto */

    }

    public void playFungaiolo(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 4);
            table.increaseCoinsOnTable(4);
        }

        //card.setFungaiolo(true);

        /** effetto */

    }

    public void playMenestrello(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 1);
            table.increaseCoinsOnTable(1);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
        }

        //card.setMenestrello(true);

        /** effetto */

    }

    public void playDama(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 2);
            table.increaseCoinsOnTable(2);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
        }

        //card.setDama(true);

        /** effetto */

    }

    public void playTossico(Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        /** gestisce costo carta */
        if (card.isNeverUsed()) {
            gc.decreaseCoinScore(player, 3);
            table.increaseCoinsOnTable(3);
            card.setNeverUsed(false);
        } else {
            gc.decreaseCoinScore(player, 4);
            table.increaseCoinsOnTable(4);
        }

        //card.setTossico(true);

        /** effetto */

    }
}





