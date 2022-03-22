package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.school.TColour;
import it.polimi.ingsw.model.player.PlayerNumber;

import java.util.ArrayList;

public class IslandCard {

    private int idIsland;
    private ArrayList<Student> studentOnIsland = new ArrayList<>();;
    private boolean towerIsOnIsland;
    private TColour towerOnIsland;

    public IslandCard(int idIsland, TColour towerOnIsland) {
        this.idIsland = idIsland;
        this.towerOnIsland = null;
        this.studentOnIsland = null;
    }


    public int getIdIsland() {
        return this.idIsland;
    }

    public ArrayList<Student> getStudentOnIsland() {
        return this.studentOnIsland;
    }

    public boolean TowerIsOnIsland() {
        return this.towerIsOnIsland;
    }

    public TColour getTowerOnIsland() {
        return this.towerOnIsland;
    }

    public void setTowerIsOnIsland(boolean towerIsOnIsland) {
        this.towerIsOnIsland = towerIsOnIsland;
    }

    public ArrayList<Student> moveStudentInIsland(int id, SColour sColour){
        ArrayList<Student> studentOnIsland = School.getEntry();
        studentOnIsland.add(new Student(id, sColour));

        // RICORDA!!! devi togliere student da School
    }

    public PlayerNumber calculateInfluence(){

    }

    public TColour buildTowerOnIsland(){

    }
}
