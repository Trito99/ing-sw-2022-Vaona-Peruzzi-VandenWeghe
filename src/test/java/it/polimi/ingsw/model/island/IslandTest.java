package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IslandTest {
   private Table table;
   private ArrayList<Player> players;

   @BeforeEach
   public void setup(){
      table = new Table();
      players = new ArrayList<>();
      table.addFinalStudents();
      table.generateIslandCards();
      table.generateMotherEarth();

      players.add(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
      players.add(new Player(TColor.BLACK, PlayerNumber.PLAYER2));
   }

   @Test
   public void IslandCard(){
      for(int count = 0; count < 12; count++){
         IslandCard island = new IslandCard(count);

         assertNotNull(island.getIdIsland());
         assertNotNull(island.getStudentOnIsland());
         assertNotNull(island.getMergedIsland());
         assertNull(island.getTowerOnIsland());

         assertEquals(count, island.getIdIsland());
         assertEquals(false, island.towerIsOnIsland());
         assertEquals(1, island.getMergedIsland());
         assertEquals(false, island.isXCardOnIsland());
      }

      //cambio indice
      for(int i=0; i<10; i++){
         IslandCard island = new IslandCard(i);
         assertEquals(i, island.getIdIsland());
         island.setIdIsland(island.getIdIsland()+1);
         assertNotNull(island.getIdIsland());
         assertEquals(i+1, island.getIdIsland());
      }

   }

   @Test
   public void MergedIsland(){
      int i,count = 0;

      for(count = 0; count < 12; count++){
         IslandCard island = new IslandCard(count);
         for(i=0; i<8; i++){
            island.setMergedIsland(i);
            assertNotNull(island.getMergedIsland());
            assertEquals(i, island.getMergedIsland());
         }
      }
   }

   @Test
   public void MotherEarthOnIsland(){
      int count = 0; //ripete su tutte le isole

      for(count = 0; count < 12; count++) {
         IslandCard island = new IslandCard(count);
         assertNotNull(island.getMergedIsland());
         assertEquals(false, island.getMotherEarthOnIsland());

         island.setMotherEarthOnIsland(true);
         assertNotNull(island.getMergedIsland());
         assertEquals(true, island.getMotherEarthOnIsland());
      }
   }


   @ParameterizedTest
   @EnumSource(TColor.class)
   public void TowerOnIsland(TColor color){
      int count = 0; //ripete su tutte le isole
      int i = 0; //indice delle torri in TowerZone
      Player player = new Player(color, PlayerNumber.PLAYER1);
      GameMode gm = GameMode.TWOPLAYERS;
      player.generateSchool(table, gm);
      IslandCard island = new IslandCard(count);

      assertNotNull(island.towerIsOnIsland());
      assertEquals(false, island.towerIsOnIsland());
      assertNull(island.getTowerOnIsland());

      for(count = 0; count < 12; count++){
         for(i=0; i<7; i++){
            switch (color){
               case WHITE:
               case BLACK:
               case GREY:
                  Tower tw = new Tower(i, color);
                  island.setTowerOnIsland(player.getPersonalSchool().getTower().get(i));
                  island.setTowerIsOnIsland(true);
                  assertNotNull(island.getTowerOnIsland());
                  assertNotNull(island.towerIsOnIsland());
                  assertEquals(tw.getTColour(), island.getTowerOnIsland().getTColour());
                  assertEquals(tw.getIdTower(), island.getTowerOnIsland().getIdTower());
                  assertEquals(true, island.towerIsOnIsland());
                  break;
            }
         }
      }
   }

   @Test
   public void InfluenceOnIsland(){

      for(int count = 0; count < 12; count++){
         IslandCard island = new IslandCard(count);

         players.get(0).setInfluenceOnIsland(6);
         players.get(1).setInfluenceOnIsland(3);
         Player winner = island.calculateInfluence(players, CardEffect.EASYMODE);
         assertNotNull(winner);
         assertEquals(players.get(0), winner);
         assertEquals(6, winner.getInfluenceOnIsland());
      }
   }

   @Test
   public void XCardOnIsland(){
      int count = 0;
      int countX = 0;

      for(count = 0; count < 12; count++){
         IslandCard island = new IslandCard(count);
         assertNotNull(island.isXCardOnIsland());
         assertEquals(false, island.isXCardOnIsland());

         for(countX = 0; countX < 4; countX++){
            assertEquals(countX, island.getXCardCounter());
            island.setXCardCounter(island.getXCardCounter() + 1);
            island.setXCardOnIsland(true);
            assertNotNull(island.getXCardCounter());
            assertEquals(countX + 1, island.getXCardCounter());
         }
      }
   }

}
