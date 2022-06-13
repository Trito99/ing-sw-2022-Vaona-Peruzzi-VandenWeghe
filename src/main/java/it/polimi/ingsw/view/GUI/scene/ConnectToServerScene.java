package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static java.lang.System.out;

/** scena che gestisce la connessione al server */

public class ConnectToServerScene extends ObservableView implements GenericScene {

    /**@FXML
    private Label details;
    @FXML
    private Label ipAddressLabel;
    @FXML
    private Label portNumberLabel; */

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView connectBackground;
    @FXML
    private TextField ipAddressField;
    @FXML
    private TextField portNumberField;
    @FXML
    private Button connectButton;
    @FXML
    private Button exitButton;

    @FXML
    public void initialize(){
        connectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickConnect);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickExit);
    }

    private void clickConnect(Event event){

        try{
            ipAddressField.setDisable(true);
            portNumberField.setDisable(true);
            String address = ipAddressField.getText();
            Integer chosenPort = Integer.parseInt(portNumberField.getText());

            connectButton.setDisable(true);

            notifyObserver(obs -> obs.updateConnect(address, chosenPort));
        }
        catch (Exception e) {
            notifyObserver(obs -> obs.updateConnect("1.0.0.0", 1));
        }

    }

    private void clickExit(Event event){
        exitButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/start_game_scene");

        /**exitButton.setDisable(true);
        notifyObserver(ObserverView :: updateDisconnect);
        System.exit(0);*/
    }

}
