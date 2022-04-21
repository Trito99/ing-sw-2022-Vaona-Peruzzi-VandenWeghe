package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.Prof;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Table {

    private ArrayList<CloudCard> cloudNumber = new ArrayList<>() ;
    private ArrayList<IslandCard> listOfIsland = new ArrayList<>();
    private ArrayList<CharacterCard> characterCardsOnTable = new ArrayList<>();
    private int coinsOnTable;
    private int posMotherEarth = 0;
    private ArrayList<Student> bag = new ArrayList<>();
    private ArrayList<Prof> listOfProfOnTable = new ArrayList<>();


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

    public void extractStudentsInit(){
        for(int i=0;i<listOfIsland.size();i++){
            if(posMotherEarth+6>listOfIsland.size()){
                if(i!=(posMotherEarth-1) && i!=(posMotherEarth+5-listOfIsland.size())) {
                    listOfIsland.get(i).getStudentOnIsland().add(bag.get(0));
                    bag.remove(bag.get(0));
                }
            }else{
                if(i!=(posMotherEarth-1) && i!=(posMotherEarth+5)) {
                    listOfIsland.get(i).getStudentOnIsland().add(bag.get(0));
                    bag.remove(bag.get(0));
                }
            }
        }
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

    public void extractStudentOnCloud() {   //estrae dal sacchetto 3/4 studenti
        for (CloudCard cloudCard : cloudNumber) {
            for (int i = 0; i < cloudCard.getNumberOfSpaces(); i++) {
                cloudCard.getStudentOnCloud().add(bag.get(i));
                bag.remove(bag.get(i));
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
        listOfIsland.get(n - 1).setMotherEarthOnIsland(true);
        posMotherEarth = n;
    }

    public void generateCharacterCardsOnTable(ArrayList<CharacterCard> characterCards){
        Collections.shuffle(characterCards);

        for( int i = 0; i<3; i++){
            characterCardsOnTable.add(characterCards.get(i));
        }

        for(int j=0; j<3; j++){
            switch (characterCardsOnTable.get(j).getCardEffect()){
                case ABATE:
                case CORTIGIANA:
                    for (int i = 0; i < 4; i++) {
                        characterCardsOnTable.get(j).getStudentsOnCard().add(bag.get(i));
                        bag.remove(bag.get(i));
                    }
                    break;
                case CURATRICE:
                        characterCardsOnTable.get(j).getCardEffect().setXCardOnCard(4);
                    break;
                case SALTIMBANCO:
                    for (int i = 0; i < 6; i++) {
                        characterCardsOnTable.get(j).getStudentsOnCard().add(bag.get(i));
                        bag.remove(bag.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void moveMotherEarth(int n) {  /** GUARDA GAMECONTROLLER */
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
        return (ArrayList<CloudCard>) cloudNumber.clone();
    }

    public ArrayList<IslandCard> getListOfIsland() {
        return (ArrayList<IslandCard>) listOfIsland;
    }

    public int getCoinsOnTable() {
        return coinsOnTable;
    }

    public void setCoinsOnTable(int coinsOnTable) {
        this.coinsOnTable = coinsOnTable;
    }

    public void increaseCoinsOnTable(int coinsValue){
        setCoinsOnTable(getCoinsOnTable() + coinsValue);
    }

    public ArrayList<CharacterCard> getCharacterCardsOnTable() {
        return (ArrayList<CharacterCard>) characterCardsOnTable.clone();
    }

    public void joinIsland(ArrayList<IslandCard> listOfIsland) {      //valuto se due isole mergiano, in caso le unisco

        int i = posMotherEarth - 1;
        if (listOfIsland.get(i).towerIsOnIsland() == true) {/** isole adiacenti */
            if (i == (listOfIsland.size() - 1)) {
                if (listOfIsland.get(i-1).towerIsOnIsland() == false) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(0).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(0).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(0).getMergedIsland());
                        listOfIsland.remove(0);
                    }
                } else if (listOfIsland.get(0).towerIsOnIsland() == false) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i - 1).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(i - 1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i-1).getMergedIsland());
                        listOfIsland.remove(i - 1);
                    }   // 3 isole caso i= size
                } else if (listOfIsland.get(0).towerIsOnIsland() == true && listOfIsland.get(i - 1).towerIsOnIsland() == true) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(0).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(i - 1).getTowerOnIsland().getTColour()) {
                        for (Student student : listOfIsland.get(0).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(0).getMergedIsland());
                        listOfIsland.remove(0);
                    }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i - 1).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(0).getTowerOnIsland().getTColour()) {
                        for (Student student : listOfIsland.get(i - 1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i-1).getMergedIsland());
                        listOfIsland.remove(i - 1);
                    }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i - 1).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(0).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(i - 1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        for (Student student : listOfIsland.get(0).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(0).getMergedIsland() + listOfIsland.get(i-1).getMergedIsland());
                        listOfIsland.remove(0);
                        listOfIsland.remove(i - 2);
                    }
                }
            }else if(i==0){
                int s = (listOfIsland.size() - 1);
                if (listOfIsland.get(s).towerIsOnIsland() == false) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i+1).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(i+1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i+1).getMergedIsland());
                        listOfIsland.remove(i+1);
                    }
                } else if (listOfIsland.get(i+1).towerIsOnIsland() == false) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(s).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(s).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(s).getMergedIsland());
                        listOfIsland.remove(s);
                    }   // 3 isole caso i= size
                } else if (listOfIsland.get(s).towerIsOnIsland() == true && listOfIsland.get(i+1).towerIsOnIsland() == true) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i+1).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(s).getTowerOnIsland().getTColour()) {
                        for (Student student : listOfIsland.get(i + 1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i+1).getMergedIsland());
                        listOfIsland.remove(i + 1);
                    }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(s).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(i+1).getTowerOnIsland().getTColour()) {
                        for (Student student : listOfIsland.get(s).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(s).getMergedIsland());
                        listOfIsland.remove(s);
                    }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(s).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i+1).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(s).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        for (Student student : listOfIsland.get(i + 1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i+1).getMergedIsland() + listOfIsland.get(s).getMergedIsland());
                        listOfIsland.remove(s);
                        listOfIsland.remove(i + 1);
                    }
                }
            }else{
                if (listOfIsland.get(i-1).towerIsOnIsland() == false) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i+1).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(i+1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i+1).getMergedIsland());
                        listOfIsland.remove(i+1);
                    }
                } else if (listOfIsland.get(i+1).towerIsOnIsland() == false) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i-1).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(i-1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i-1).getMergedIsland());
                        listOfIsland.remove(i-1);
                    }   // 3 isole caso i= size
                } else if (listOfIsland.get(i-1).towerIsOnIsland() == true && listOfIsland.get(i+1).towerIsOnIsland() == true) {
                    if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i+1).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(i-1).getTowerOnIsland().getTColour()) {
                        for (Student student : listOfIsland.get(i+1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i+1).getMergedIsland());
                        listOfIsland.remove(i+1);
                    }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i-1).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(i+1).getTowerOnIsland().getTColour()) {
                        for (Student student : listOfIsland.get(i-1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i-1).getMergedIsland());
                        listOfIsland.remove(i-1);
                    }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i - 1).getTowerOnIsland().getTColour()) &&
                            listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(i+1).getTowerOnIsland().getTColour())) {
                        for (Student student : listOfIsland.get(i-1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        for (Student student : listOfIsland.get(i + 1).getStudentOnIsland())
                            listOfIsland.get(i).getStudentOnIsland().add(student);
                        listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(i+1).getMergedIsland() + listOfIsland.get(i-1).getMergedIsland());
                        listOfIsland.remove(i-1);
                        listOfIsland.remove(i);
                    }
                }
            }

            for (int f = 0; f < listOfIsland.size(); f++) {
                listOfIsland.get(f).setIdIsland(f + 1);
                if (listOfIsland.get(f).getMotherEarthOnIsland() == true)
                    posMotherEarth = f + 1;
            }
        }
    }

    public Player playerIsWinning(Game game) {  //calcola influenza torri sul tavolo e restituisce quello con più influenza
        int countGrey = 0;
        int countWhite = 0;
        int countBlack = 0;
        Player winner = null;
        Player alsoWinner = null;

        /** conto il numero di torri presenti sul tavolo per ogni colore */
        for (int s = 0; s < listOfIsland.size(); s++) {
            if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColor.GREY)) {
                countGrey=countGrey+listOfIsland.get(s).getMergedIsland();
            } else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColor.WHITE)) {
                countWhite=countWhite+listOfIsland.get(s).getMergedIsland();
            } else if ((listOfIsland.get(s).getTowerOnIsland().getTColour()).equals(TColor.BLACK)) {
                countBlack=countBlack+listOfIsland.get(s).getMergedIsland();
            }
        }

        /** confronto e cerco chi ha maggior influenza */
        if(game.getGameMode().equals(GameMode.THREEPLAYERS)){
            if (countBlack > countGrey && countBlack > countWhite) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.BLACK)) {
                        return player;
                    }
                }
            } else if (countGrey > countBlack && countGrey > countWhite) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.GREY)) {
                        return player;
                    }
                }
            } else if (countWhite > countBlack && countWhite > countGrey) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.WHITE)) {
                        return player;
                    }
                }
            }

            /** in caso di parità, confronto i player e vince quello con più prof */
            else if (countBlack == countGrey && countBlack > countWhite) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.BLACK)) {
                        winner = player;
                    } else if (player.getTColour().equals(TColor.GREY)) {
                        alsoWinner = player;
                    }
                }
                int profWinner = winner.getPersonalSchool().numberOfProf();
                int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

                if (profWinner > profAlsoWinner)
                    return winner;
                else if (profWinner < profAlsoWinner)
                    return alsoWinner;
                else
                    return null; //Black Grey
            }
            else if (countGrey == countWhite && countGrey > countBlack) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.GREY)) {
                        winner = player;
                    } else if (player.getTColour().equals(TColor.WHITE)) {
                        alsoWinner = player;
                    }
                }
                int profWinner = winner.getPersonalSchool().numberOfProf();
                int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

                if (profWinner > profAlsoWinner)
                    return winner;
                else if (profWinner < profAlsoWinner)
                    return alsoWinner;
                else
                    return null; //grey white
            }
            else if (countWhite == countBlack && countWhite > countGrey) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.WHITE)) {
                        winner = player;
                    } else if (player.getTColour().equals(TColor.BLACK)) {
                        alsoWinner = player;
                    }
                }
                int profWinner = winner.getPersonalSchool().numberOfProf();
                int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

                if (profWinner > profAlsoWinner)
                    return winner;
                else if (profWinner < profAlsoWinner)
                    return alsoWinner;
                else
                    return null; //white black
            }else{
                int profWinner = game.getOrder().get(0).getPersonalSchool().numberOfProf();
                int profAlsoWinner = game.getOrder().get(1).getPersonalSchool().numberOfProf();
                int profAlsoWinner2 = game.getOrder().get(2).getPersonalSchool().numberOfProf();

                if (profWinner > profAlsoWinner && profWinner> profAlsoWinner2)
                    return game.getOrder().get(0);
                else if (profWinner < profAlsoWinner && profAlsoWinner>profAlsoWinner2)
                    return game.getOrder().get(1);
                else if (profAlsoWinner2>profAlsoWinner && profAlsoWinner2>profWinner)
                    return game.getOrder().get(2);
                else
                    return null;
            }
        }else{
            if (countBlack > countWhite) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.BLACK)) {
                        return player;
                    }
                }
            }else if (countWhite > countBlack) {
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.WHITE)) {
                        return player;
                    }
                }
            } else if(countWhite == countBlack){
                for (Player player : game.getOrder()) {
                    if (player.getTColour().equals(TColor.WHITE)) {
                        winner = player;
                    } else if (player.getTColour().equals(TColor.BLACK)) {
                        alsoWinner = player;
                    }
                }
                int profWinner = winner.getPersonalSchool().numberOfProf();
                int profAlsoWinner = alsoWinner.getPersonalSchool().numberOfProf();

                if (profWinner > profAlsoWinner)
                    return winner;
                else if (profWinner < profAlsoWinner)
                    return alsoWinner;
                else
                    return null;
            }
        }
        return winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return listOfIsland.equals(table.listOfIsland);
    }

}

