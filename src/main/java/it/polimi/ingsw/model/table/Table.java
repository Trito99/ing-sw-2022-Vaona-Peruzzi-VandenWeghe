package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Table {

    private ArrayList<CloudCard> cloudNumber;
    private ArrayList<IslandCard> listOfIsland;
    private ArrayList<CharacterCard> characterCardsOnTable;
    private int coinsOnTable;
    private int posMotherEarth = 0;
    private ArrayList<Student> bag = new ArrayList<>();

    public void generateBagInit() {
        SColor green = SColor.GREEN;
        SColor red = SColor.RED;
        SColor yellow = SColor.YELLOW;
        SColor pink = SColor.PINK;
        SColor blue = SColor.BLUE;

        for (int s = 1; s < 11; s++) {
            if (s < 3)
                bag.add(new Student(s, green));
            else if (s < 5)
                bag.add(new Student(s, red));
            else if (s < 7)
                bag.add(new Student(s, yellow));
            else if (s < 9)
                bag.add(new Student(s, pink));
            else
                bag.add(new Student(s, blue));
        }
        Collections.shuffle(bag);
    }

    public void addFinalStudents() {
        SColor green = SColor.GREEN;
        SColor red = SColor.RED;
        SColor yellow = SColor.YELLOW;
        SColor pink = SColor.PINK;
        SColor blue = SColor.BLUE;

        for (int s = 11; s < 131; s++) {
            if (s < 35)
                bag.add(new Student(s, green));
            else if (s < 59)
                bag.add(new Student(s, red));
            else if (s < 83)
                bag.add(new Student(s, yellow));
            else if (s < 107)
                bag.add(new Student(s, pink));
            else
                bag.add(new Student(s, blue));
        }
        Collections.shuffle(bag);
    }

    public void extractStudentsInit(){
        for(int i=0;i<listOfIsland.size();i++){
            if(i!=(posMotherEarth-1) && i!=(posMotherEarth+5)) {
                listOfIsland.get(i).getStudentOnIsland().add(bag.get(0));
                bag.remove(bag.get(0));
            }
        }
    }

    public void extractStudent() {   //estrae dal sacchetto 3/4 studenti
        for(int c=0;c<cloudNumber.size();c++){
            for (int i = 0; i < cloudNumber.get(c).getNumberOfSpaces(); i++) {
                cloudNumber.get(c).getStudentOnCloud().add(bag.get(0));
                bag.remove(bag.get(0));
            }
        }
    }

    public ArrayList<Student> getBag() {
        return bag;
    }

    public void generateCloudNumber(GameMode gm) {
        int x;
        int maxNumberOfStudents;

        if (gm.equals(GameMode.TWOPLAYERS)) {
            x = 2;
            maxNumberOfStudents = 3;
        } else if (gm.equals(GameMode.THREEPLAYERS)) {
            x = 3;
            maxNumberOfStudents = 4;
        } else {
            x = 4;
            maxNumberOfStudents = 3;
        }
        for (int i = 0; i < x; i++) {
            cloudNumber.add(new CloudCard(i, maxNumberOfStudents));
        }
    }

    public void generateIslandCards() {
        for (int s = 1; s < 13; s++) {
            listOfIsland.add(new IslandCard(s));
        }
    }

    public void generateMotherEarth() {
        Random rn = new Random();
        int n = rn.nextInt(12) + 1;
        System.out.println(n);
        listOfIsland.get(n - 1).setMotherEarthOnIsland(true);
        posMotherEarth = n;
    }

    public void moveMotherEarth(int n) {
        listOfIsland.get(posMotherEarth - 1).setMotherEarthOnIsland(false);
        if ((posMotherEarth + n) > listOfIsland.size()) {
            listOfIsland.get(posMotherEarth + n - listOfIsland.size() - 1).setMotherEarthOnIsland(true);
            posMotherEarth = posMotherEarth + n - listOfIsland.size();
        } else {
            listOfIsland.get(posMotherEarth + n - 1).setMotherEarthOnIsland(true);
            posMotherEarth = posMotherEarth + n;
        }
    }

    public int getPosMotherEarth() {
        return posMotherEarth;
    }

    public void setPosMotherEarth(int posMotherEarth) {
        this.posMotherEarth = posMotherEarth;
    }

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

    public ArrayList<IslandCard> joinIsland(IslandCard island, ArrayList<IslandCard> listOfIsland) {      //valuto se due isole mergiano, in caso le unisco

        int i = posMotherEarth -1;
        ArrayList<IslandCard> newListOfIsland = listOfIsland;


        /** isole adiacenti */
        // se è 0 controlla il listofisland.size()
        if (i == listOfIsland.size() - 1) {
            int s = 0;
            if(listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(s).getTowerOnIsland().getTColour()) &&
                    (listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(i - 1).getTowerOnIsland().getTColour())) {
                newListOfIsland.remove(s);                 // 2 isole caso i= size
            }
            else if(listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i - 1).getTowerOnIsland().getTColour())){
                newListOfIsland.remove(s);
                newListOfIsland.remove(posMotherEarth);   // 3 isole caso i= size
            }
        }
        else if (i == 0) {
            int s = listOfIsland.size();
            if(listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i + 1).getTowerOnIsland().getTColour()) &&
                    (listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(s).getTowerOnIsland().getTColour())) {
                newListOfIsland.remove(s);              // 2 isole caso i = 0
            }
            else if(listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(s).getTowerOnIsland().getTColour())){
                newListOfIsland.remove(s);
                newListOfIsland.remove(posMotherEarth);  // 3 isole caso i = 0
            }
        }
        else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i + 1).getTowerOnIsland().getTColour()) &&
                (listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(i - 1).getTowerOnIsland().getTColour())) {
            newListOfIsland.remove(posMotherEarth);      // Caso normal 2 isole
        }

        /** tre isole */
        else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i - 1).getTowerOnIsland().getTColour())) {
            newListOfIsland.remove(posMotherEarth);
            newListOfIsland.remove(posMotherEarth -2);      // Caso normal 3 isole
        }
        return newListOfIsland;
    }


    public Player playerIsWinning(Game game, Table table, ArrayList<Player> listOfPlayers) {  //calcola influenza torri sul tavolo e restituisce quello con più influenza
        int countGrey = 0;
        int countWhite = 0;
        int countBlack = 0;
        Player winner = null;
        Player alsoWinner = null;

        /** conto il numero di torri presenti sul tavolo per ogni colore */
        for (int s = 1; s < 13; s++) {
            if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColor.GREY)) {
                countGrey++;
            } else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColor.WHITE)) {
                countWhite++;
            } else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColor.BLACK)) {
                countBlack++;
            }
        }

        /** confronto e cerco chi ha maggior influenza */
        if (countBlack > countGrey && countBlack > countWhite) {
            for (Player player : listOfPlayers) {
                if (player.getTColour().equals(TColor.BLACK)) {
                    return winner = player;
                }
            }
        } else if (countGrey > countBlack && countGrey > countWhite) {
            for (Player player : listOfPlayers) {
                if (player.getTColour().equals(TColor.BLACK)) {
                    return winner = player;
                }
            }
        } else if (countWhite > countBlack && countWhite > countGrey) {
            for (Player player : listOfPlayers) {
                if (player.getTColour().equals(TColor.BLACK)) {
                    return winner = player;
                }
            }
        }

        /** in caso di parità, confronto i player e vince quello con più prof */
        else if (countBlack == countGrey && countBlack > countWhite) {
            for (Player player : listOfPlayers) {
                if (player.getTColour().equals(TColor.BLACK)) {
                    winner = player;
                } else if (player.getTColour().equals(TColor.GREY)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if (profWinner > profAlsoWinner) {
                return winner;
            } else return winner = alsoWinner;
        } else if (countGrey == countWhite && countGrey > countBlack) {
            for (Player player : listOfPlayers) {
                if (player.getTColour().equals(TColor.GREY)) {
                    winner = player;
                } else if (player.getTColour().equals(TColor.WHITE)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if (profWinner > profAlsoWinner) {
                return winner;
            } else return winner = alsoWinner;
        } else if (countWhite == countBlack && countWhite > countGrey) {
            for (Player player : listOfPlayers) {
                if (player.getTColour().equals(TColor.WHITE)) {
                    winner = player;
                } else if (player.getTColour().equals(TColor.BLACK)) {
                    alsoWinner = player;
                }
            }
            int profWinner = winner.getPersonalSchool().numberOfProf();
            int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

            if (profWinner > profAlsoWinner) {
                return winner;
            } else if (profAlsoWinner > profWinner) {
                winner = alsoWinner;
            } else {
                System.out.println(String.format("%s e %s", winner.getNickname(), alsoWinner.getNickname()) + "HANNO PAREGGIATO!");
            }
        }

        if (game.gameIsFinished(table)) {

            System.out.println("HA VINTO IL GIOCATORE " + winner.getNickname());

            return winner;


        }
    }
