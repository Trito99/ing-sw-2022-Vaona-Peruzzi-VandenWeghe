package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.assistant.DeckAssistant;
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

import static org.junit.Assert.*;

public class IslandTest {
   private Table table;

   @BeforeEach
   public void setup(){
      table = new Table();
      table.addFinalStudents();
      table.generateIslandCards();
      table.generateMotherEarth();
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
      int count = 0;

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
      int count = 0;
      int i = 0;
      Player player = new Player(color, PlayerNumber.PLAYER1);
      IslandCard island = new IslandCard(count);


      assertNotNull(island.towerIsOnIsland());
      assertEquals(false, island.towerIsOnIsland());
      assertNull(island.getTowerOnIsland());

      /**
      for(count = 0; count < 12; count++){
         for(i=0; i<7; i++){
            switch (color){
               case WHITE:
                  Tower tw = new Tower(i, color);
                  island.setTowerOnIsland(player.getPersonalSchool().getTower().get(i));
                  assertNotNull(island.getTowerOnIsland());
                  assertEquals(tw, island.getTowerOnIsland());
                  break;
               case BLACK:
                  break;
               case GREY:
                  break;
            }
         }
      }*/
   }

   /** da fare */
   @Test
   public void InfluenceOnIsland(){
      int count = 0;

      for(count = 0; count < 12; count++){
         IslandCard island = new IslandCard(count);

      }
      return;
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
