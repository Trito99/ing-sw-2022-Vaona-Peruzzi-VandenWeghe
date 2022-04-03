package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.school.TColour;
import it.polimi.ingsw.model.game.GameMode;

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


    public Player calculateInfluence(){   //Restituisce playerNumber del Player che ha influenza sull'isola?

    return Player;
    }

    public void buildTowerOnIsland(){        //Builda la torre del colore del Player che ha l'influenza sull'isola

        Player player = calculateInfluence();           //Player che ha influenza sull'isola
        TColour towerColour = player.getTColour();      //Colore delle torri del player che ha influenza

        towerOnIsland = new Tower(player.getPersonalSchool().getTower().size(), towerColour);
        player.getPersonalSchool().removeTower();

        setTowerIsOnIsland(true);
    }

    public void changeTowerColour(){        //cambio colore della torre se è cambiata l'influenza sull'isola

        Player prevPlayer = null;
        Player playerBuilder = calculateInfluence();

        for(Player player : Game.getListOfPlayer()){
            if(player.getTColour().equals(towerOnIsland.getTColour()))          //determina il prevPlayer
                prevPlayer = player;
        }

        if(playerBuilder.getTColour().equals(towerOnIsland.getTColour())) {     //
            return;
        }
        else{
            setTowerOnIsland(playerBuilder.getPersonalSchool().getTower().get(playerBuilder.getPersonalSchool().getTower().size() - 1));
            prevPlayer.getPersonalSchool().addTower(playerBuilder.getPersonalSchool().getTower().size(), playerBuilder.getTColour());
        }
    }

    public int getMergedIsland() {
        return mergedIsland;
    }

    public void setMergedIsland(int mergedIsland) {
        this.mergedIsland = mergedIsland;
    }
}
