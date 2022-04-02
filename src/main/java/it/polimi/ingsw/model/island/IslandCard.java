package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.school.TColour;

import java.util.ArrayList;

public class IslandCard {

    private int idIsland;
    private ArrayList<Student> studentOnIsland ;
    private boolean towerIsOnIsland;
    private Tower towerOnIsland;
    private int mergedIsland;        // = quante isole sono unite

    public IslandCard(int idIsland) {
        this.idIsland = idIsland;
        studentOnIsland = new ArrayList<>();
        towerIsOnIsland = false;
        towerOnIsland = null;
        mergedIsland = 1;
    }


    public int getIdIsland() {
        return idIsland;
    }

    public ArrayList<Student> getStudentOnIsland() {
        return studentOnIsland;
    }

    public boolean TowerIsOnIsland() {
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


    public Player calculateInfluence(){   //Restituisce playerNumber del Player che ha influenza sull'isola?

    return Player;
    }

    public void buildTowerOnIsland(){        //Builda la torre del colore del Player che ha l'influenza sull'isola??

        Player player = calculateInfluence();           //Player che ha influenza sull'isola
        TColour towerColour = player.getTColour();      //Colore delle torri del player che ha influenza

        towerOnIsland = new Tower(player.getPersonalSchool().lastTowerAvailable(), towerColour);
        player.getPersonalSchool().removeTower();

        setTowerIsOnIsland(true);
    }

    public TColour changeTowerColour(){

        return TColour;
    }

    public int getMergeIsland() {
        return mergedIsland;
    }

    public void setMergeIsland(int mergeIsland) {
        this.mergedIsland = mergeIsland;
    }
}
