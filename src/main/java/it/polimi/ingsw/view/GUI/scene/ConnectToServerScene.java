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

/** scena che gestisce la connessione al server */

public class ConnectToServerScene extends ObservableView implements GenericScene {

    /**@FXML
    private Label details;
    @FXML
    private Label ipAddressLabel;
    @FXML
    private Label portNumberLabel; */

    @FXML
    private AnchorPane mainPane;
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

    @FXML
    private void clickConnect(Event event){
        ipAddressField.setDisable(true);
        portNumberField.setDisable(true);

        String address = ipAddressField.getText();
        Integer chosenPort = Integer.parseInt(portNumberField.getText());

        connectButton.setDisable(true);

        notifyObserver(obs -> obs.updateConnect(address, chosenPort));

        /**
        // carica scena successiva
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/setup_game_scene"));

        // oppure
        GuiManager.changeRootPane(observers, event,"/fxml/setup_game_scene");
        */
    }


    @FXML
    private void clickExit(Event event){
        exitButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/start_game_scene");

        /**exitButton.setDisable(true);
        notifyObserver(ObserverView :: updateDisconnect);
        System.exit(0);*/
    }

}
