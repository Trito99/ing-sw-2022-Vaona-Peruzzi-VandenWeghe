package it.polimi.ingsw.model.cloud;


import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class CloudCard {
    private int idCloud;
    private ArrayList<Student> studentOnCloud;
    private int numberOfSpaces;


    public CloudCard(int idCloud, int numberOfSpaces){
        this.idCloud = idCloud;
        this.studentOnCloud = new ArrayList<Student>();
        this.numberOfSpaces = numberOfSpaces;
    }

    public ArrayList<Student> getStudentOnCloud() {
        return studentOnCloud;
    }


    public int getNumberOfSpaces(){
        return numberOfSpaces;
    }

    public int getIdCloud(){
        return idCloud;
    }

    public void moveStudentToIsland(IslandCard islandCard, int idStudent){ //Specifico Studente va spostato (sceglie player)
        Student student = new Student(131,null);
        for(int i = 0; i < studentOnCloud.size(); i++) {
            if(idStudent == studentOnCloud.get(i).getIdStudent())
                student = studentOnCloud.get(i);
        }
        islandCard.getStudentOnIsland().add(studentOnCloud.get(studentOnCloud.indexOf(student)));
        studentOnCloud.remove(studentOnCloud.get(studentOnCloud.indexOf(student)));
    }
    /** Fede: Penso non funzioni (andrebbe messo come parametro l'id dell'isola, non l'isola)
     ---> Da spostare in gameController*/

}
