package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** scena che gestisce la connessione al server */

public class ConnectToServer extends ObservableView implements GenericScene {

    @FXML
    private TextField ipAddressField;

    @FXML
    private TextField portNumberField;

    @FXML
    private Button connectButton;

    @FXML
    public void initialize(){
        connectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickConnect);
    }

    @FXML
    private void clickConnect(Event event){
        ipAddressField.setDisable(true);
        portNumberField.setDisable(true);

        String address = ipAddressField.getText();
        Integer chosenPort = Integer.parseInt(portNumberField.getText());

        connectButton.setDisable(true);

        notifyObserver(obs -> obs.updateConnect(address, chosenPort));

        /** carica scena successiva */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/setup_game_scene.fxml"));
    }

    @FXML
    private void closeGui(){
        notifyObserver(ObserverView :: updateDisconnect);
        System.exit(0);
    }
}
