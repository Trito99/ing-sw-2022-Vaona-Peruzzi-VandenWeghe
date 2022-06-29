package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.network.ClientMessanger;
import it.polimi.ingsw.view.GUI.scene.StartGameScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Starts the GUI
 */
public class StartGUI extends Application {

    /**
     * Starts the GUI
     */
    @Override
    public void start(Stage stage) {

        GUI view = new GUI();
        ClientMessanger clientMessanger = new ClientMessanger(view);
        view.addObserver(clientMessanger);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/start_game_scene.fxml"));

        Parent rootLayout = null;
        try {
            rootLayout = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        StartGameScene controller = loader.getController();
        controller.addObserver(clientMessanger);
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);

        stage.setTitle("Eriantys");
        Image icon = new Image(getClass().getResourceAsStream("/images/Logo_Eriantys.png"));
        stage.getIcons().add(icon);
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.setFullScreen(false);

        stage.show();
    }

}
