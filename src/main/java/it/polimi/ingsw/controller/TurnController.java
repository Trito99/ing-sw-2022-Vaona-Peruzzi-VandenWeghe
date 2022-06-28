package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;

import java.util.*;

import static java.util.Comparator.*;

public class TurnController {
    private HashMap<String, Boolean> activePlayer;
    private String playingPlayer;

    private ArrayList<String> playerOrderByName;
    private ArrayList<Player> playerOrder;
    private ArrayList<String> newPlayerOrderByName;
    private ArrayList<Player> newPlayerOrder;


    /**
     * TurnController constructor
     * @param gameController associeted to a turnController
     */
    public TurnController(GameController gameController){
        playingPlayer = YoungestPlayer(gameController.getGameSession().getListOfPlayers()).getNickname();
        playerOrder = new ArrayList<>();
        playerOrderByName = new ArrayList<>();
        newPlayerOrderByName = new ArrayList<>();
        newPlayerOrder = new ArrayList<>();
        activePlayer= new HashMap<>();
        for(int i = 0; i < gameController.getAllVirtualView().size(); i++){
            String nickname = gameController.getGameSession().getListOfPlayers().get(i).getNickname();
            activePlayer.put(nickname, true);
            playerOrderByName.add(nickname);
            playerOrder.add(gameController.getGameSession().getListOfPlayers().get(i));
        }
    }

    /**
     * Identifies the youngest player whom, in the first round, moves first (planning phase)
     * @param listOfPlayers list of the players of the match
     * @return the youngest player of the match.
     */
    private Player YoungestPlayer(ArrayList<Player> listOfPlayers) {

        Player youngestPlayer = listOfPlayers.get(0);

        for (Player p : listOfPlayers){
            if (youngestPlayer.getPlayerDate().compareTo(p.getPlayerDate()) == -1 || youngestPlayer.getPlayerDate().compareTo(p.getPlayerDate()) == 0)
                youngestPlayer = p;
        }
        return youngestPlayer;
    }


    /**
     * Changes the active player
     * @param playerOrderByName list of the nickname of the players
     */
    public void nextPlayer(ArrayList<String> playerOrderByName){
        int player;
        if((playerOrderByName.indexOf(playingPlayer)+1)>(playerOrderByName.size()-1))
            player = 0;
        else
            player = playerOrderByName.indexOf(playingPlayer)+1;
        playingPlayer = playerOrderByName.get(player);
    }

    /**
     * In Action phase, the player turns are in ascendant order of their turnValue (value played with the assistant card)
     * This function change the order of the players for each turn
     */
    public void changeOrder(){
        ArrayList<Player>  NewPlayerOrder = (ArrayList<Player>) playerOrder.clone();
        newPlayerOrderByName.clear();
        NewPlayerOrder.sort(comparingInt(o -> o.getTrash().getTurnValue()));
        for(Player player : NewPlayerOrder)
            newPlayerOrderByName.add(player.getNickname());
        newPlayerOrder = NewPlayerOrder;
    }

    /**
     * @return active player's nickname
     */
    public String getActivePlayer() {
        return playingPlayer;
    }

    /**
     * @return a list with player's nicknames when the turn starts (before having played an assistant card)
     */
    public ArrayList<String> getPlayerOrderByName() {return playerOrderByName;}

    /**
     * @return a list with player's nicknames with the new order (after having played an assistant card)
     */
    public ArrayList<String> getNewPlayerOrderByName() {return newPlayerOrderByName;}

    /**
     * Sets the nickname of the player who's going to play
     * @param nickname of the player who's next to play
     */
    public void setPlayingPlayer(String nickname){
        this.playingPlayer = nickname; }

}
