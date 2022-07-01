package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.scene.DashboardScene;
import it.polimi.ingsw.view.GUI.scene.GenericScene;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles the change of each Scene in GUI
 */
public class GuiManager extends ObservableView {
    private static Scene scene;
    private static Map<GenericScene, Stage> newStages = new HashMap<>();
    private static GenericScene activeController;
    private static DashboardScene mainController;
    private static Scene mainScene;
    private static Parent mainRoot;

    /** sets the new scene
     *
     * @param observerViewList list of observers
     * @param newScene the new scene to set
     * @param fxml fxml file to load
     * @return
     * @param <T> event T
     */
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

    /** sets the new scene
     *
     * @param observerList list of observer
     * @param fxml file fxml to load
     * @return
     * @param <T> event
     */
    public static <T> T changeRootPane(List<ObserverView> observerList, String fxml) {
        return changeRootPane(observerList, scene, fxml);
    }

    /** sets the new scene
     *
     * @param controller the new controller of the new scene
     * @param fxml file fxml to load
     */
    public static void changeRootPane(GenericScene controller, String fxml) {
        changeRootPane(controller, scene, fxml);
    }

    /** sets the new scene
     *
     * @param controller the new controller of the new scene
     * @param scene the scene to assign at the gui manager
     * @param fxml file fxml to load
     */
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

    /** sets the new scene
     *
     * @param controller the new controller of the new scene
     * @param fxml the fxml file to load
     */
    public static void newStagePane(GenericScene controller, String fxml) {
        try {
            for(GenericScene genericScene : newStages.keySet()){
                if(!(newStages.get(genericScene).isShowing()))
                    newStages.remove(genericScene);
            }
            Stage stage = new Stage();
            newStages.put(controller,stage);
            FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource(fxml + ".fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Close the stage of the controller
     * @param controller controller to close
     */
    public static void closeStage(GenericScene controller){
        newStages.get(controller).close();
    }

    /**changes the Main scene
     *
     * @param observerList list of observer
     * @param <T> event
     */
    public static <T> void changeRootMainScene(List<ObserverView> observerList) {
        T controller;
        if(mainController==null){
            FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/dashboard_scene.fxml"));
            Parent root;
            try {
                root = loader.load();
                controller = loader.getController();
                ((ObservableView) controller).addAllObservers(observerList);
                mainController = (DashboardScene) controller;
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

    /** sets the new scene
     *
     * @param observerList list of observers
     * @param event event
     * @param fxml file fxml to load
     * @return
     * @param <T> event
     */
    public static <T> T changeRootPane(List<ObserverView> observerList, Event event, String fxml) {
        Scene newScene = ((Node) event.getSource()).getScene();
        return changeRootPane(observerList, newScene, fxml);
    }

    /** return the Main scene
     *
     * @return the Main scene
     */
    public static DashboardScene getMainScene(){
        return mainController;
    }
}
