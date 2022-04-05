package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.TColour;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Table {

    private ArrayList<CloudCard> cloudNumber;
    private ArrayList<IslandCard> listOfIsland;
    private ArrayList<CharacterCard> characterCardsOnTable;
    private int coinsOnTable;
    private int posMotherEarth=0;
    private ArrayList<Student> bag = new ArrayList<>();

    public void generateBagInit(){
        SColour green = SColour.GREEN;
        SColour red = SColour.RED;
        SColour yellow = SColour.YELLOW;
        SColour pink = SColour.PINK;
        SColour blue = SColour.BLUE;

        for(int s=1;s<11;s++){
            if(s<3)
                bag.add(new Student(s,green));
            else if(s<5)
                bag.add(new Student(s,red));
            else if(s<7)
                bag.add(new Student(s,yellow));
            else if(s<9)
                bag.add(new Student(s,pink));
            else
                bag.add(new Student(s,blue));
        }
        Collections.shuffle(bag);
    }

    public void addFinalStudents(){
        SColour green = SColour.GREEN;
        SColour red = SColour.RED;
        SColour yellow = SColour.YELLOW;
        SColour pink = SColour.PINK;
        SColour blue = SColour.BLUE;

        for(int s=11;s<131;s++){
            if(s<35)
                bag.add(new Student(s,green));
            else if(s<59)
                bag.add(new Student(s,red));
            else if(s<83)
                bag.add(new Student(s,yellow));
            else if(s<107)
                bag.add(new Student(s,pink));
            else
                bag.add(new Student(s,blue));
        }
        Collections.shuffle(bag);
    }


    public void extractStudent() {   //estrae dal sacchetto 3/4 studenti
        for(int c=0;c<cloudNumber.size();c++){
            if (cloudNumber.get(c).getNumberOfSpaces() == 4) {
                for (int i = 0; i < 4; i++) {
                    cloudNumber.get(c).getStudentOnCloud().add(bag.get(i));
                    bag.remove(bag.get(i));
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    cloudNumber.get(c).getStudentOnCloud().add(bag.get(i));
                    bag.remove(bag.get(i));
                }
            }
        }
    }

    public ArrayList<Student> getBag() {
        return bag;
    }

    public void generateCloudNumber(GameMode gm){
        int x;
        int maxNumberOfStudents;

        if(gm.equals(GameMode.TWOPLAYERS)){
            x=2;
            maxNumberOfStudents = 3;
        }else if(gm.equals(GameMode.THREEPLAYERS)){
            x=3;
            maxNumberOfStudents = 4;
        }else{
            x=4;
            maxNumberOfStudents = 3;
        }
        for(int i=0;i<x;i++){
            cloudNumber.add(new CloudCard(i, maxNumberOfStudents));
        }
    }

    public void generateIslandCards(){
        for(int s=1;s<13;s++) {
            listOfIsland.add(new IslandCard(s));
        }
    }
    public void generateMotherEarth(){
        Random rn = new Random();
        int n = rn.nextInt(12)+1;
        System.out.println(n);
        listOfIsland.get(n-1).setMotherEarthOnIsland(true);
        posMotherEarth=n;
    }
    public int getPosMotherEarth(){ return posMotherEarth;}

    public void setPosMotherEarth(int posMotherEarth){this.posMotherEarth=posMotherEarth;}

    public ArrayList<CloudCard> getCloudNumber() {
        return cloudNumber;
    }

    public ArrayList<IslandCard> getListOfIsland() {
        return listOfIsland;
    }

    public int getCoinsOnTable() {
        return coinsOnTable;
    }

    public ArrayList<CharacterCard> getCharacterCardsOnTable() {
        return characterCardsOnTable;
    }

    public ArrayList<IslandCard> joinIsland(IslandCard island){      //valuto se due isole mergiano, in caso le unisco
        int i = 0;
        ArrayList<IslandCard> newListOfIsland = null;

        for(IslandCard islands : listOfIsland){
            if(islands.getMotherEarthOnIsland()){
                i = islands.getIdIsland();
            }
        }


        /** isole adiacenti */
        // se è 0 controlla il 12
        if (i == listOfIsland.size() -1){
            int s = 0;
            newListOfIsland.remove(s);
            //....
        }
        else if(listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i+1).getTowerOnIsland().getTColour()) &&
                (listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(i+2).getTowerOnIsland().getTColour())){
            newListOfIsland.remove(island.getIdIsland()+1);
        }

        /** tre isole */
        else if(listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i-1).getTowerOnIsland().getTColour())){

        }
    }


    public Player playerIsWinning(Table table, ArrayList<Player> listOfPlayers){  //calcola influenza torri sul tavolo e restituisce quello con più influenza
        int countGrey = 0;
        int countWhite = 0;
        int countBlack = 0;
        Player winner = null;
        Player alsoWinner = null ;

        /** conto il numero di torri presenti sul tavolo per ogni colore */
        for (int s=1;s<13;s++){
            if((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColour.GREY)){
                countGrey++;
            }
            else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColour.WHITE)){
                countWhite++;
            }
            else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColour.BLACK)){
                countBlack++;
            }
        }

        /** confronto e cerco chi ha maggior influenza */
        if (countBlack > countGrey && countBlack > countWhite){
            for(Player player : listOfPlayers){
                if(player.getTColour().equals(TColour.BLACK)){
                    return winner = player;
                }
            }
        }

        else if(countGrey > countBlack && countGrey > countWhite){
                for(Player player : listOfPlayers) {
                    if (player.getTColour().equals(TColour.BLACK)) {
                        return winner = player;
                    }
                }
        }

        else if(countWhite > countBlack && countWhite > countGrey){
            for(Player player : listOfPlayers) {
                if (player.getTColour().equals(TColour.BLACK)) {
                    return winner = player;
                }
            }
        }

        /** in caso di parità, confronto i player e vince quello con più prof */
        else if(countBlack == countGrey && countBlack > countWhite){
            for(Player player : listOfPlayers) {
                if (player.getTColour().equals(TColour.BLACK)) {
                    winner = player;
                }
                else if(player.getTColour().equals(TColour.GREY)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return winner = alsoWinner;
        }

        else if(countGrey == countWhite && countGrey > countBlack){
            for(Player player : listOfPlayers) {
                if (player.getTColour().equals(TColour.GREY)) {
                    winner = player;
                }
                else if(player.getTColour().equals(TColour.WHITE)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else return winner = alsoWinner;
        }

        else if(countWhite == countBlack && countWhite > countGrey){
            for(Player player : listOfPlayers) {
                if (player.getTColour().equals(TColour.WHITE)) {
                    winner = player;
                }
                else if(player.getTColour().equals(TColour.BLACK)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if(profWinner > profAlsoWinner){
                return winner;
            }
            else if(profAlsoWinner > profWinner){
                winner = alsoWinner;
            }
            else{
                System.out.println(String.format("%s e %s", winner.getNickname(), alsoWinner.getNickname()) + "HANNO PAREGGIATO!");
            }
        }

        if(Game.gameIsFinished(table))   {

            System.out.println("HA VINTO IL GIOCATORE " + winner.getNickname());

        return winner;


}
