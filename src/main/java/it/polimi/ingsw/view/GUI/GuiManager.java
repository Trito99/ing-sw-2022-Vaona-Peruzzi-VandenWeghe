package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.scene.BoardScene;
import it.polimi.ingsw.view.GUI.scene.GenericScene;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class GuiManager extends ObservableView {
    private static Scene scene;
    private static GenericScene activeController;

    private static BoardScene mainController;
    private static Scene mainScene;
    private static Parent mainRoot;

    public static <T> T changeRootPane(List<ObserverView> observerViewList, Scene newScene, String fxml) {
        T controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource(fxml + ".fxml"));
            loader.setLocation(StartGUI.class.getResource(fxml + ".fxml"));
            Parent root = loader.load();
            controller = loader.getController();
            ((ObservableView) controller).addAllObservers(observerViewList);

            activeController = (GenericScene) controller;
            scene = newScene;
            scene.setRoot(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return controller;
    }

    public static <T> T changeRootPane(List<ObserverView> observerList, String fxml) {
        return changeRootPane(observerList, scene, fxml);
    }

    public static void changeRootPane(GenericScene controller, String fxml) {
        changeRootPane(controller, scene, fxml);
    }

    public static void changeRootPane(GenericScene controller, Scene scene, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource(fxml + ".fxml"));

            loader.setController(controller);
            GuiManager.activeController = controller;
            Parent root = loader.load();


            GuiManager.scene = scene;
            GuiManager.scene.setRoot(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static <T> void changeRootMainScene(List<ObserverView> observerList) {
        T controller;
        if(mainController==null){
            FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/board_scene.fxml"));
            Parent root;
            try {
                root = loader.load();
                controller = loader.getController();
                ((ObservableView) controller).addAllObservers(observerList);
                mainController = (BoardScene) controller;
                activeController = (GenericScene) controller;
                scene.setRoot(root);
                mainRoot = root;
                mainScene = scene;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            scene = mainScene;
            activeController = mainController;
            scene.setRoot(mainRoot);
        }
    }

    public static <T> T changeRootPane(List<ObserverView> observerList, Event event, String fxml) {
        Scene newScene = ((Node) event.getSource()).getScene();
        return changeRootPane(observerList, newScene, fxml);
    }

    public static void setScene(String fxml){
        FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource(fxml + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GuiManager.scene.setRoot(root);
    }

    public static Scene getScene() {
        return scene;
    }

    public static BoardScene getMainScene(){
        return mainController;
    }

}
