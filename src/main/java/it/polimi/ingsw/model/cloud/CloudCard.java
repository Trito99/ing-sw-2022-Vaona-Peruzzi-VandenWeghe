package it.polimi.ingsw.model.cloud;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class CloudCard {
    private ArrayList<Student> studentOnCloud;
    private int numberOfSpaces;
    private int studentCount;
    private int idCloud;



    public CloudCard(ArrayList<Student> studentOnCloud, int numberOfSpaces, int studentCount, int idCloud){
        this.idCloud = idCloud;
        this.studentOnCloud = new ArrayList<Student>(3);
        this.studentCount = 0;

        Game gameMode = Game.getGameMode();       // Passo gameMode come parametro?
        assert gameMode != null;                  // Se non sappiamo ancora la gameMode? Altro costruttore?(con numberOfSpaces=0)
        if (gameMode.equals(GameMode.THREEPLAYERS)) this.numberOfSpaces = 4;
        else this.numberOfSpaces = 3;

    }

    public ArrayList<Student> getStudentOnCloud() {
        return this.studentOnCloud;
    }

    public int getNumberOfSpaces() {
        GameMode gameMode = Game.getGameMode();

        if(gameMode.equals(GameMode.THREEPLAYERS)){
            numberOfSpaces=4;
            return numberOfSpaces;
        }
        else{
            numberOfSpaces=3;
            return numberOfSpaces;
        }
    }

    public int getIdCloud(){
        return this.idCloud;
    }

    public void clearCloud(){
        for(int i=0; i<numberOfSpaces; i++){
            studentOnCloud.set(i, null);
        }

    }

   public int getStudentCount() {    //restituisce numero di studenti sulla nuvola
        return this.studentCount;
    }
}
