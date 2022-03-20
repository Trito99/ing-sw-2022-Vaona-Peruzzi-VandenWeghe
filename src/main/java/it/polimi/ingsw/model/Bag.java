package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Bag {

    private ArrayList<Student> bag = new ArrayList<>(130);;

    public ArrayList<Student> shuffleBag(){
        //da fare
        return this.bag;
    }

    public ArrayList<Student> extractStudent(){
        //da fare
        return this.bag;
    }

    public boolean CheckIsEmpty(){
        if (this.bag != null){
            return 0;
        }
    }

}
