package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.network.ClientMessanger;
import it.polimi.ingsw.view.GUI.scene.StartGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class StartGUI extends Application {
    private GridPane rootLayout;
    private Stage primaryStage;

    public static void main(){
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Eriantys");
        //this.primaryStage.getIcons().add(new Image("calamaio.png"));
        this.primaryStage.setResizable(true);
        this.primaryStage.setMaximized(true);
        this.primaryStage.setFullScreen(true);

        GUI view = new GUI();
        ClientMessanger clientMessanger = new ClientMessanger(view);
        view.addObserver(clientMessanger);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start_game_scene.fxml"));
        //loader.setLocation(getClass().getResource("/fxml/start_game_scene.fxml"));

        //Parent rootLayout = null;
        try {
            rootLayout = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        StartGame controller = loader.getController();
        controller.addObserver(clientMessanger);
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);

        stage.show();
    }

}
