package it.polimi.ingsw.view;

import it.polimi.ingsw.model.game.Game;

import java.util.Observable;
import java.util.Observer;

public class ModelView extends Observable implements Observer {

    private Game modelCopy;


    @Override
    public void update(Observable o, Object arg) {
        if(!(o instanceof Game)){
            throw new IllegalArgumentException();
        }
        modelCopy=((Game)o).clone();
        setChanged();
        notifyObservers();

    }
}
