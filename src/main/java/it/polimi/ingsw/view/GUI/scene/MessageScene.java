package it.polimi.ingsw.view.GUI.scene;

import javafx.scene.control.Alert;

/** mostra un messaggio generico */

public class MessageScene {
    public static void display(String header, String title, String message){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(message);
        errorAlert.setTitle(title);
        errorAlert.showAndWait();
    }

}
