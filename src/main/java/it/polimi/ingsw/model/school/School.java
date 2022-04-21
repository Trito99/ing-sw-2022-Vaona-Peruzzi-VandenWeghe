package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public class School {

    private ArrayList<Student> entry;
    private ArrayList<Student> GTable;
    private ArrayList<Student> RTable;
    private ArrayList<Student> YTable;
    private ArrayList<Student> PTable;
    private ArrayList<Student> BTable;
    private ArrayList<Tower> towerZone;
    private ArrayList<Prof> profOfPlayer;

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

    public void moveStudentToIsland(IslandCard islandCard, int id){ //Specifico Studente va spostato (sceglie player)
        Student student = new Student(131,null);
        for (Student s : entry) {
            if (id == s.getIdStudent())
                student = s;
        }
        islandCard.getStudentOnIsland().add(entry.get(entry.indexOf(student)));
        entry.remove(entry.get(entry.indexOf(student)));
    }

    public void moveStudentInHall(Player playerMoving, int id, Table table, Difficulty difficulty) {
        Student student = new Student(131, null);
        for (Student s : entry) {
            if (id == s.getIdStudent())
                student = s;
        }
        switch(student.getsColour()) {
            case GREEN:
                GTable.add(student);
                getCoinFromStudentMove(playerMoving, table, difficulty);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case RED:
                RTable.add(student);
                getCoinFromStudentMove(playerMoving, table, difficulty);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case YELLOW:
                YTable.add(student);
                getCoinFromStudentMove(playerMoving, table, difficulty);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case PINK:
                PTable.add(student);
                getCoinFromStudentMove(playerMoving, table, difficulty);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
            case BLUE:
                BTable.add(student);
                getCoinFromStudentMove(playerMoving, table, difficulty);
                entry.remove(entry.get(entry.indexOf(student)));
                break;
        }
    }

    private void getCoinFromStudentMove(Player playerMoving, Table table, Difficulty difficulty) {
        if(difficulty.equals(Difficulty.EXPERTMODE) && (GTable.size()==3)){
            playerMoving.setCoinScore(playerMoving.getCoinScore() + 1);
            table.setCoinsOnTable(table.getCoinsOnTable() - 1);
        }
        else if(difficulty.equals(Difficulty.EXPERTMODE) && (GTable.size()==6)){
            playerMoving.setCoinScore(playerMoving.getCoinScore() + 1);
            table.setCoinsOnTable(table.getCoinsOnTable() - 1);
        }
        else if(difficulty.equals(Difficulty.EXPERTMODE) && (GTable.size()==9)){
            playerMoving.setCoinScore(playerMoving.getCoinScore() + 1);
            table.setCoinsOnTable(table.getCoinsOnTable() - 1);
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
        return  YTable;
    }

    public ArrayList<Student> getPTable(){
        return  PTable;
    }

    public ArrayList<Student> getBTable(){
        return  BTable;
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

    void winProf(ArrayList<Player> players, Player playerTurn, CardEffect cardEffectPlayed, SColor color) {
        int max = 0;
        int playerWithMax = 0;
        Player maxPlayer = null;

        switch (color){
            case GREEN:
                for (Player p : players) {                         /** In questo for trovo max numero di verdi generale*/
                    if (numberOfStudents(p, SColor.GREEN) > max) {
                        max = numberOfStudents(p, SColor.GREEN);
                    }
                }
                for (Player p : players) {                          /** In questo for conto i giocatori che hanno il max numero di verdi*/
                    if (numberOfStudents(p, SColor.GREEN) == max) {
                        playerWithMax++;
                        maxPlayer = p;      /** Se è solo uno lo salvo in maxPlayer*/
                    }else p.getPersonalSchool().getProfOfPlayer().get(0).setInHall(false);
                }
                if (playerWithMax == 1) {   /** Setto a true il prof verde del maxplayer */
                    maxPlayer.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true);
                }else if(playerWithMax > 1 && cardEffectPlayed.isOstePlayed()){       /** Se ho più giocatori in pareggio, setto a true solo il prof di chi ha l'effetto di CiccioPanza*/
                    playerTurn.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true);
                }else{
                    for(Player p : players)
                        p.getPersonalSchool().getProfOfPlayer().get(0).setInHall(false);
                }


            case RED:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.RED) > max) {
                        max = numberOfStudents(p, SColor.RED);
                    }
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.RED) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    } else p.getPersonalSchool().getProfOfPlayer().get(1).setInHall(false);
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().getProfOfPlayer().get(1).setInHall(true);
                }
                else if(playerWithMax > 1 && cardEffectPlayed.isOstePlayed()){ /**Se ho più giocatori in pareggio, setto a true solo il prof di chi ha l'effetto di CiccioPanza*/
                    playerTurn.getPersonalSchool().getProfOfPlayer().get(1).setInHall(true);
                }else{
                    for(Player p : players)
                        p.getPersonalSchool().getProfOfPlayer().get(1).setInHall(false);
                }
            case YELLOW:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.YELLOW) > max) {
                        max = numberOfStudents(p, SColor.YELLOW);
                    }
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.YELLOW) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }else p.getPersonalSchool().getProfOfPlayer().get(2).setInHall(false);
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().getProfOfPlayer().get(2).setInHall(true);
                }
                else if(playerWithMax > 1 && cardEffectPlayed.isOstePlayed()){ /**Se ho più giocatori in pareggio, setto a true solo il prof di chi ha l'effetto di Oste*/
                    playerTurn.getPersonalSchool().getProfOfPlayer().get(2).setInHall(true);
                }else{
                    for(Player p : players)
                        p.getPersonalSchool().getProfOfPlayer().get(2).setInHall(false);
                }

            case PINK:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.PINK) > max) {
                        max = numberOfStudents(p, SColor.PINK);
                    }
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.PINK) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }else p.getPersonalSchool().getProfOfPlayer().get(3).setInHall(false);
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().getProfOfPlayer().get(3).setInHall(true);
                }
                else if(playerWithMax > 1 && cardEffectPlayed.isOstePlayed()){ /**Se ho più giocatori in pareggio, setto a true solo il prof di chi ha l'effetto di Oste*/
                    playerTurn.getPersonalSchool().getProfOfPlayer().get(3).setInHall(true);
                }else{
                    for(Player p : players)
                        p.getPersonalSchool().getProfOfPlayer().get(3).setInHall(false);
                }
            case BLUE:
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.BLUE) > max) {
                        max = numberOfStudents(p, SColor.BLUE);
                    }
                }
                for (Player p : players) {
                    if (numberOfStudents(p, SColor.BLUE) == max) {
                        playerWithMax++;
                        maxPlayer = p;
                    }else p.getPersonalSchool().getProfOfPlayer().get(4).setInHall(false);
                }
                if (playerWithMax == 1) {
                    maxPlayer.getPersonalSchool().getProfOfPlayer().get(4).setInHall(true);
                }
                else if(playerWithMax > 1 && cardEffectPlayed.isOstePlayed()){ /**Se ho più giocatori in pareggio, setto a true solo il prof di chi ha l'effetto di Oste*/
                    playerTurn.getPersonalSchool().getProfOfPlayer().get(4).setInHall(true);
                }else{
                    for(Player p : players)
                        p.getPersonalSchool().getProfOfPlayer().get(4).setInHall(false);
                }
        }
        cardEffectPlayed.setOstePlayed(false); /** Va Bene??? */
    }

    public boolean getProfInHall(SColor color){     /** DA CAMBIARE: ORA ABBIAMO ARRAY PROFOFPLAYER (POI VA CAMBIATO ANCHE CALCULATEINFLUENCE) */
        boolean x = false;

        for(Prof p : profOfPlayer) {
            if (p.getSColour().equals(color))
                x= p.getIsInHall();
        }
        return x;
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

        for (Prof prof : profOfPlayer){
            if(prof.getIsInHall())
                countProf++;
        }
        return countProf;
    }

}

