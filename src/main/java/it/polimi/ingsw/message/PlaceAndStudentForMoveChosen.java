package it.polimi.ingsw.message;

public class PlaceAndStudentForMoveChosen extends ClientMessage {
    private String place;
    private int id;

    public PlaceAndStudentForMoveChosen(String nickname, String place, int id) {
        super(nickname, MessageType.PLACE_AND_STUDENT_FOR_MOVE_CHOSEN);
        this.id=id;
        this.place=place;
    }

    public int getId() {
        return id;
    }
    public String getPlace() { return place; }
}
