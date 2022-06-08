package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class IslandCard implements Serializable {

    private int idIsland;
    private ArrayList<Student> studentOnIsland ;
    private boolean towerIsOnIsland;
    private Tower towerOnIsland;
    private int mergedIsland; // = quante isole sono unite
    private boolean MotherEarthOnIsland = false;
    private boolean xCardOnIsland = false;
    private int xCardCounter = 0;

    public IslandCard(int idIsland) {
        this.idIsland = idIsland;
        studentOnIsland = new ArrayList<>();
        towerIsOnIsland = false;
        towerOnIsland = null;
        mergedIsland = 1;
    }

    public boolean getMotherEarthOnIsland(){ return MotherEarthOnIsland;}

    public void setMotherEarthOnIsland(boolean MotherEarthIsOnIsland){
        this.MotherEarthOnIsland = MotherEarthIsOnIsland;
    }

    public int getIdIsland() {
        return idIsland;
    }

    public void setIdIsland(int newId) {
         this.idIsland=newId;
    }

    public ArrayList<Student> getStudentOnIsland() {
        return studentOnIsland;
    }

    public boolean towerIsOnIsland() {
        return this.towerIsOnIsland;
    }

    public Tower getTowerOnIsland() {
        return towerOnIsland;
    }

    public void setTowerIsOnIsland(boolean towerIsOnIsland) {
        this.towerIsOnIsland = towerIsOnIsland;
    }

    public void setTowerOnIsland(Tower towerOnIsland){
        this.towerOnIsland = towerOnIsland;
    }

    /**
     *
     * @param listOfPlayer list of player of the match
     * @param cardEffectPlayed effect played in this turn
     * @param activePlayer player that is playing his turn
     * @param gameMode gameMode of the match (2,3 or 4 players)
     */
    public void buildTowerOnIsland(ArrayList<Player> listOfPlayer, CardEffect cardEffectPlayed, Player activePlayer, GameMode gameMode){

        if(cardEffectPlayed.equals(CardEffect.HERALD)) /** Herald effect */
            MotherEarthOnIsland=true;

        if(MotherEarthOnIsland) {

            if (gameMode == GameMode.COOP) {
                Team teamFound = calculateInfluenceCoop(listOfPlayer, cardEffectPlayed, activePlayer); /** Finds the player that will build the tower (null if it doesn't exists) */
                /** CURATOR: Checks that there aren't forbidden cards on the island */
                if (xCardOnIsland) {
                    setXCardCounter(getXCardCounter() - 1);         /** Curator effect */
                    if (xCardCounter == 0) setXCardOnIsland(false);
                } else {
                    if (teamFound != null) {
                        if (teamFound.getTeamLeader().getPersonalSchool().getTowers().size() != 0) {
                            if (!towerIsOnIsland) {
                                towerOnIsland = new Tower(teamFound.getTeamColor());
                                teamFound.getTeamLeader().getPersonalSchool().removeTower();
                                towerIsOnIsland = true;
                            } else {
                                if (!teamFound.getTeamColor().equals(towerOnIsland.getTColour())) {
                                    changeTowerColour(listOfPlayer, teamFound.getTeamLeader(), gameMode);
                                }
                            }
                        }
                    }
                }

            } else {
                Player playerFound = calculateInfluence(listOfPlayer, cardEffectPlayed, activePlayer); /** Finds the player that will build the tower (null if it doesn't exists) */
                /** CURATOR: Checks that there aren't forbidden cards on the island */
                if (xCardOnIsland) {
                    setXCardCounter(getXCardCounter() - 1);         /** Curator effect */
                    if (xCardCounter == 0) setXCardOnIsland(false);
                } else {
                    if (playerFound != null) {
                        if (playerFound.getPersonalSchool().getTowers().size() != 0) {
                            if (!towerIsOnIsland) {
                                towerOnIsland = new Tower(playerFound.getTColor());
                                playerFound.getPersonalSchool().removeTower();
                                towerIsOnIsland = true;
                            } else {
                                if (!playerFound.getTColor().equals(towerOnIsland.getTColour())) {
                                    changeTowerColour(listOfPlayer, playerFound, gameMode);
                                }
                            }
                        }
                    }
                }
            }
        }

        if(cardEffectPlayed.equals(CardEffect.HERALD))
            MotherEarthOnIsland=false;
    }

    /**
     *
     * @param listOfPlayers list of player of the match
     * @param playerBuilder the player that has influence and has to place a tower
     * @param gameMode gameMode of the match (2,3 or 4 players)
     */
    private void changeTowerColour(ArrayList<Player> listOfPlayers, Player playerBuilder, GameMode gameMode){        //cambio colore della torre se è cambiata l'influenza sull'isola
        Player prevPlayer = null; /** Player that has the towers on island before */
        Player teamLeader = null; /** The team leader is the player of the team that has the towers (Coop mode) */

        for(Player player : listOfPlayers){
            if(player.getTColor().equals(towerOnIsland.getTColour()))          /**determines the prevPlayer */
                prevPlayer = player;
        }

        if(gameMode==GameMode.COOP) {
            for (Player p : listOfPlayers) {
                if (playerBuilder.getPersonalSchool().getTowers().size() == 0 && playerBuilder.getTeamMate().equals(p.getNickname()))
                    teamLeader = p;
            }
            if (teamLeader==null)               /** Finds the team leader */
                teamLeader = playerBuilder;

            if(!teamLeader.getTColor().equals(towerOnIsland.getTColour())){
                for(int i=0;i<mergedIsland;i++) {
                    if(!teamLeader.getPersonalSchool().getTowers().isEmpty()) {
                        teamLeader.getPersonalSchool().getTowers().add(new Tower(prevPlayer.getTColor()));
                        teamLeader.getPersonalSchool().removeTower();
                    }
                }
                setTowerOnIsland(new Tower(teamLeader.getTColor()));
            }

        }
        else {
            if (!playerBuilder.getTColor().equals(towerOnIsland.getTColour())) {
                for (int i = 0; i < mergedIsland; i++) {
                    if (!playerBuilder.getPersonalSchool().getTowers().isEmpty()) {
                        prevPlayer.getPersonalSchool().getTowers().add(new Tower(prevPlayer.getTColor()));
                        playerBuilder.getPersonalSchool().removeTower();
                    }
                }
                setTowerOnIsland(new Tower(playerBuilder.getTColor()));
            }
        }
    }

    /**
     *
     * @param listOfPlayers list of player of the match
     * @param cardEffectPlayed effect played in this turn
     * @param activePlayer player that is playing his turn
     * @return the player with the influence on the island, return null if no one has influence or there is a draw.
     */
    public Player calculateInfluence(ArrayList<Player> listOfPlayers, CardEffect cardEffectPlayed, Player activePlayer){   //Restituisce il Player che ha influenza sull'isola
        int i ;
        int maxInfluence = 0;
        Player playerWithInfluence = null;

        /** calculate influence on island for all the players */
        for(Player p : listOfPlayers){
            int countTot = 0;
            for(i=0; i<studentOnIsland.size(); i++) {
                switch (studentOnIsland.get(i).getsColour()) {   /** checks student color */
                   /** if The player has the prof of the color of the student and the color isn't blocked (herbalist effect) increments the count of the influence */
                case GREEN:
                        if (p.getPersonalSchool().getProfInHall(SColor.GREEN) && !SColor.GREEN.isColorBlocked()) {
                            countTot++;
                        }
                        break;
                    case RED:
                        if (p.getPersonalSchool().getProfInHall(SColor.RED) && !SColor.RED.isColorBlocked()) {
                            countTot++;
                        }
                        break;
                    case YELLOW:
                        if (p.getPersonalSchool().getProfInHall(SColor.YELLOW) && !SColor.YELLOW.isColorBlocked()) {
                            countTot++;
                        }
                        break;
                    case PINK:
                        if (p.getPersonalSchool().getProfInHall(SColor.PINK) && !SColor.PINK.isColorBlocked()) {
                            countTot++;
                        }
                        break;
                    case BLUE:
                        if (p.getPersonalSchool().getProfInHall(SColor.BLUE) && !SColor.BLUE.isColorBlocked()) {
                            countTot++;
                        }
                        break;
                }

                /** Knight effect */
                if(cardEffectPlayed.isKnightPlayed() && p.equals(activePlayer)) {
                    p.setInfluenceOnIsland(countTot + 2);
                }
                else
                    p.setInfluenceOnIsland(countTot);
            }

            /** CENTAUR EFFECT */
            if(towerIsOnIsland) {
                if (p.getTColor().equals(towerOnIsland.getTColour()) && !cardEffectPlayed.isCentaurPlayed()) {  /** Adds influence of the towers if centaur effect isn't activated */
                    countTot += mergedIsland;
                    p.setInfluenceOnIsland(countTot);
                }
            }
        }

        for(SColor c : SColor.values()){            /** Disable Herbalist effect */
            if(c.isColorBlocked)
                c.unlockColor();
        }

        int count = 0;
        for(Player p : listOfPlayers) {
            if (p.getInfluenceOnIsland() > maxInfluence){
                maxInfluence = p.getInfluenceOnIsland();    /** finds the max value of the influence */
            }
        }

        for(Player p : listOfPlayers) {
            if (p.getInfluenceOnIsland() == maxInfluence){
                playerWithInfluence = p;                    /** update the player with the highest value of influence */
                count++;                                    /** counts the number of players that have the max value of influence */
            }
        }

        cardEffectPlayed.setKnightPlayed(false);            /** Disable Knight effect */
        cardEffectPlayed.setCentaurPlayed(false);           /** Disable Centaur effect */
        if (count==1)
            return playerWithInfluence;
        else
            return null;                                    /** if there is a draw, no one has influence on the island */

    }

    public Team calculateInfluenceCoop(ArrayList<Player> listOfPlayers, CardEffect cardEffectPlayed, Player activePlayer){   //Restituisce il Player che ha influenza sull'isola
        int i ;
        Team TeamWithInfluence = null;

        /** calculate influence on island for all the players */
        for(Player p : listOfPlayers){
            int countTotTeamWhite = 0;
            int countTotTeamBlack = 0;
            for(i=0; i<studentOnIsland.size(); i++) {
                switch (studentOnIsland.get(i).getsColour()) {   /** checks student color */
                    /** if The player has the prof of the color of the student and the color isn't blocked (herbalist effect) increments the count of the influence */
                    case GREEN:
                        if (p.getPersonalSchool().getProfInHall(SColor.GREEN) && !SColor.GREEN.isColorBlocked()) {
                            if(p.getTColor().equals(TColor.WHITE))
                                countTotTeamWhite++;
                            else
                                countTotTeamBlack++;
                        }
                        break;
                    case RED:
                        if (p.getPersonalSchool().getProfInHall(SColor.RED) && !SColor.RED.isColorBlocked()) {
                            if(p.getTColor().equals(TColor.WHITE))
                                countTotTeamWhite++;
                            else
                                countTotTeamBlack++;
                        }
                        break;
                    case YELLOW:
                        if (p.getPersonalSchool().getProfInHall(SColor.YELLOW) && !SColor.YELLOW.isColorBlocked()) {
                            if(p.getTColor().equals(TColor.WHITE))
                                countTotTeamWhite++;
                            else
                                countTotTeamBlack++;
                        }
                        break;
                    case PINK:
                        if (p.getPersonalSchool().getProfInHall(SColor.PINK) && !SColor.PINK.isColorBlocked()) {
                            if(p.getTColor().equals(TColor.WHITE))
                                countTotTeamWhite++;
                            else
                                countTotTeamBlack++;
                        }
                        break;
                    case BLUE:
                        if (p.getPersonalSchool().getProfInHall(SColor.BLUE) && !SColor.BLUE.isColorBlocked()) {
                            if(p.getTColor().equals(TColor.WHITE))
                                countTotTeamWhite++;
                            else
                                countTotTeamBlack++;
                        }
                        break;
                }

                /** Knight effect */
                if(cardEffectPlayed.isKnightPlayed() && p.equals(activePlayer)) {
                    if(p.getTColor().equals(TColor.WHITE))
                        p.setInfluenceOnIsland(countTotTeamWhite + 2);
                    else
                        p.setInfluenceOnIsland(countTotTeamBlack + 2);
                }
                else
                if(p.getTColor().equals(TColor.WHITE))
                    p.setInfluenceOnIsland(countTotTeamWhite);
                else
                    p.setInfluenceOnIsland(countTotTeamBlack);
            }

            /** CENTAUR EFFECT */
            if(towerIsOnIsland) {
                if (p.getTColor().equals(towerOnIsland.getTColour()) && !cardEffectPlayed.isCentaurPlayed()) {  /** Adds influence of the towers if centaur effect isn't activated */
                    if(p.getTColor().equals(TColor.WHITE)) {
                        countTotTeamWhite += mergedIsland;
                        p.setInfluenceOnIsland(countTotTeamWhite);
                    }
                    else {
                        countTotTeamBlack += mergedIsland;
                        p.setInfluenceOnIsland(countTotTeamBlack);
                    }

                }
            }
        }

        int teamWhiteInfluence = 0;
        int teamBlackInfluence = 0;
        for (Player p : listOfPlayers){
            if(p.getTColor().equals(TColor.WHITE))
                teamWhiteInfluence =+ p.getInfluenceOnIsland();
            else
                teamBlackInfluence =+ p.getInfluenceOnIsland();
        }

        for(SColor c : SColor.values()){            /** Disable Herbalist effect */
            if(c.isColorBlocked)
                c.unlockColor();
        }

        cardEffectPlayed.setKnightPlayed(false);            /** Disable Knight effect */
        cardEffectPlayed.setCentaurPlayed(false);           /** Disable Centaur effect */

        if(teamWhiteInfluence > teamBlackInfluence)
            return null; //white
        else if(teamWhiteInfluence < teamBlackInfluence)
            return null; //black
        else
            return null; /** if there is a draw, no one has influence on the island */




    }

    public int getMergedIsland() {
        return mergedIsland;
    }

    public void setMergedIsland(int mergedIsland) {
        this.mergedIsland = mergedIsland;
    }

    public boolean isXCardOnIsland() {
        return xCardOnIsland;
    }

    public int getXCardCounter() {
        return xCardCounter;
    }

    public void setXCardCounter(int xCardCounter) {
        this.xCardCounter = xCardCounter;
    }

    public void setXCardOnIsland(boolean xCardOnIsland) {
        this.xCardOnIsland = xCardOnIsland;
    }

}
