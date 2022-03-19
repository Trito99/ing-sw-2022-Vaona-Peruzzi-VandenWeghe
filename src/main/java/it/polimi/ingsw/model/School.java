package it.polimi.ingsw.model;

import java.util.ArrayList;

public class School {

    private ArrayList<Student> Entry;
    private ArrayList<Student> GTable;
    private ArrayList<Student> RTable;
    private ArrayList<Student> YTable;
    private ArrayList<Student> PTable;
    private ArrayList<Student> BTable;
    private boolean ProfGInHall;
    private boolean ProfRInHall;
    private boolean ProfYInHall;
    private boolean ProfPInHall;
    private boolean ProfBInHall;
    private ArrayList<Tower> Tower;

    public ArrayList<Student> getEntry() {
        return this.Entry;
    }

    public ArrayList<Student> addStudent() {
        ArrayList<Student> = ArrayList<Student> + 1;
        // implementare lo scan dei nuovi studenti
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
        Player.CoinScore +1 ;
    }

    public boolean getProfGInHall(){
        return this.ProfGInHall;
    }

    public boolean getProfRInHall(){
        return this.ProfRInHall;
    }

    public boolean getProfYInHall(){
        return this.ProfYInHall;
    }

    public boolean getProfPInHall(){
        return this.ProfPInHall;
    }

    public boolean getProfBInHall(){
        return this.ProfBInHall;
    }

    public boolean setProfGInHall(){
        if(getProfGInHall()==false){
            return ProfGInHall()=true;
        }
        if(getProfGInHall()==true){
            return ProfGInHall()=false;
        }
    }

    public boolean setProfRInHall(){
        if(getProfRInHall()==false){
            return ProfRInHall()=true;
        }
        if(getProfRInHall()==true){
            return ProfRInHall()=false;
        }
    }

    public boolean setProfYInHall(){
        if(getProfYInHall()==false){
            return ProfYInHall()=true;
        }
        if(getProfYInHall()==true){
            return ProfYInHall()=false;
        }
    }

    public boolean setProfPInHall(){
        if(getProfPInHall()==false){
            return ProfPInHall()=true;
        }
        if(getProfPInHall()==true){
            return ProfPInHall()=false;
        }
    }

    public boolean setProfBInHall(){
        if(getProfBInHall()==false){
            return ProfBInHall()=true;
        }
        if(getProfBInHall()==true){
            return ProfBInHall()=false;
        }
    }

    public ArrayList<Tower> getTower() {
        return this.Tower;
    }

    public ArrayList<Tower> addTower() {
        ArrayList<Tower> = ArrayList<Tower> + 1;
        return this.Tower;
    }

    public ArrayList<Tower> removeTower() {
        ArrayList<Tower> = ArrayList<Tower> - 1;
        return this.Tower;
    }

    public boolean checkTowerIsEmpty() {
        if (this.Tower != null)
            return false;
        else
            return true;
        }
    }

}
