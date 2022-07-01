package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Locale;

/**
 * This Scene Controller is used to tell the player he tied the match
 */
public class ColorChoice extends ObservableView implements GenericScene {

    @FXML
    private Button blueColor;

    @FXML
    private Pane blueColorShadow;

    @FXML
    private Button greenColor;

    @FXML
    private Pane greenColorShadow;

    @FXML
    private Button pinkColor;

    @FXML
    private Pane pinkColorShadow;

    @FXML
    private Button playButton;

    @FXML
    private Text playText;

    @FXML
    private Text playText1;

    @FXML
    private Button redColor;

    @FXML
    private Pane redColorShadow;

    @FXML
    private Button yellowColor;

    @FXML
    private Pane yellowColorShadow;

    private SColor color;

    /**
     * Initialize buttons present in the Scene
     */
    public void initialize(){
        hide();
        greenColor.addEventHandler(MouseEvent.MOUSE_CLICKED, this::colorClicked);
        redColor.addEventHandler(MouseEvent.MOUSE_CLICKED, this::colorClicked);
        yellowColor.addEventHandler(MouseEvent.MOUSE_CLICKED, this::colorClicked);
        pinkColor.addEventHandler(MouseEvent.MOUSE_CLICKED, this::colorClicked);
        blueColor.addEventHandler(MouseEvent.MOUSE_CLICKED, this::colorClicked);
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::close);
    }

    /**
     * Handles the click on the color button.
     * @param event the mouse click event.
     */
    private void colorClicked(Event event){
        Object source = event.getSource();
        if (greenColor.equals(source)) {
            hide();
            greenColorShadow.setVisible(true);
            setColor(SColor.GREEN);
        } else if (redColor.equals(source)) {
            hide();
            redColorShadow.setVisible(true);
            setColor(SColor.RED);
        } else if (yellowColor.equals(source)) {
            hide();
            yellowColorShadow.setVisible(true);
            setColor(SColor.YELLOW);
        } else if (pinkColor.equals(source)) {
            hide();
            pinkColorShadow.setVisible(true);
            setColor(SColor.PINK);
        } else if (blueColor.equals(source)) {
            hide();
            blueColorShadow.setVisible(true);
            setColor(SColor.BLUE);
        }
    }

    public SColor getColor() {
        return color;
    }

    @FXML
    public void setColor(SColor color) {
        this.color = color;
    }

    @FXML
    private void close(Event event){
        if(color != null) {
            notifyObserver(obs -> obs.chooseColorToBlock(color.toString().toUpperCase(Locale.ROOT)));
            GuiManager.closeStage(this);
        }
    }

    /**
     * Hides nodes not used yet
     */

    private void hide(){
        greenColorShadow.setVisible(false);
        greenColorShadow.setDisable(true);
        redColorShadow.setVisible(false);
        redColorShadow.setDisable(true);
        yellowColorShadow.setVisible(false);
        yellowColorShadow.setDisable(true);
        pinkColorShadow.setVisible(false);
        pinkColorShadow.setDisable(true);
        blueColorShadow.setVisible(false);
        blueColorShadow.setDisable(true);

    }
}
