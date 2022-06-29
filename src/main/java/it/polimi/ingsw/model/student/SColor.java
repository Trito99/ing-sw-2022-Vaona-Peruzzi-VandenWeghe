package it.polimi.ingsw.model.student;

/**
 * Enumeration which identifies all possible student's colors
 * It also identifies all professor's colors
 */
public enum SColor {
    GREEN,
    RED,
    YELLOW,
    PINK,
    BLUE;

    public boolean isColorBlocked;

    /**
     * Handles character card's effect
     * @return true if the color has been selected by the player to be blocked in the influence calculation,
     *          otherwise return false
     */
    public boolean isColorBlocked() {
        return isColorBlocked;
    }

    /**
     * Handles Herbalist's effect
     */
    public void lockColor() {
        isColorBlocked = true;
    }

    /**
     * Handles Herbalist's effect
     */
    public void unlockColor(){
        isColorBlocked = false;
    }

}
