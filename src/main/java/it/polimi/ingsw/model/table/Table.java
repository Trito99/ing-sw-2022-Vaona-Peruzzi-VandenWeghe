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
                case ABBOT:
                case COURTESAN:
                    for (int i = 0; i < 4; i++) {
                        characterCardsOnTable.get(j).getStudentsOnCard().add(bag.get(i));
                        bag.remove(bag.get(i));
                    }
                    break;
                case CURATOR:
                        characterCardsOnTable.get(j).getCardEffect().setXCardOnCard(4);
                    break;
                case ACROBAT:
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
        return listOfIsland;
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
        if (listOfIsland.get(i).towerIsOnIsland()) {/** isole adiacenti */
            if (i == (listOfIsland.size() - 1)) {
                if (!listOfIsland.get(i - 1).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland,i,0);
                } else if (!listOfIsland.get(0).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i, i-1);
                } else if (listOfIsland.get(0).towerIsOnIsland() && listOfIsland.get(i - 1).towerIsOnIsland()) {
                    MergeTower(listOfIsland,i,0,i-1);
                }
            }else if(i==0){
                int s = (listOfIsland.size() - 1);
                if (!listOfIsland.get(s).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i,i+1);
                } else if (!listOfIsland.get(i + 1).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i, s);// 3 isole caso i= size
                } else if (listOfIsland.get(s).towerIsOnIsland() && listOfIsland.get(i+1).towerIsOnIsland()) {
                    MergeTower(listOfIsland,i,i+1,s);
                }
            }else{
                if (!listOfIsland.get(i - 1).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i,i+1);
                } else if (!listOfIsland.get(i + 1).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i, i-1);
                } else if (listOfIsland.get(i-1).towerIsOnIsland() && listOfIsland.get(i+1).towerIsOnIsland()) {
                    MergeTower(listOfIsland,i,i+1,i-1);
                }
            }

            for (int f = 0; f < listOfIsland.size(); f++) {
                listOfIsland.get(f).setIdIsland(f + 1);
                if (listOfIsland.get(f).getMotherEarthOnIsland())
                    posMotherEarth = f + 1;
            }
        }
    }

    private void MergeNoTower(ArrayList<IslandCard> listOfIsland, int i, int ii) {
        if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(ii).getTowerOnIsland().getTColour())) {
            for (Student student : listOfIsland.get(ii).getStudentOnIsland())
                listOfIsland.get(i).getStudentOnIsland().add(student);
            listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(ii).getMergedIsland());
            listOfIsland.remove(ii);
        }
    }

    private void MergeTower(ArrayList<IslandCard> listOfIsland, int i, int ii, int iii){
        if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(ii).getTowerOnIsland().getTColour()) &&
                listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(iii).getTowerOnIsland().getTColour()) {
            for (Student student : listOfIsland.get(ii).getStudentOnIsland())
                listOfIsland.get(i).getStudentOnIsland().add(student);
            listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(ii).getMergedIsland());
            listOfIsland.remove(ii);
        }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(iii).getTowerOnIsland().getTColour()) &&
                listOfIsland.get(i).getTowerOnIsland().getTColour() != listOfIsland.get(ii).getTowerOnIsland().getTColour()) {
            for (Student student : listOfIsland.get(iii).getStudentOnIsland())
                listOfIsland.get(i).getStudentOnIsland().add(student);
            listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(iii).getMergedIsland());
            listOfIsland.remove(iii);
        }else if (listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(iii).getTowerOnIsland().getTColour()) &&
                listOfIsland.get(i).getTowerOnIsland().getTColour().equals(listOfIsland.get(ii).getTowerOnIsland().getTColour())) {
            for (Student student : listOfIsland.get(iii).getStudentOnIsland())
                listOfIsland.get(i).getStudentOnIsland().add(student);
            for (Student student : listOfIsland.get(ii).getStudentOnIsland())
                listOfIsland.get(i).getStudentOnIsland().add(student);
            listOfIsland.get(i).setMergedIsland(listOfIsland.get(i).getMergedIsland() + listOfIsland.get(ii).getMergedIsland() + listOfIsland.get(iii).getMergedIsland());
            if(i==0){
                listOfIsland.remove(listOfIsland.size()-1);
                listOfIsland.remove(ii);
            }else if(i==listOfIsland.size()-1){
                listOfIsland.remove(iii);
                listOfIsland.remove(0);
            }else{
                listOfIsland.remove(iii);
                listOfIsland.remove(i);
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
        for (IslandCard islandCard : listOfIsland) {
            if ((islandCard.getTowerOnIsland().getTColour()).equals(TColor.GREY)) {
                countGrey = countGrey + islandCard.getMergedIsland();
            } else if ((islandCard.getTowerOnIsland().getTColour()).equals(TColor.WHITE)) {
                countWhite = countWhite + islandCard.getMergedIsland();
            } else if ((islandCard.getTowerOnIsland().getTColour()).equals(TColor.BLACK)) {
                countBlack = countBlack + islandCard.getMergedIsland();
            }
        }

        /** confronto e cerco chi ha maggior influenza */
        if(game.getGameMode().equals(GameMode.THREEPLAYERS)){
            if (countBlack > countGrey && countBlack > countWhite) {
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.BLACK)) {
                        return player;
                    }
                }
            } else if (countGrey > countBlack && countGrey > countWhite) {
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.GREY)) {
                        return player;
                    }
                }
            } else if (countWhite > countBlack && countWhite > countGrey) {
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.WHITE)) {
                        return player;
                    }
                }
            }

            /** in caso di parità, confronto i player e vince quello con più prof */
            else if (countBlack == countGrey && countBlack > countWhite) {
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.BLACK)) {
                        winner = player;
                    } else if (player.getTColor().equals(TColor.GREY)) {
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
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.GREY)) {
                        winner = player;
                    } else if (player.getTColor().equals(TColor.WHITE)) {
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
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.WHITE)) {
                        winner = player;
                    } else if (player.getTColor().equals(TColor.BLACK)) {
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
                int profWinner = game.getListOfPlayers().get(0).getPersonalSchool().numberOfProf();
                int profAlsoWinner = game.getListOfPlayers().get(1).getPersonalSchool().numberOfProf();
                int profAlsoWinner2 = game.getListOfPlayers().get(2).getPersonalSchool().numberOfProf();

                if (profWinner > profAlsoWinner && profWinner> profAlsoWinner2)
                    return game.getListOfPlayers().get(0);
                else if (profWinner < profAlsoWinner && profAlsoWinner>profAlsoWinner2)
                    return game.getListOfPlayers().get(1);
                else if (profAlsoWinner2>profAlsoWinner && profAlsoWinner2>profWinner)
                    return game.getListOfPlayers().get(2);
                else
                    return null;
            }
        }else{
            if (countBlack > countWhite) {
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.BLACK)) {
                        return player;
                    }
                }
            }else if (countWhite > countBlack) {
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.WHITE)) {
                        return player;
                    }
                }
            } else if(countWhite == countBlack){
                for (Player player : game.getListOfPlayers()) {
                    if (player.getTColor().equals(TColor.WHITE)) {
                        winner = player;
                    } else if (player.getTColor().equals(TColor.BLACK)) {
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

