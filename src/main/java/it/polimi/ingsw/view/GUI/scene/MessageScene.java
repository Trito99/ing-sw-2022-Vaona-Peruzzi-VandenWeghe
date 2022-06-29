package it.polimi.ingsw.view.GUI.scene;

import javafx.scene.control.Alert;

/**
 * This Scene Controller is used to show a generic message
 */
public class MessageScene {

    /**
     * Shows a message
     * @param header text in header
     * @param title text in title
     * @param message text message
     */
    public static void display(String header, String title, String message){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(message);
        errorAlert.setTitle(title);
        errorAlert.showAndWait();
    }

}
