package it.polimi.ingsw.message;

public class EndGame extends ClientMessage {

     public EndGame(String nickname) {
         super(nickname, MessageType.END);
     }

 }

