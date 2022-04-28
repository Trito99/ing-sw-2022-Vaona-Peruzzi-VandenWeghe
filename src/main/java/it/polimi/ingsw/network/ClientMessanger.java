package it.polimi.ingsw.network;

import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMessanger implements ObserverView {
    private String nickname;
    private View view;
    private ExecutorService queue;
    private ClientSocket client;
    private String lobby;

    public ClientMessanger(View view){
        this.view = view;
        queue = Executors.newSingleThreadExecutor();
    }
}
