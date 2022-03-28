package it.polimi.ingsw.view;

import java.util.Observable;
import java.util.Observer;

public class ModelView extends Observable implements Observer {

    private Model modelCopy;


    @Override
    public void update(Observable o, Object arg) {
        if(!(o instanceof Model)){
            throw new IllegalArgumentException();
        }
        modelCopy=((Model)o).clone();
        setChanged();
        notifyObservers();

    }
}
