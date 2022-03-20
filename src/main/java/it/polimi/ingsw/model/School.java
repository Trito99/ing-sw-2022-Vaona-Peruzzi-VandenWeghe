package it.polimi.ingsw.model;

import java.util.ArrayList;

public class School {

    private ArrayList<Student> entry  = new ArrayList<>(7);
    private ArrayList<Student> GTable = new ArrayList<>();
    private ArrayList<Student> RTable = new ArrayList<>();
    private ArrayList<Student> YTable = new ArrayList<>();
    private ArrayList<Student> PTable = new ArrayList<>();
    private ArrayList<Student> BTable = new ArrayList<>();
    private boolean profGInHall;
    private boolean profRInHall;
    private boolean profYInHall;
    private boolean profPInHall;
    private boolean profBInHall;
    private ArrayList<Tower> tower = new ArrayList<>();

    public ArrayList<Student> getEntry() {
        return this.entry;
    }

    public ArrayList<Student> addStudent() {
        this.entry + 1;     //ho scoperto che non si può usare per array :')
        // ci sarà una notify observer
        // ?? in school o in entry ??
        // SERVE ?
    }

    public ArrayList<Student> moveStudentInHall(){
        //da implementare
    }

    public ArrayList<Student> moveStudentInIsland(){
        //da implementare
    }

    public ArrayList<Student> getGTable(){
        return this.GTable;
    }

    public ArrayList<Student> getRTable(){
        return this.RTable;
    }

    public ArrayList<Student> getYTable(){
        return this.YTable;
    }

    public ArrayList<Student> getPTable(){
        return this.PTable;
    }

    public ArrayList<Student> getBTable(){
        return this.BTable;
    }

    public boolean calculateGInfluence() {
        //da fare
    }

    public boolean calculateRInfluence() {
        //da fare
    }

    public boolean calculateYInfluence() {
        //da fare
    }

    public boolean calculatePInfluence() {
        //da fare
    }

    public boolean calculateBInfluence() {
        //da fare
    }

    public int increaseCoinScore(){
        int coinScore = Player.getCoinScore();
        coinScore = Player.increaseCoinScore();
        return coinScore;
    }

    public boolean getProfGInHall(){
        return this.profGInHall;
    }

    public boolean getProfRInHall(){
        return this.profRInHall;
    }

    public boolean getProfYInHall(){
        return this.profYInHall;
    }

    public boolean getProfPInHall(){
        return this.profPInHall;
    }

    public boolean getProfBInHall(){
        return this.profBInHall;
    }

    public boolean setProfGInHall(){
        if(getProfGInHall()==false){
            return profGInHall()=true;
        }
        if(getProfGInHall()==true){
            return profGInHall()=false;
        }
    }

    public boolean setProfRInHall(){
        if(getProfRInHall()==false){
            return profRInHall()=true;
        }
        if(getProfRInHall()==true){
            return profRInHall()=false;
        }
    }

    public boolean setProfYInHall(){
        if(getProfYInHall()==false){
            return profYInHall()=true;
        }
        if(getProfYInHall()==true){
            return profYInHall()=false;
        }
    }

    public boolean setProfPInHall(){
        if(getProfPInHall()==false){
            return profPInHall()=true;
        }
        if(getProfPInHall()==true){
            return profPInHall()=false;
        }
    }

    public boolean setProfBInHall(){
        if(getProfBInHall()==false){
            return profBInHall()=true;
        }
        if(getProfBInHall()==true){
            return profBInHall()=false;
        }
    }

    public ArrayList<Tower> getTower() {
        return this.tower;
    }

    public ArrayList<Tower> addTower() {
        this.tower + 1;
        // ci sarà una notify observer
        return null;
    }

    public ArrayList<Tower> removeTower() {
        ArrayList<Tower> = ArrayList<Tower> - 1;
        return this.tower;
    }

    public boolean checkTowerIsEmpty() {
        if (this.tower != null)
            return false;
        else
            return true;
        }
    }

}
