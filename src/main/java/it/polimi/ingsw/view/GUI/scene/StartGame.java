package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartGame extends ObservableView implements GenericScene{

    @FXML
    private Button startButton;

    @FXML
    private Button rulesButton;

    @FXML
    public void initialize(){
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickButtonStart);
    }

    /** gestisce il click sul pulsante */
    @FXML
    private void clickButtonStart(Event event){
        startButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/connect_to_server_scene");
    }

    /** DA IMPLEMENTARE schermata rules */
    @FXML
    private void clickButtonRules(Event event){
        rulesButton.setDisable(true);
        GuiManager.changeRootPane(observers, event, "/fxml/rules_scene");
    }

}
