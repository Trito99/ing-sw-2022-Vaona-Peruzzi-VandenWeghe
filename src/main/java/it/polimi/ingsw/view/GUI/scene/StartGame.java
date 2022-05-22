package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartGame extends ObservableView implements GenericScene{

    @FXML
    private Button btmNext;


    /**
     * Handles the click on the Next button.
     * @param event the mouse click event.
     */
    private void onPlayBtnClick(Event event){
        btmNext.setDisable(true);

        //GuiManager.changeRootPane(observers, event,"/fxml/connect_to_server_scene.fxml");
    }

}
