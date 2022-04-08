package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

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

/*    EnumMap<SColor, Boolean> profMap = new EnumMap<>(SColor.class);
    profMap.put(SColor.GREEN, false);       */

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
    public int numberOfStudents(Player player, SColor color){
        switch(color){
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

    void winProf(ArrayList<Player> players, SColor color) {
        int max = 0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        switch (color){
            case GREEN:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.GREEN) > max) {
                        max = numberOfStudents(p, SColor.GREEN);
                    } else p.getPersonalSchool().setProfGInHall(false);
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.GREEN) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().setProfGInHall(true);
                }

            case RED:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.RED) > max) {
                        max = numberOfStudents(p, SColor.RED);
                    } else p.getPersonalSchool().setProfRInHall(false);
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.RED) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().setProfRInHall(true);
                }
            case YELLOW:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.YELLOW) > max) {
                        max = numberOfStudents(p, SColor.YELLOW);
                    } else p.getPersonalSchool().setProfYInHall(false);
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.YELLOW) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().setProfYInHall(true);
                }
            case PINK:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.PINK) > max) {
                        max = numberOfStudents(p, SColor.PINK);
                    } else p.getPersonalSchool().setProfPInHall(false);
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.PINK) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().setProfPInHall(true);
                }
            case BLUE:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.BLUE) > max) {
                        max = numberOfStudents(p, SColor.BLUE);
                    } else p.getPersonalSchool().getProfInHall(p, color.BLUE).get(4).setInHall(false);
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.BLUE) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().getProfOfPlayer().get(4).setInHall(true);
                }
        }
    }

    public boolean getProfInHall(Player player, SColor color){
        switch(color){
            case GREEN:
                return profGInHall;
            case RED:
                return profRInHall;
            case YELLOW:
                return profYInHall;
            case PINK:
                return profPInHall;
            case BLUE:
                return profBInHall;
        }

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

    public void addTower(int id, TColor tColor) {
        towerZone.add(new Tower(id, tColor));
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

