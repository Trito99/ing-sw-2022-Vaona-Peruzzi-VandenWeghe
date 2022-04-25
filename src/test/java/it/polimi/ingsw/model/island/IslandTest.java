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
      int count = 0;

      for(count = 0; count < 12; count++){
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
                  assertNotNull(island.getTowerOnIsland());
                  assertEquals(tw.getTColour(), island.getTowerOnIsland().getTColour());
                  assertEquals(tw.getIdTower(), island.getTowerOnIsland().getIdTower());
                  break;
            }
         }
      }
   }

   /** da fare */
   @Test
   public void InfluenceOnIsland(){
      int count = 0;

      for(count = 0; count < 12; count++){
         IslandCard island = new IslandCard(count);

         players.get(0).setInfluenceOnIsland(6);
         players.get(1).setInfluenceOnIsland(3);
         /** DA CONTROLLARE: la funzione deve poter funzionare anche senza carta effetto*/
         Player winner = island.calculateInfluence(players, CardEffect.CORTIGIANA);
         assertNotNull(winner);
         assertEquals(players.get(0), winner);
         assertEquals(6, winner.getInfluenceOnIsland());
      }
   }

   /** da fare */
   @Test
   public void XCardOnIsland(){
      int count = 0;

      for(count = 0; count < 12; count++){
         IslandCard island = new IslandCard(count);

      }
      return;
   }

}
