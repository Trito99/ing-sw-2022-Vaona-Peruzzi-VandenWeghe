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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }


}
