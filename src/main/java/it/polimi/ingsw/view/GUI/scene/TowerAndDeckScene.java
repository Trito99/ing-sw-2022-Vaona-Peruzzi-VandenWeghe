package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class TowerAndDeckScene extends ObservableView implements GenericScene {

    @FXML
    public Button nextButton;
    @FXML
    public Button backButton;

    @FXML
    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickNext);
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickBack);
    }

    /** gestisce il click sul pulsante */
    private void clickNext(Event event){
        nextButton.setDisable(true);
    }

    private void clickBack(Event event){
        nextButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/setup_game_scene");
    }

}
