package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


import static java.lang.System.out;

/** scena che gestisce la connessione al server */

public class ConnectToServerScene extends ObservableView implements GenericScene {

    @FXML
    private ImageView connectBackground;
    @FXML
    private Button connectButton;
    @FXML
    private Text connectText;
    @FXML
    private Button exitButton;
    @FXML
    private Text exitText;
    @FXML
    private TextField ipAddressField;
    @FXML
    private Text ipText1;
    @FXML
    private TextField portNumberField;
    @FXML
    private Text portText;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Text titleText;

    @FXML
    public void initialize(){
        connectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickConnect);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickExit);
    }

    private void clickConnect(Event event){
        try {
            ipAddressField.setDisable(true);
            portNumberField.setDisable(true);
            String address = ipAddressField.getText();
            Integer chosenPort = Integer.parseInt(portNumberField.getText());

            connectButton.setDisable(true);

            notifyObserver(obs -> obs.updateConnect(address, chosenPort));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "WRONG INPUT", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                ipAddressField.setDisable(false);
                ipAddressField.clear();
                portNumberField.setDisable(false);
                portNumberField.clear();
            }
        }
    }

    private void clickExit(Event event){
        exitButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/start_game_scene");
    }

}
