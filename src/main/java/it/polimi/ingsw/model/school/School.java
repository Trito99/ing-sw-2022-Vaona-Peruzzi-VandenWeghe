package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class School {

    private ArrayList<Student> entry;
    private ArrayList<Student> GTable;
    private ArrayList<Student> RTable;
    private ArrayList<Student> YTable;
    private ArrayList<Student> PTable;
    private ArrayList<Student> BTable;
    private boolean profGInHall;
    private boolean profRInHall;
    private boolean profYInHall;
    private boolean profPInHall;
    private boolean profBInHall;
    private ArrayList<Tower> towerZone;
    private ArrayList<Prof> profOfPlayer;

    public School(ArrayList<Prof> profOfPlayer) {

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

    public void moveStudentToIsland(IslandCard islandCard, int id){ //Specifico Studente va spostato (sceglie player)
        Student student = new Student(131,null);
        for(int i = 0; i < entry.size(); i++) {
            if(id==entry.get(i).getIdStudent())
                student = entry.get(i);
        }
        islandCard.getStudentOnIsland().add(entry.get(entry.indexOf(student)));
        entry.remove(entry.get(entry.indexOf(student)));
    }

    public void moveStudentInHall(int id) {
        Student student = new Student(131, null);
        for (int i = 0; i < entry.size(); i++) {
            if (id == entry.get(i).getIdStudent())
                student=entry.get(i);
        }
        switch(student.getsColour()){
            case GREEN:
                GTable.add(student);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case RED:
                RTable.add(student);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case YELLOW:
                YTable.add(student);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case PINK:
                PTable.add(student);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case BLUE:
                BTable.add(student);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
        }
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
        return YTable;
    }

    public ArrayList<Student> getPTable(){
        return PTable;
    }

    public ArrayList<Student> getBTable(){
        return BTable;
    }

    /** da generalizzare tutto con questo */
    public int numberOfStudents(Player player, SColour colour){
        switch(colour){
            case GREEN:
                return player.getPersonalSchool().getGTable().size();
            case RED:
                return player.getPersonalSchool().getRTable().size();
            case YELLOW:
                return player.getPersonalSchool().getYTable().size();
            case PINK:
                return player.getPersonalSchool().getPTable().size();
            case BLUE:
                return player.getPersonalSchool().getBTable().size();
        }
        return 0;
    }

    public int numberOfR(Player player){
        return player.getPersonalSchool().getRTable().size();
    }

    public int numberOfY(Player player){
        return player.getPersonalSchool().getYTable().size();
    }

    public int numberOfP(Player player){
        return player.getPersonalSchool().getPTable().size();
    }

    public int numberOfB(Player player){
        return player.getPersonalSchool().getBTable().size();
    }

    public void winProfG(ArrayList<Player> players) {
        //calcolo se player che sta giocando conquista il prof giallo
        // numero di studenti gialli del player "selezionato"
        int max=0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        /** Prof Giallo */
        /* for (Player player : players) {
            for (SColour colour : SColour.values()){
                if (numberOfStudents(player, colour) > max) {
                    max = numberOfStudents(player, colour);
                }
                else player.getPersonalSchool().getProfOfPlayer().get(0).setInHall(false);
                max = 0;
            }
        }

        for (Player player : players) {
            for (SColour colour : SColour.values()){
                if(numberOfStudents(player, colour) == max){
                    playerWithMax ++;
                    maxPlayer = player;
                }
                if (playerWithMax == 1){
                    maxPlayer.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true);
                }
            }
        } */
        for (Player player : players) {
            if (numberOfG(player) > max) {
                max = numberOfG(player);
            }
            else player.getPersonalSchool().setProfRInHall(false);
        }

        for (Player player : players) {
            if(numberOfG(player) == max){
                playerWithMax ++;
                maxPlayer = player;
            }
        }
        if (playerWithMax == 1){
            maxPlayer.getPersonalSchool().setProfGInHall(true);
        }
    }

    public void winProfR(ArrayList<Player> players) {
        int max=0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        for (Player player : players) {
            if (numberOfR(player) > max) {
                max = numberOfR(player);
            }
            else player.getPersonalSchool().setProfRInHall(false);
        }

        for (Player player : players) {
            if(numberOfR(player) == max){
                playerWithMax ++;
                maxPlayer = player;
            }
        }
        if (playerWithMax == 1){
            maxPlayer.getPersonalSchool().setProfRInHall(true);
        }
    }

    public void winProfY(ArrayList<Player> players) {
        int max=0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        for (Player player : players) {
            if (numberOfY(player) > max) {
                max = numberOfY(player);
            }
            else player.getPersonalSchool().setProfYInHall(false);
        }

        for (Player player : players) {
            if(numberOfY(player) == max){
                playerWithMax ++;
                maxPlayer = player;
            }
        }
        if (playerWithMax == 1){
            maxPlayer.getPersonalSchool().setProfYInHall(true);
        }
    }

    public void winProfP(ArrayList<Player> players) {
        int max=0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        for (Player player : players) {
            if (numberOfP(player) > max) {
                max = numberOfP(player);
            }
            else player.getPersonalSchool().setProfPInHall(false);
        }

        for (Player player : players) {
            if(numberOfP(player) == max){
                playerWithMax ++;
                maxPlayer = player;
            }
        }
        if (playerWithMax == 1){
            maxPlayer.getPersonalSchool().setProfPInHall(true);
        }
    }

    public void winProfB(ArrayList<Player> players) {
        int max = 0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        for (Player player : players) {
            if (numberOfB(player) > max) {
                max = numberOfB(player);
            } else player.getPersonalSchool().setProfBInHall(false);
        }

        for (Player player : players) {
            if (numberOfB(player) == max) {
                playerWithMax++;
                maxPlayer = player;
            }
        }
        if (playerWithMax == 1) {
            maxPlayer.getPersonalSchool().setProfBInHall(true);
        }
    }

    public boolean getProfGInHall(){
        return profGInHall;
    }

    public boolean getProfRInHall(){
        return profRInHall;
    }

    public boolean getProfYInHall(){
        return profYInHall;
    }

    public boolean getProfPInHall(){
        return profPInHall;
    }

    public boolean getProfBInHall(){
        return profBInHall;
    }

    public boolean setProfGInHall(boolean profGInHall){
        return this.profGInHall = profGInHall;
    }

    public boolean setProfRInHall(boolean profRInHall){
        return this.profRInHall = profRInHall;
    }

    public boolean setProfYInHall(boolean profYInHall){
       return this.profYInHall = profYInHall;
    }

    public boolean setProfPInHall(boolean profPInHall){
        return this.profPInHall = profPInHall;
    }

    public boolean setProfBInHall(boolean profBInHall){
        return this.profBInHall = profBInHall;
    }

    public ArrayList<Tower> getTower() {
        return towerZone;
    }

    public void addTower(int id, TColour tColour) {
        towerZone.add(new Tower(id,tColour));
        // ci sarà una notify observer
    }

    public void removeTower() {
        towerZone.remove(towerZone.size() - 1);
    }

    public int numberOfProf(){
        int countProf = 0;

        if(profGInHall == true) countProf++;
        else if(profBInHall == true) countProf++;
        else if(profPInHall == true) countProf++;
        else if(profRInHall == true) countProf++;
        else if(profYInHall == true) countProf++;

        return countProf;
    }

   /* public boolean checkTowerIsEmpty() {     //  (Ricorda: se non ci sono Tower in TowerZone finisce la partita)
        return towerZone.toArray().length != 0;
    } */

}

