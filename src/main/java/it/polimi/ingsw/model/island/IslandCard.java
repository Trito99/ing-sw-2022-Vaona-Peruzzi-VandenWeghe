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
    private int mergedIsland; // = quante isole sono unite
    private boolean MotherEarthOnIsland = false;

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


    public Player calculateInfluence(Player player, ArrayList<Player> listOfPlayers){   //Restituisce il Player che ha influenza sull'isola
        int i ;
        int maxInfluence = 0;
        Player playerWithInfluence = null;


        for(Player players : listOfPlayers){
            int countTot = 0;

            for(i=0; i<studentOnIsland.size(); i++){
                switch (studentOnIsland.get(i).getsColour()){
                    case GREEN:
                        if(player.getPersonalSchool().getProfGInHall()){
                            countTot++;
                        }
                        break;
                    case RED:
                        if(player.getPersonalSchool().getProfRInHall()){
                            countTot++;
                        }
                        break;
                    case YELLOW:
                        if(player.getPersonalSchool().getProfYInHall()){
                            countTot++;
                        }
                        break;
                    case PINK:
                        if(player.getPersonalSchool().getProfPInHall()){
                            countTot++;
                        }
                        break;
                    case BLUE:
                        if(player.getPersonalSchool().getProfBInHall()){
                            countTot++;
                        }
                        break;
                }
                player.setInfluenceOnIsland(countTot);
            }
        }

        for(Player players : listOfPlayers) {
            if (player.getInfluenceOnIsland() > maxInfluence){
                maxInfluence = player.getInfluenceOnIsland();
            }
        }

        for(Player player : listOfPlayers) {
            if (player.getInfluenceOnIsland() == maxInfluence){
                playerWithInfluence = player;
            }
        }

        return playerWithInfluence;
    }

    public void buildTowerOnIsland(Player player, ArrayList<Player> listOfPlayer){        //Builda la torre del colore del Player che ha l'influenza sull'isola

        Player playerFonud = calculateInfluence(player, listOfPlayer);           //Player che ha influenza sull'isola
        TColour towerColour = player.getTColour();      //Colore delle torri del player che ha influenza

        towerOnIsland = new Tower(player.getPersonalSchool().getTower().size(), towerColour);
        player.getPersonalSchool().removeTower();

        setTowerIsOnIsland(true);
    }

    public void changeTowerColour(ArrayList<Player> listOfPlayers){        //cambio colore della torre se è cambiata l'influenza sull'isola

        Player prevPlayer = null;
        Player playerBuilder = calculateInfluence();

        for(Player player : listOfPlayers){
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
