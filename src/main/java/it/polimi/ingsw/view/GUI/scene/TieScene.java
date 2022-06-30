package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class TieScene extends ObservableView implements GenericScene {

    @FXML
    private Button closeButton;

    public void initialize(){
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::buttonClose);
    }

    /**
     * Handles the click on the close button.
     * @param event the mouse click event.
     */
    private void buttonClose(Event event){
        notifyObserver(ObserverView::updateDisconnect);

        System.exit(0);
    }

}
