package it.polimi.ingsw.model.student;

public enum SColor {
    GREEN,
    RED,
    YELLOW,
    PINK,
    BLUE;

    public boolean isColorBlocked;

    public boolean isColorBlocked() {
        return isColorBlocked;
    }

    public void lockColor() {
        isColorBlocked = true;
    }

    public void unlockColor(){
        isColorBlocked = false;

    }

}
