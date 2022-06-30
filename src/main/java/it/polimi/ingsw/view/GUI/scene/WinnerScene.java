package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * This Scene Controller is used to tell the player he won the match
 */
public class WinnerScene extends ObservableView implements GenericScene {

    @FXML
    private Button closeButton;

    /**
     * Initialize buttons and panes present in the Scene
     */
    public void initialize(){
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::buttonClose);
    }

    /**
     * Handles the click on the close button
     * @param event the mouse click event
     */
    private void buttonClose(Event event){
        System.exit(0);
    }

}
