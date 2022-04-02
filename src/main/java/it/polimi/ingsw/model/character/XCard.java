package it.polimi.ingsw.model.character;

public class XCard {

    private boolean isOnIsland;
    private int position;
    private int idXCard;

    public XCard(boolean isOnIsland, int position, int idXCard) {
        this.isOnIsland = false;
        this.position = position;
        this.idXCard = idXCard;
    }

    public boolean getIsOnIsland() {
        return isOnIsland;
    }

    public int getPosition() {
        return position;
    }

    public int getIdXCard() {
        return idXCard;
    }
}
