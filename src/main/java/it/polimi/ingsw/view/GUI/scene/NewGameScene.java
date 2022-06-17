package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;


import java.util.Locale;

public class NewGameScene extends ObservableView implements GenericScene {

    @FXML
    public Button nextButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button standardButton;
    @FXML
    public Button expertButton;
    @FXML
    public Button threePlayersButton;
    @FXML
    public Button coopButton;
    @FXML
    public Button twoPlayersButton;

    @FXML
    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickNext);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickExit);
    }

    /** gestisce il click sul pulsante */
    private void clickNext(Event event){
        nextButton.setDisable(true);
        //String g = (String) difficulty.getValue();
        //Difficulty difficulty1 = Difficulty.valueOf(g.toUpperCase(Locale.ROOT)+"MODE");
        //notifyObserver(obs -> obs.choosePlayersNumberAndDifficulty((Integer) numberOfPlayers.getValue(),difficulty1) );
    }


    /** DA IMPLEMENTARE la exit */
    private void clickExit(Event event){
        nextButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/setup_game_scene");
    }


}
