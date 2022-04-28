package it.polimi.ingsw.model.island;

import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.Prof;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Random;

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
         Player winner = island.calculateInfluence(players, CardEffect.STANDARDMODE);
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

   @RepeatedTest(100)
   public void CalculateInfluenceTest(){
      Random rn = new Random();
      int r;
      Game game= new Game();
      for(int index=0;index<2;index++) { /** For the first two GameModes */
         table.getBag().clear();
         table.getListOfIsland().clear();
         table.generateIslandCards();
         table.generateMotherEarth();
         table.generateBagInit();
         table.extractStudentsInit();
         table.addFinalStudents();
         game.getListOfPlayers().clear();
         game.setGameMode(GameMode.values()[index]);
         for (int i = 0; i < index + 2; i++) {         /** Adds players based on the GameMode */
            game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
            game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
         }
         for (int i = 0; i < index + 2; i++) {      /** Students are positioned randomly in the different Tables */
            for (int s = 1; s < 6; s++) {
               r = rn.nextInt(4) + 1;
               switch (s) {
                  case 1:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getGTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 2:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getRTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 3:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getYTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 4:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getPTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 5:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getBTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
               }
            }
         }
         for (int i = 0; i < index + 2; i++) {    /** Positions the Professors according to the rules */
            game.getListOfPlayers().get(i).getPersonalSchool().winProf(game.getListOfPlayers(), game.getListOfPlayers().get(i), CardEffect.STANDARDMODE);
         }
         for (int i = 0; i < 12; i++) {  /** Moves random number of students on Islands. */
            r = rn.nextInt(4) + 1;
            for (int n = 0; n < r; n++) {
               table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
               table.getBag().remove(0);
            }
            ArrayList<Integer> countInfluence = new ArrayList<>();
            for (Player player : game.getListOfPlayers()) {      /** Calculates manually the influence for each player on that particular island. */
               int s = 0;
               for (Prof prof : player.getPersonalSchool().getProfOfPlayer()) {
                  if (prof.getIsInHall()) {
                     for (Student student : table.getListOfIsland().get(i).getStudentOnIsland()) {
                        if (student.getsColour() == prof.getSColour())
                           s++;
                     }
                  }
               }
               countInfluence.add(s);
            }
            table.getListOfIsland().get(i).calculateInfluence(game.getListOfPlayers(), CardEffect.STANDARDMODE);
            int max = 0, n = 0;
            /** Checks if the influence calculated manually for each player is the same calculated by the function "calculateInfluence"
             *  and finds the maximum between them*/
            for (Player player : game.getListOfPlayers()) {
               assertEquals(countInfluence.get(game.getListOfPlayers().indexOf(player)).intValue(), player.getInfluenceOnIsland());
               if (max < player.getInfluenceOnIsland()) {
                  max = player.getInfluenceOnIsland();
               }
            }
            Player playerWithMaxInfluence = null;
            for (Player player : game.getListOfPlayers()) {    /** Differentiates between the case of victory and tie with n*/
               if (player.getInfluenceOnIsland() == max) {
                  n++;
                  playerWithMaxInfluence = player;
               }
            }
            if (n == 1)   /** Checks if the player selected by the function is the one with the most influence*/
               assertEquals(playerWithMaxInfluence, table.getListOfIsland().get(i).calculateInfluence(game.getListOfPlayers(), CardEffect.STANDARDMODE));
            else if (n > 1)  /** Checks if in case of a tie the function returns null */
               assertNull(table.getListOfIsland().get(i).calculateInfluence(game.getListOfPlayers(), CardEffect.STANDARDMODE));
         }
      }
   }



   @RepeatedTest(100)
   public void BuildTowerOnIslandTest(){
      Random rn = new Random();
      int r;
      Game game= new Game();
      for(int index=0;index<2;index++) { /** For the first two GameModes */
         table.getBag().clear();
         table.getListOfIsland().clear();
         table.generateIslandCards();
         table.generateMotherEarth();
         table.generateBagInit();
         table.extractStudentsInit();
         table.addFinalStudents();
         game.getListOfPlayers().clear();
         game.setGameMode(GameMode.values()[index]);
         for (int i = 0; i < index + 2; i++) {         /** Adds players based on the GameMode */
            game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
            game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
         }
         for (int i = 0; i < index + 2; i++) {      /** Students are positioned randomly in the different Tables */
            for (int s = 1; s < 6; s++) {
               r = rn.nextInt(4) + 1;
               switch (s) {
                  case 1:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getGTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 2:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getRTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 3:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getYTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 4:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getPTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
                  case 5:
                     for (int t = 1; t < r; t++) {
                        game.getListOfPlayers().get(i).getPersonalSchool().getBTable().add(table.getBag().get(0));
                        table.getBag().remove(0);
                     }
                     break;
               }
            }
         }
         for (int i = 0; i < index+2; i++) {    /** Positions the Professors according to the rules */
            game.getListOfPlayers().get(i).getPersonalSchool().winProf(game.getListOfPlayers(),game.getListOfPlayers().get(i), CardEffect.STANDARDMODE);
         }
         for (int i = 0; i < 12; i++) {  /** Moves random number of students on Islands. */
            r = rn.nextInt(4)+1;
            for (int n = 0; n < r; n++) {
               table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
               table.getBag().remove(0);
            }
         }

         table.moveMotherEarth(13-table.getPosMotherEarth());
         for (IslandCard islandCard: table.getListOfIsland()){
            islandCard.buildTowerOnIsland(game.getListOfPlayers(),CardEffect.STANDARDMODE);
            if(islandCard.towerIsOnIsland())
               assertEquals(islandCard.calculateInfluence(game.getListOfPlayers(),CardEffect.STANDARDMODE).getTColor(),islandCard.getTowerOnIsland().getTColour());
            table.moveMotherEarth(1);
         }
      }
   }

}
