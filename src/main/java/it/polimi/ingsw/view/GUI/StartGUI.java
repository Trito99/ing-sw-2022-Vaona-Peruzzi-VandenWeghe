package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.network.ClientMessanger;
import javafx.application.Application;
import javafx.stage.Stage;


public class StartGUI extends Application {

    @Override
    public void start(Stage stage) {
        GUI view = new GUI();
        ClientMessanger clientMessanger = new ClientMessanger(view);
        view.addObserver(clientMessanger);
        //...
    }

}
