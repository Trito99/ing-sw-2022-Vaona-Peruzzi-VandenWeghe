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
    private ArrayList<Tower> tower;
    private ArrayList<Prof> profOfPlayer = null;

    public School(ArrayList<Prof> profOfPlayer) {
        Game gameMode = Game.getGameMode();
        assert gameMode != null;
        if (gameMode.equals(GameMode.THREEPLAYERS))
            entry = new ArrayList<>(9);
        else entry = new ArrayList<>(7);

        GTable = new ArrayList<>();
        RTable = new ArrayList<>();
        YTable = new ArrayList<>();
        PTable = new ArrayList<>();
        BTable = new ArrayList<>();
        tower = new ArrayList<>();
    }

    public ArrayList<Student> getEntry() {
        return this.entry;
    }

    public void moveStudentToIsland(School school, IslandCard islandCard, int id){ //Specifico Studente va spostato (sceglie player)
        Student student = new Student(131,null);
        for(int i = 0; i < school.getEntry().size(); i++) {
            if(id==school.getEntry().get(i).getIdStudent())
                student = school.getEntry().get(i);
        }
        islandCard.getStudentOnIsland().add(school.getEntry().get(school.getEntry().indexOf(student)));
        school.getEntry().remove(school.getEntry().get(school.getEntry().indexOf(student)));
    }

    public ArrayList<Student> moveStudentInHall(int id, SColour sColour){    //DA RIGUARDARE!!
        if(sColour == SColour.GREEN) GTable.add(new Student(id, sColour));
        else if(sColour == SColour.RED) RTable.add(new Student(id, sColour));
        else if(sColour == SColour.YELLOW) YTable.add(new Student(id,sColour));
        else if(sColour == SColour.PINK) PTable.add(new Student(id, sColour));
        else if(sColour == SColour.BLUE) BTable.add(new Student(id, sColour));
        entry.remove(new Student(id,sColour));   // da verificare il "new"!!!!!
    }

    public ArrayList<Student> getGTable(){
        return this.GTable;
    }

    public ArrayList<Student> getRTable(){
        return this.RTable;
    }

    public ArrayList<Student> getYTable(){
        return this.YTable;
    }

    public ArrayList<Student> getPTable(){
        return this.PTable;
    }

    public ArrayList<Student> getBTable(){
        return this.BTable;
    }

    public int numberOfG(Player player){
        return player.getPersonalSchool().getGTable().size();
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

        for (Player player : players) {
            if (numberOfG(player) > max) {
                max = numberOfG(player);
            }
            else player.getPersonalSchool().setProfGInHall(false);
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

    public boolean winProfB(ArrayList<Player> players) {
        int max=0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        for (Player player : players) {
            if (numberOfB(player) > max) {
                max = numberOfB(player);
            }
            else player.getPersonalSchool().setProfBInHall(false);
        }

        for (Player player : players) {
            if(numberOfB(player) == max){
                playerWithMax ++;
                maxPlayer = player;
            }
        }
        if (playerWithMax == 1){
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
        return this.tower;
    }

    public void addTower(int id, TColour tColour) {
        tower.add(new Tower(id,tColour));
        // ci sarà una notify observer
    }

    public void removeTower(int id, TColour tColour) {
        tower.remove(new Tower(id,tColour)); // da verificare il "new"
    }

    public boolean checkTowerIsEmpty() {     //  (Ricorda: se non ci sono Tower in TowerZone finisce la partita)
        return tower.toArray().length != 0;
    }

}
