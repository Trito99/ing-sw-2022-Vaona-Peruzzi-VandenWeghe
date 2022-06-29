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

/**
 * Represents the Island Card
 */

public class IslandCard implements Serializable {
    private int idIsland;
    private int immutableIdIsland;
    private ArrayList<Student> studentOnIsland ;
    private boolean towerIsOnIsland;
    private Tower towerOnIsland;
    private int mergedIsland;
    private boolean MotherEarthOnIsland = false;

    private ArrayList<Integer> listOfMinorIslands;
    private boolean xCardOnIsland = false;
    private int xCardCounter = 0;

    /**
     * default constructor
     * @param idIsland id of a specific island, used to identify itself
     */
    public IslandCard(int idIsland) {
        this.idIsland = idIsland;
        this.immutableIdIsland = idIsland;
        studentOnIsland = new ArrayList<>();
        towerIsOnIsland = false;
        towerOnIsland = null;
        mergedIsland = 1;
        listOfMinorIslands = new ArrayList<>();
        listOfMinorIslands.add(idIsland);
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
     * Builds tower when a player has the influence on the island where is placed Mother Earth
     * Also handles Curator's effect (character card)
     * @param listOfPlayer list of player of the match
     * @param cardEffectPlayed effect played in this turn
     * @param activePlayer player that is playing his turn
     * @param gameMode gameMode of the match (2,3 or 4 players)
     */
    public void buildTowerOnIsland(ArrayList<Player> listOfPlayer, CardEffect cardEffectPlayed, Player activePlayer, GameMode gameMode, ArrayList<Team> teams){

        if(cardEffectPlayed.equals(CardEffect.HERALD)) /** Herald effect */
            MotherEarthOnIsland = true;

        if(MotherEarthOnIsland) {
            Player playerFound;
            /** Finds the player that will build the tower (null if it doesn't exists) */
            if(gameMode.equals(GameMode.COOP))
                playerFound = calculateInfluenceCoop(listOfPlayer, cardEffectPlayed, activePlayer, teams, gameMode);
            else
                playerFound = calculateInfluence(listOfPlayer, cardEffectPlayed, activePlayer, gameMode);
            /** CURATOR: Checks that there aren't forbidden cards on the island */
            if (xCardOnIsland) {
                setXCardCounter(getXCardCounter() - 1);         /** Curator effect */
                if (xCardCounter == 0) setXCardOnIsland(false);
            } else {
                if (playerFound != null) {
                    if (playerFound.getPersonalSchool().getTowers().size() != 0) {
                        /** Build tower on island */
                        if (!towerIsOnIsland) {
                            towerOnIsland = new Tower(playerFound.getTColor());
                            playerFound.getPersonalSchool().removeTower();
                            towerIsOnIsland = true;
                        } else {
                            /** if already present check if the tower has to change color or not */
                            if (!playerFound.getTColor().equals(towerOnIsland.getTColour())) {
                                changeTowerColour(listOfPlayer, playerFound);
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
     * Checks if the tower present has to change color or not
     * @param listOfPlayers list of player of the match
     * @param playerBuilder the player that has influence and has to place a tower
     */
    private void changeTowerColour(ArrayList<Player> listOfPlayers, Player playerBuilder){        //cambio colore della torre se è cambiata l'influenza sull'isola
        Player prevPlayer = null; /** Player that has the towers on island before */

        for(Player player : listOfPlayers){
            if(player.getTColor().equals(towerOnIsland.getTColour()))          /**determines the prevPlayer */
                prevPlayer = player;
        }

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

    /**
     * Returns the player with the maximum influence on a certain island
     * @param listOfPlayers list of player of the match
     * @param cardEffectPlayed effect played in this turn
     * @param activePlayer player that is playing his turn
     * @return the player with the influence on the island,
     *         null if no one has influence or there is a draw.
     */
    public Player calculateInfluence(ArrayList<Player> listOfPlayers, CardEffect cardEffectPlayed, Player activePlayer, GameMode gameMode){   //Restituisce il Player che ha influenza sull'isola
        int i ;
        int maxInfluence = 0;
        Player playerWithInfluence = null;

        /** islands with 0 students case */
        if(studentOnIsland.isEmpty() && !cardEffectPlayed.isKnightPlayed())
            return null;
        else if(studentOnIsland.isEmpty() && cardEffectPlayed.isKnightPlayed()){
            for(Player player : listOfPlayers){
                if(player.equals(activePlayer)){
                    player.setInfluenceOnIsland(2);
                    return player;
                }

            }
        }

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

            }

            /** KNIGHT EFFECT */
            if(cardEffectPlayed.isKnightPlayed() && p.equals(activePlayer)) {
                p.setInfluenceOnIsland(countTot + 2);
            }
            else
                p.setInfluenceOnIsland(countTot);

            /** CENTAUR EFFECT */
            if(towerIsOnIsland) {
                if (p.getTColor().equals(towerOnIsland.getTColour()) && !cardEffectPlayed.isCentaurPlayed()) {
                    /** Adds influence of the towers if centaur effect isn't activated */
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

        if(!gameMode.equals(GameMode.COOP)) {
            for (SColor c : SColor.values()) {            /** Disable Herbalist effect */
                if (c.isColorBlocked)
                    c.unlockColor();
            }

            cardEffectPlayed.setKnightPlayed(false);            /** Disable Knight effect */
            cardEffectPlayed.setCentaurPlayed(false);           /** Disable Centaur effect */
        }

        if (count==1)
            return playerWithInfluence;
        else
            return null;                                    /** if there is a draw, no one has influence on the island */

    }

    /**
     * Returns the player with the maximum influence on the island in COOP mode (4 players mode)
     * @param listOfPlayers list of player of the match
     * @param cardEffectPlayed effect played in this turn
     * @param activePlayer player that is playing his turn
     * @param teams of the match
     * @param gameMode of the match
     * @return the player with the influence on the island, return null if no one has influence or there is a draw.
     */
    public Player calculateInfluenceCoop(ArrayList<Player> listOfPlayers, CardEffect cardEffectPlayed, Player activePlayer, ArrayList<Team> teams, GameMode gameMode) {   //Restituisce il Team Leader che ha influenza sull'isola
        int inflTot = 0;

        if (calculateInfluence(listOfPlayers, cardEffectPlayed, activePlayer, gameMode) == null) {
            for (Player p : listOfPlayers)
                inflTot += p.getInfluenceOnIsland();
            /** case no students on island */
            if (inflTot == 0)
                return null;
        }

        int influenceWhite = 0, influenceBlack = 0;
        Team teamWhite = null, teamBlack = null;
        for (Team t : teams) {
            int influenceTeam = 0;
            for (Player player : t.getTeam()) {
                influenceTeam += player.getInfluenceOnIsland();
            }
            if (towerIsOnIsland && !cardEffectPlayed.isCentaurPlayed()) {
                if (towerOnIsland.getTColour().equals(t.getTeamColor()))
                    influenceTeam = influenceTeam - mergedIsland;  /** The amount of towers are added two times (one for each player of the team) so it has to be removed once*/
            }
            if (t.getTeamColor().equals(TColor.WHITE)) {
                influenceWhite = influenceTeam;
                teamWhite = t;
            } else {
                influenceBlack = influenceTeam;
                teamBlack = t;
            }
        }

        for (SColor c : SColor.values()) {            /** Disable Herbalist effect */
        if (c.isColorBlocked)
            c.unlockColor();
        }

        cardEffectPlayed.setKnightPlayed(false);            /** Disable Knight effect */
        cardEffectPlayed.setCentaurPlayed(false);           /** Disable Centaur effect */

        if (influenceWhite > influenceBlack)
            return teamWhite.getTeamLeader();
        else if (influenceWhite < influenceBlack)
            return teamBlack.getTeamLeader();
        else
            return null;

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

    public ArrayList<Integer> getListOfMinorIslands() {
        return listOfMinorIslands;
    }
    public int getImmutableIdIsland() {
        return immutableIdIsland;
    }
}
