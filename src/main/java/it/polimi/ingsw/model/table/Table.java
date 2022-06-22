package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Table implements Serializable {

    private ArrayList<CloudCard> cloudNumber = new ArrayList<>() ;
    private ArrayList<IslandCard> listOfIsland = new ArrayList<>();
    private ArrayList<CharacterCard> characterCardsOnTable = new ArrayList<>();
    private int coinsOnTable;
    private int posMotherEarth = 0;
    private ArrayList<Student> bag = new ArrayList<>();

    /**
     * generate the initial bag with the 10 students, each 2 of a different color, and shuffle them.
     */
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

    /**
     * Places the initial 10 students in the islands
     */
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

    /**
     * Adds in the bag the remaining 120 students and shuffle them.
     */
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

        /**for (int s = 11; s < 71; s++) {
            if (s < 23)
                bag.add(new Student(s, green));
            else if (s < 35)
                bag.add(new Student(s, red));
            else if (s < 47)
                bag.add(new Student(s, yellow));
            else if (s < 59)
                bag.add(new Student(s, pink));
            else
                bag.add(new Student(s, blue));
        }*/
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
            cloudNumber.add(new CloudCard(i+1, maxNumberOfStudents));
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

        for( int i = 6; i<9; i++){
            characterCardsOnTable.add(characterCards.get(i));
        }

        for(int j=0; j<3; j++){
            switch (characterCardsOnTable.get(j).getCardEffect()){
                case ABBOT:
                case COURTESAN:
                    for (int i = 0; i < 4; i++) {
                        characterCardsOnTable.get(j).getStudentsOnCard().add(bag.get(0));
                        bag.remove(bag.get(0));
                    }
                    break;
                case CURATOR:
                        characterCardsOnTable.get(j).setXCardOnCard(4);
                    break;
                case ACROBAT:
                    for (int i = 0; i < 6; i++) {
                        characterCardsOnTable.get(j).getStudentsOnCard().add(bag.get(0));
                        bag.remove(bag.get(0));
                    }
                    break;
                default:
                    break;
            }
        }
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

    /**
     *
     * @param cardEffect the effect selected
     * @return Returns the character card from the list of character cards on the table with the effect selected
     */
    public CharacterCard getCharacterCard(CardEffect cardEffect){
        int indexCharacter = getCharacterCardByEffect().indexOf(cardEffect);
        return characterCardsOnTable.get(indexCharacter);
    }

    /**
     *
     * @return Returns an array with the effects of the character card of the match
     */
    public ArrayList<CardEffect> getCharacterCardByEffect() {
        ArrayList<CardEffect> cardEffectList = new ArrayList<>();
        for (CharacterCard cc : characterCardsOnTable) {
            cardEffectList.add(cc.getCardEffect());
        }
        return cardEffectList;
    }

    public int getPosMotherEarth() {
        return posMotherEarth;
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

    public void decreaseCoinsOnTable(int coinsValue){
        setCoinsOnTable(getCoinsOnTable() - coinsValue);
    }

    public ArrayList<CharacterCard> getCharacterCardsOnTable() {
        return characterCardsOnTable;
    }

    /** Evaluate if 2 or more islands needs to merge
     *
     * @param listOfIsland list of the islands of the match
     */
    public void joinIsland(ArrayList<IslandCard> listOfIsland) {      //valuto se due isole mergiano, in caso le unisco

        int i = posMotherEarth - 1;
        if (listOfIsland.get(i).towerIsOnIsland()) {
            if (i == (listOfIsland.size() - 1)) {
                if (!listOfIsland.get(i - 1).towerIsOnIsland() && listOfIsland.get(0).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland,i,0);
                } else if (!listOfIsland.get(0).towerIsOnIsland() && listOfIsland.get(i - 1).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i, i-1);
                } else if (listOfIsland.get(0).towerIsOnIsland() && listOfIsland.get(i - 1).towerIsOnIsland()) {
                    MergeTower(listOfIsland,i,0,i-1);
                }
            }else if(i==0){
                int s = (listOfIsland.size() - 1);
                if (!listOfIsland.get(s).towerIsOnIsland() && listOfIsland.get(i + 1).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i,i+1);
                } else if (!listOfIsland.get(i + 1).towerIsOnIsland() && listOfIsland.get(s).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i, s);// 3 isole caso i= size
                } else if (listOfIsland.get(s).towerIsOnIsland() && listOfIsland.get(i+1).towerIsOnIsland()) {
                    MergeTower(listOfIsland,i,i+1,s);
                }
            }else{
                if (!listOfIsland.get(i - 1).towerIsOnIsland() && listOfIsland.get(i + 1).towerIsOnIsland()) {
                    MergeNoTower(listOfIsland, i,i+1);
                } else if (!listOfIsland.get(i + 1).towerIsOnIsland() && listOfIsland.get(i - 1).towerIsOnIsland() ) {
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
        ArrayList<Team> teams = game.getTeams();
        int countGrey = 0;
        int countWhite = 0;
        int countBlack = 0;
        Player winner = null;
        Player alsoWinner = null;

        /** Counts for each color, the number of tower placed */
        for (IslandCard islandCard : listOfIsland) {
            if(islandCard.towerIsOnIsland()){
                if ((islandCard.getTowerOnIsland().getTColour()).equals(TColor.GREY)) {
                    countGrey = countGrey + islandCard.getMergedIsland();
                } else if ((islandCard.getTowerOnIsland().getTColour()).equals(TColor.WHITE)) {
                    countWhite = countWhite + islandCard.getMergedIsland();
                } else if ((islandCard.getTowerOnIsland().getTColour()).equals(TColor.BLACK)) {
                    countBlack = countBlack + islandCard.getMergedIsland();
                }
            }
        }

        /** comparison and looking for who has the most towers, if someone has the highest number of towers is winning */
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

            /** in case of a tie, I compare the players and the one with the most prof wins
            Black and Grey case */
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
                    return null; /** if they tie also the number of professors */
            }
            /** in case of a tie, I compare the players and the one with the most prof wins
             White and Grey case */
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
                    return null;
            }
            /** in case of a tie, I compare the players and the one with the most prof wins
             Black and White case */
            else if (countWhite == countBlack && countWhite > countGrey) {
                return returnWinner(game);
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
        }else if(game.getGameMode().equals(GameMode.TWOPLAYERS)){
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
                return returnWinner(game);
            }
        }
        else{


            if(countWhite > countBlack) {
                int i=0;
                while (!teams.get(i).getTeamColor().equals(TColor.WHITE))
                    i++;
                return teams.get(i).getTeamLeader();
            }
            else if(countWhite < countBlack){
                int i=0;
                while (!teams.get(i).getTeamColor().equals(TColor.WHITE))
                    i++;
                return teams.get(i).getTeamLeader();
            }
            else {
                int countProfWhite = 0;
                int countProfBlack = 0;
                Player teamLeaderWhite = null;
                Player teamLeaderBlack = null;
                for (Team t : teams){
                    for(Player p : t.getTeam()){
                        if(p.getTColor().equals(TColor.WHITE)){
                            countProfWhite += p.getPersonalSchool().numberOfProf();
                            teamLeaderWhite = t.getTeamLeader();
                        }
                        else {
                            countProfBlack += p.getPersonalSchool().numberOfProf();
                            teamLeaderBlack = t.getTeamLeader();
                        }
                    }
                }

                if(countProfWhite > countProfBlack)
                    return teamLeaderWhite;
                else if(countProfWhite < countProfBlack)
                    return teamLeaderBlack;
            }
        }
        return null;
    }

    private Player returnWinner(Game game) {
        Player winner = null;
        Player alsoWinner = null;
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

