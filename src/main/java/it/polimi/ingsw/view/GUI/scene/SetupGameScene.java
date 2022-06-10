package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/** scena che chiede al giocatore a quale lobby connettersi, chiedendo username, data di nascita, gameId */

public class SetupGameScene extends ObservableView implements GenericScene {
    @FXML
    private Button buttonNext;
    @FXML
    private Button exit;
    @FXML
    public void initialize(){
        buttonNext.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickButton);
        //exit.addEventHandler(MouseEvent.MOUSE_CLICKED, this::closeGui);

    }

    /** gestisce il click sul pulsante */
    @FXML
    private void clickButton(Event event){
        buttonNext.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/new_connect_to_server_scene.fxml");
    }

    @FXML
    private void closeGui(){
        notifyObserver(ObserverView:: updateDisconnect);
        System.exit(0);
    }
}
