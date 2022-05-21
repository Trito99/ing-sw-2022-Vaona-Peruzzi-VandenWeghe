package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.scene.GenericScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class GuiManager extends ObservableView {
    private static Scene scene;
    private static GenericScene activeController;
    private static Scene mainScene;
    private static Parent mainRoot;

    public static <T> T changeRootPane(List<ObserverView> observerViewList, Scene newScene, String fxml) {
        T controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource(fxml + ".fxml"));
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



}
