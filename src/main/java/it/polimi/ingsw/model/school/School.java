package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents School's board
 * Composed by entry, student's hall, professor's hall and tower zone
 */
public class School implements Serializable {

    private ArrayList<Student> entry;
    private ArrayList<Student> GTable;
    private ArrayList<Student> RTable;
    private ArrayList<Student> YTable;
    private ArrayList<Student> PTable;
    private ArrayList<Student> BTable;
    private ArrayList<Tower> towerZone;
    private ArrayList<Prof> profOfPlayer;

    /**
     * Default constructor
     */
    public School() {
        entry = new ArrayList<>();
        GTable = new ArrayList<>();
        RTable = new ArrayList<>();
        YTable = new ArrayList<>();
        PTable = new ArrayList<>();
        BTable = new ArrayList<>();
        towerZone = new ArrayList<>();
        profOfPlayer = new ArrayList<>();
    }

    public ArrayList<Student> getEntry() {
        return entry;
    }

    public ArrayList<Prof> getProfOfPlayer(){
        return profOfPlayer;
    }

    public ArrayList<Student> getGTable(){
        return GTable;
    }

    public ArrayList<Student> getRTable(){
        return RTable;
    }

    public ArrayList<Student> getYTable(){
        return  YTable;
    }

    public ArrayList<Student> getPTable(){
        return  PTable;
    }

    public ArrayList<Student> getBTable(){
        return  BTable;
    }

    /**
     * Moves the students from the Cloud Card selected to the entry of the player's personal school
     * @param cloudCard chosen by the player
     */
    public void moveStudentFromCloudToEntry(CloudCard cloudCard){
        entry.addAll(cloudCard.getStudentOnCloud());
        cloudCard.getStudentOnCloud().clear();
    }

    /**
     * Returns the number of students in the hall of each student's color
     * @param color of the hall where we want to count the students
     * @return the number of students in the hall selected
     */
    public int numberOfStudentsInHall(SColor color){
        switch(color){
            case GREEN:
                return GTable.size();
            case RED:
                return RTable.size();
            case YELLOW:
                return YTable.size();
            case PINK:
                return PTable.size();
            case BLUE:
                return BTable.size();
        }
        return 0;
    }

    /**
     * Assigns professors to the player with the highest number of students in the hall of a certain student's color
     * @param players of the match
     * @param playerTurn player that is playing
     * @param cardEffectPlayed card effect played in the turn
     */
    public void winProf(ArrayList<Player> players, Player playerTurn, CardEffect cardEffectPlayed) {

        for (int i=0;i<SColor.values().length;i++) {
            int max = 0;
            int playerWithMax = 0;
            Player maxPlayer = null;
            boolean activePlayerHasMax = false;

            switch (SColor.values()[i]) {
                case GREEN:
                    for (Player p : players) {                         /** find the highest number of green students between all the tables*/
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.GREEN) > max) {
                            max = p.getPersonalSchool().numberOfStudentsInHall(SColor.GREEN);
                        }
                    }
                    if (playerTurn.getPersonalSchool().numberOfStudentsInHall(SColor.GREEN) == max)
                        activePlayerHasMax = true;

                    for (Player p : players) {                          /** find how many players has the max number of green students */
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.GREEN) == max) {
                            playerWithMax++;
                            maxPlayer = p;      /** when a player has the max value of green I update maxPlayer*/
                        } else p.getPersonalSchool().getProfOfPlayer().get(0).setInHall(false);
                    }
                    if (playerWithMax == 1) {                                               /** ---> No draw */
                        maxPlayer.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true);
                    } else if (playerWithMax > 1) {                                         /** Draw */
                        for (Player p : players)
                            p.getPersonalSchool().getProfOfPlayer().get(0).setInHall(false);
                        if (cardEffectPlayed.isHostPlayed() && activePlayerHasMax){
                            playerTurn.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true);        /** Draw, but Host effect is played */
                        }
                    }
                    break;
                case RED:
                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.RED) > max) {
                            max = p.getPersonalSchool().numberOfStudentsInHall(SColor.RED);
                        }
                    }

                    if (playerTurn.getPersonalSchool().numberOfStudentsInHall(SColor.RED) == max)
                        activePlayerHasMax = true;

                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.RED) == max) {
                            playerWithMax++;
                            maxPlayer = p;
                        } else p.getPersonalSchool().getProfOfPlayer().get(1).setInHall(false);
                    }
                    if (playerWithMax == 1) {
                        maxPlayer.getPersonalSchool().getProfOfPlayer().get(1).setInHall(true);

                    } else if (playerWithMax > 1) {
                        for (Player p : players)
                            p.getPersonalSchool().getProfOfPlayer().get(1).setInHall(false);
                        if (cardEffectPlayed.isHostPlayed() && activePlayerHasMax){
                            playerTurn.getPersonalSchool().getProfOfPlayer().get(1).setInHall(true);
                        }
                    }
                    break;
                case YELLOW:
                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.YELLOW) > max) {
                            max = p.getPersonalSchool().numberOfStudentsInHall(SColor.YELLOW);
                        }
                    }

                    if (playerTurn.getPersonalSchool().numberOfStudentsInHall(SColor.YELLOW) == max)
                        activePlayerHasMax = true;

                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.YELLOW) == max) {
                            playerWithMax++;
                            maxPlayer = p;
                        } else p.getPersonalSchool().getProfOfPlayer().get(2).setInHall(false);
                    }
                    if (playerWithMax == 1) {
                        maxPlayer.getPersonalSchool().getProfOfPlayer().get(2).setInHall(true);
                    } else if (playerWithMax > 1) {
                        for (Player p : players)
                            p.getPersonalSchool().getProfOfPlayer().get(2).setInHall(false);
                        if (cardEffectPlayed.isHostPlayed() && activePlayerHasMax){
                            playerTurn.getPersonalSchool().getProfOfPlayer().get(2).setInHall(true);
                        }
                    }
                    break;
                case PINK:
                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.PINK) > max) {
                            max = p.getPersonalSchool().numberOfStudentsInHall(SColor.PINK);
                        }
                    }

                    if (playerTurn.getPersonalSchool().numberOfStudentsInHall(SColor.PINK) == max)
                        activePlayerHasMax = true;

                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.PINK) == max) {
                            playerWithMax++;
                            maxPlayer = p;
                        } else p.getPersonalSchool().getProfOfPlayer().get(3).setInHall(false);
                    }
                    if (playerWithMax == 1) {
                        maxPlayer.getPersonalSchool().getProfOfPlayer().get(3).setInHall(true);
                    } else if (playerWithMax > 1) {
                        for (Player p : players)
                            p.getPersonalSchool().getProfOfPlayer().get(3).setInHall(false);
                        if (cardEffectPlayed.isHostPlayed() && activePlayerHasMax){
                            playerTurn.getPersonalSchool().getProfOfPlayer().get(3).setInHall(true);
                        }
                    }
                    break;
                case BLUE:
                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.BLUE) > max) {
                            max = p.getPersonalSchool().numberOfStudentsInHall(SColor.BLUE);
                        }
                    }

                    if (playerTurn.getPersonalSchool().numberOfStudentsInHall(SColor.BLUE) == max)
                        activePlayerHasMax = true;

                    for (Player p : players) {
                        if (p.getPersonalSchool().numberOfStudentsInHall(SColor.BLUE) == max) {
                            playerWithMax++;
                            maxPlayer = p;
                        } else p.getPersonalSchool().getProfOfPlayer().get(4).setInHall(false);
                    }
                    if (playerWithMax == 1) {
                        maxPlayer.getPersonalSchool().getProfOfPlayer().get(4).setInHall(true);
                    } else if (playerWithMax > 1) {
                        for (Player p : players)
                            p.getPersonalSchool().getProfOfPlayer().get(4).setInHall(false);
                        if (cardEffectPlayed.isHostPlayed() && activePlayerHasMax){
                            playerTurn.getPersonalSchool().getProfOfPlayer().get(4).setInHall(true);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Verifies if the player wins a Professor or not
     * @param color of the Professor
     * @return true if there is the prof of color "color" in the school, otherwise return false
     */
    public boolean getProfInHall(SColor color){
        for(Prof p : profOfPlayer) {
            if (p.getSColour().equals(color))
                return p.getIsInHall();
        }
        return false;
    }

    public ArrayList<Tower> getTowers() {
        return towerZone;
    }

    public void addTower(TColor tColor) {
        towerZone.add(new Tower(tColor));
    }

    /**
     * Removes the last tower from the towerZone of the school
     */
    public void removeTower() {
        towerZone.remove(towerZone.size() - 1);
    }

    /**
     * Counts the number of Professors in a certain school
     * @return the number of prof currently in the school
     */
    public int numberOfProf(){
        int countProf = 0;

        for (Prof prof : profOfPlayer){
            if(prof.getIsInHall())
                countProf++;
        }
        return countProf;
    }

}

