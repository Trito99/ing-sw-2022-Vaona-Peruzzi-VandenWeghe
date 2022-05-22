package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.school.TColor;

import java.io.Serializable;
import java.util.ArrayList;

public class IslandCard implements Serializable {

    private int idIsland;
    private ArrayList<Student> studentOnIsland ;
    private boolean towerIsOnIsland;
    private Tower towerOnIsland;
    private int mergedIsland; // = quante isole sono unite
    private boolean MotherEarthOnIsland = false;
    private boolean xCardOnIsland = false;
    private int xCardCounter = 0;

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

    public void buildTowerOnIsland(ArrayList<Player> listOfPlayer,  CardEffect cardEffectPlayed, Player activePlayer){
        if(cardEffectPlayed.equals(CardEffect.HERALD))
            MotherEarthOnIsland=true;

        if(MotherEarthOnIsland) {
            Player playerFound = calculateInfluence(listOfPlayer, cardEffectPlayed, activePlayer);

            /** CURATRICE: controllo che non ci sia una tessera divieto sull'isola */
            if (xCardOnIsland) {
                setXCardCounter(getXCardCounter() - 1);
                if (xCardCounter == 0) setXCardOnIsland(false);
            } else {
                if (playerFound != null) {
                    if (playerFound.getPersonalSchool().getTower().size() != 0) {
                        if (!towerIsOnIsland) {
                            towerOnIsland = new Tower(playerFound.getTColor());
                            playerFound.getPersonalSchool().removeTower();
                            towerIsOnIsland = true;
                        }else{
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

    private void changeTowerColour(ArrayList<Player> listOfPlayers, Player playerBuilder){        //cambio colore della torre se è cambiata l'influenza sull'isola

        Player prevPlayer = null;

        for(Player player : listOfPlayers){
            if(player.getTColor().equals(towerOnIsland.getTColour()))          //determina il prevPlayer
                prevPlayer = player;
        }
        if(!playerBuilder.getTColor().equals(towerOnIsland.getTColour())){
            for(int i=0;i<mergedIsland;i++) {
                if(!playerBuilder.getPersonalSchool().getTower().isEmpty()) {
                    prevPlayer.getPersonalSchool().getTower().add(new Tower(prevPlayer.getTColor()));
                    playerBuilder.getPersonalSchool().removeTower();
                }
            }
            setTowerOnIsland(new Tower(playerBuilder.getTColor()));
        }
    }

    public Player calculateInfluence(ArrayList<Player> listOfPlayers, CardEffect cardEffectPlayed, Player activePlayer){   //Restituisce il Player che ha influenza sull'isola
        int i ;
        int maxInfluence = 0;
        Player playerWithInfluence = null;

        /** calcolo influenza sull'isola */
        for(Player p : listOfPlayers){
            int countTot = 0;
            for(i=0; i<studentOnIsland.size(); i++) {
                switch (studentOnIsland.get(i).getsColour()) {   /** guardo colore studente */
                    case GREEN:
                        if (p.getPersonalSchool().getProfInHall(SColor.GREEN) && !SColor.GREEN.isColorBlocked()) { /** se ho il prof verde e stud è verde incremento influenza */
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

                /** EFFETTO CAVALIERE */
                if(cardEffectPlayed.isKnightPlayed() && p.equals(activePlayer)) {
                    p.setInfluenceOnIsland(countTot + 2);
                }
                else
                    p.setInfluenceOnIsland(countTot);
            }

            /** EFFETTO CENTAURO */
            if(towerIsOnIsland) {
                if (p.getTColor().equals(towerOnIsland.getTColour()) && !cardEffectPlayed.isCentaurPlayed()) {  /** Aggiungo influenza torri */
                    countTot=countTot+mergedIsland;
                }
            }

            for(SColor c : SColor.values()){            /** controlla se va bene qua (Erborista) */
                if(c.isColorBlocked)
                    c.unlockColor();
            }
        }
        int count=0;
        for(Player p : listOfPlayers) {
            if (p.getInfluenceOnIsland() > maxInfluence){
                maxInfluence = p.getInfluenceOnIsland();
            }
        }

        for(Player p : listOfPlayers) {
            if (p.getInfluenceOnIsland() == maxInfluence){
                playerWithInfluence = p;
                count++;
            }
        }

        cardEffectPlayed.setKnightPlayed(false);
        cardEffectPlayed.setCentaurPlayed(false);
        if (count==1)
            return playerWithInfluence;
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

}
