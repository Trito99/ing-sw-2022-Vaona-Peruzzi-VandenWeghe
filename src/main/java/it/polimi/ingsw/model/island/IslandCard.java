package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.school.TColor;

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


    public Player calculateInfluence(ArrayList<Player> listOfPlayers, Player playerTurn){   //Restituisce il Player che ha influenza sull'isola
        int i ;
        int maxInfluence = 0;
        Player playerWithInfluence = null;


        for(Player players : listOfPlayers){
            int countTot = 0;

            for(i=0; i<studentOnIsland.size(); i++){
                switch (studentOnIsland.get(i).getsColour()){   /** guardo colore studente */
                    case GREEN:
                        if(players.getPersonalSchool().getProfInHall(SColor.GREEN)){ /** se ho il prof verde e stud è verde incremento influenza */
                            countTot++;
                        }
                        break;
                    case RED:
                        if(players.getPersonalSchool().getProfInHall(SColor.RED)){
                            countTot++;
                        }
                        break;
                    case YELLOW:
                        if(players.getPersonalSchool().getProfInHall(SColor.YELLOW)){
                            countTot++;
                        }
                        break;
                    case PINK:
                        if(players.getPersonalSchool().getProfInHall(SColor.PINK)){
                            countTot++;
                        }
                        break;
                    case BLUE:
                        if(players.getPersonalSchool().getProfInHall(SColor.BLUE)){
                            countTot++;
                        }
                        break;
                }
                /** EFFETTO SILVIO */
                if(playerTurn==players && playerTurn.isSilvioPlayed()) {
                    players.setInfluenceOnIsland(countTot + 2);
                    playerTurn.setSilvioPlayed(false);      /** controlla  se va bene qua */
                }
                else
                    players.setInfluenceOnIsland(countTot);
            }
        }

        for(Player players : listOfPlayers) {
            if (players.getInfluenceOnIsland() > maxInfluence){
                maxInfluence = players.getInfluenceOnIsland();
            }
        }

        for(Player players : listOfPlayers) {
            if (players.getInfluenceOnIsland() == maxInfluence){
                playerWithInfluence = players;
            }
        }

        return playerWithInfluence;         /** Controllo Pareggio Influenza ----> return null? */
    }

    public void buildTowerOnIsland(ArrayList<Player> listOfPlayer){        //Builda la torre del colore del Player che ha l'influenza sull'isola

        Player playerFound = calculateInfluence(listOfPlayer);  //Player che ha influenza sull'isola

        if(playerFound==null){
            return;             /** Se nessuno ha influenza non buildo */
        }

        TColor towerColour = playerFound.getTColour();      //Colore delle torri del player che ha influenza

        towerOnIsland = new Tower(playerFound.getPersonalSchool().getTower().size(), towerColour);
        playerFound.getPersonalSchool().removeTower();

        setTowerIsOnIsland(true);
    }

    public void changeTowerColour(ArrayList<Player> listOfPlayers){        //cambio colore della torre se è cambiata l'influenza sull'isola

        Player prevPlayer = null;
        Player playerBuilder = calculateInfluence(listOfPlayers);

        if(playerBuilder==null){
            return;             /** Se nessuno ha influenza non cambia il colore */
        }

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
