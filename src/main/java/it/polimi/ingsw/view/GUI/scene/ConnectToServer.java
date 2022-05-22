package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** scena che gestisce la connessione al server */

public class ConnectToServer extends ObservableView implements GenericScene {

    @FXML
    private TextField ip_address_field;

    @FXML
    private TextField port_number_field;

    @FXML
    private Button submit_button;

    @FXML
    public void initialize(){
        submit_button.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onConnectBtm);
    }

    private void onConnectBtm(Event event){
        ip_address_field.setDisable(true);
        port_number_field.setDisable(true);
        String address = ip_address_field.getText();
        Integer chosenPort = Integer.parseInt(port_number_field.getText());
        submit_button.setDisable(true);

        notifyObserver(obs -> obs.updateConnect(address, chosenPort));
    }
}
