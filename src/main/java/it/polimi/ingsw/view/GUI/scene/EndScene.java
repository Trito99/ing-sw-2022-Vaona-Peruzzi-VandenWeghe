package it.polimi.ingsw.view.GUI.scene;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class EndScene {

    @FXML
    private Text endMessageText;

    public void updateEndMessageText(Text endMessageText) {
        this.endMessageText = endMessageText;
    }
}
