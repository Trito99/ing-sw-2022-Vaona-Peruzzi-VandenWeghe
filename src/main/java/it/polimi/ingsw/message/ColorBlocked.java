package it.polimi.ingsw.message;

/**
 * Extends Client Message
 * Notifies the color blocked by the player
 * Used in Expert Mode
 */
public class ColorBlocked extends ClientMessage {
    private String color;

    public ColorBlocked(String nickname, String color) {
        super(nickname, MessageType.COLOR_CHOSEN);
        this.color = color;
    }

    public String getColor() { return color; }
}
