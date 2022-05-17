package it.polimi.ingsw.message;

import java.util.GregorianCalendar;
 public class EndGame extends ClientMessage {
     private String gameId;

     public EndGame(String nickname, String gameId) {
         super(nickname, MessageType.END);
         this.gameId = gameId;
     }

     public String getGameId() {
         return gameId;
     }
 }

