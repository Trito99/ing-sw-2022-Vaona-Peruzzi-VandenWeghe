package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class TurnController {
    private HashMap<String, Boolean> activePlayer;
    private String playingPlayer;

    private ArrayList<String> playerOrderByName;
    private ArrayList<Player> playerOrder;
    private ArrayList<String> newPlayerOrderByName;
    private ArrayList<Player> newPlayerOrder;

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

    /** (At the first round, in the planning phase, the youngest player moves first.)
     *
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


    /** changes the active player
     *
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

    /** in the Action phase, the player turns are in ascendant order of their turnValue (value played with the assistant card).
     *
     */
    public void changeOrder(){
        ArrayList<Player>  NewPlayerOrder = (ArrayList<Player>) playerOrder.clone();
        newPlayerOrderByName.clear();
        Collections.sort(NewPlayerOrder, (o1, o2) -> Integer.valueOf(o1.getTrash().getTurnValue()).compareTo(o2.getTrash().getTurnValue()));
        for(Player player : NewPlayerOrder)
            newPlayerOrderByName.add(player.getNickname());
        newPlayerOrder = NewPlayerOrder;
    }

    public String getActivePlayer() {
        return playingPlayer;
    }
    public ArrayList<String> getPlayerOrderByName() {return playerOrderByName;}
    public ArrayList<String> getNewPlayerOrderByName() {return newPlayerOrderByName;}
    public void setPlayingPlayer(String nickname){ this.playingPlayer=nickname; }
}
