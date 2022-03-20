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
        return entry;
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
        return true;
    }

    public boolean calculateRInfluence() {
        //da fare
        return true;
    }

    public boolean calculateYInfluence() {
        //da fare
        return true;
    }

    public boolean calculatePInfluence() {
        //da fare
        return true;
    }

    public boolean calculateBInfluence() {
        //da fare
        return true;
    }

    public int increaseCoinScore(){
        Player.getCoinScore();
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

    public boolean setProfGInHall(boolean profGInHall){
        return this.profGInHall = profGInHall;
    }

    public boolean setProfRInHall(boolean profRInHall){
        return this.profRInHall = profRInHall;
    }

    public boolean setProfYInHall(boolean profYInHall){
       return this.profYInHall = profYInHall;
    }

    public boolean setProfPInHall(boolean profPInHall){
        return this.profPInHall = profPInHall;
    }

    public boolean setProfBInHall(boolean profBInHall){
        return this.profBInHall = profBInHall;
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
        return this.tower == null;
    }





}
