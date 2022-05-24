package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartGame extends ObservableView implements GenericScene{

    @FXML
    private Button buttonStart;

    @FXML
    public void initialize(){
        buttonStart.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickButton);
    }

    /** gestisce il click sul pulsante */
    private void clickButton(Event event){
        buttonStart.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/connect_to_server_scene.fxml");
    }

}
