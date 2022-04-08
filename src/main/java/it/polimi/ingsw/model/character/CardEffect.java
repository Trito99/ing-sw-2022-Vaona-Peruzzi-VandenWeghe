package it.polimi.ingsw.model.character;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.table.Table;

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



    public void playCiccioPanza(Player player, GameController gc, Table table, CharacterCard card) {
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

        // prendi il controllo dei prof se hai stesso numero di studenti nella scuola

    }


}



