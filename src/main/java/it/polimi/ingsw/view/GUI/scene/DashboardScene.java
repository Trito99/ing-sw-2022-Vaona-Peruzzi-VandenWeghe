package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.*;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.CardsController.CharacterCardController;
import it.polimi.ingsw.view.GUI.GuiManager;
import it.polimi.ingsw.view.GUI.StartGUI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This Scene Controller is used to manage the main scene of the game
 */
public class DashboardScene extends ObservableView implements GenericScene {
    private Map<String,SchoolController> schoolControllersMap = new HashMap<>();
    private CloudCards cloudController;
    private SchoolController personalSchoolController;
    private CharacterCard cardSelected = null;
    private Map<CardEffect,CharacterCardController> characterCardControllerMap = new HashMap<>();
    private Image temp = null;
    private static Map<String, String> assistantCardMap;
    static {
        assistantCardMap = new HashMap<>();
        assistantCardMap.put("lion", "/images/assistantCards/assistant1.png");
        assistantCardMap.put("ostrich", "/images/assistantCards/assistant2.png");
        assistantCardMap.put("cat", "/images/assistantCards/assistant3.png");
        assistantCardMap.put("falcon", "/images/assistantCards/assistant4.png");
        assistantCardMap.put("fox", "/images/assistantCards/assistant5.png");
        assistantCardMap.put("lizard", "/images/assistantCards/assistant6.png");
        assistantCardMap.put("octopus", "/images/assistantCards/assistant7.png");
        assistantCardMap.put("dog", "/images/assistantCards/assistant8.png");
        assistantCardMap.put("elephant", "/images/assistantCards/assistant9.png");
        assistantCardMap.put("turtle", "/images/assistantCards/assistant10.png");
    }
    private ViewDeckScene assistantDeck;
    private  Table table;
    private GameMode gameMode;
    private Difficulty difficulty;
    private boolean planning = false, actionStudent = false, actionMother = false, actionCloud = false;
    private Map<Pane, ArrayList<Integer>> islandListOfStudentsIdMap = new HashMap<>();
    private Map<Pane,Integer> islandIdMap = new HashMap<>();
    private int studentDestinationIslandId, motherDestinationIslandId, islandMother, maxSteps;


    @FXML
    private Rectangle Bridge10_11;

    @FXML
    private Rectangle Bridge11_12;

    @FXML
    private Rectangle Bridge12_1;

    @FXML
    private Rectangle Bridge1_2;

    @FXML
    private Rectangle Bridge2_3;

    @FXML
    private Rectangle Bridge3_4;

    @FXML
    private Rectangle Bridge4_5;

    @FXML
    private Rectangle Bridge5_6;

    @FXML
    private Rectangle Bridge6_7;

    @FXML
    private Rectangle Bridge7_8;

    @FXML
    private Rectangle Bridge8_9;

    @FXML
    private Rectangle Bridge9_10;

    @FXML
    private ImageView ImageTrashPersonal;

    @FXML
    private Pane PaneCoinScore;

    @FXML
    private Pane PaneIsland1;

    @FXML
    private Pane PaneIsland10;

    @FXML
    private Pane PaneIsland11;

    @FXML
    private Pane PaneIsland12;

    @FXML
    private Pane PaneIsland2;

    @FXML
    private Pane PaneIsland3;

    @FXML
    private Pane PaneIsland4;

    @FXML
    private Pane PaneIsland5;

    @FXML
    private Pane PaneIsland6;

    @FXML
    private Pane PaneIsland7;

    @FXML
    private Pane PaneIsland8;

    @FXML
    private Pane PaneIsland9;

    @FXML
    private Pane PaneTeamMate;

    @FXML
    private Pane PaneXCard1;

    @FXML
    private Pane PaneXCard10;

    @FXML
    private Pane PaneXCard11;

    @FXML
    private Pane PaneXCard12;

    @FXML
    private Pane PaneXCard2;

    @FXML
    private Pane PaneXCard3;

    @FXML
    private Pane PaneXCard4;

    @FXML
    private Pane PaneXCard5;

    @FXML
    private Pane PaneXCard6;

    @FXML
    private Pane PaneXCard7;

    @FXML
    private Pane PaneXCard8;

    @FXML
    private Pane PaneXCard9;

    @FXML
    private Text TextCoinscore;

    @FXML
    private Text TextTeamMate;

    @FXML
    private VBox characterCardLayout;

    @FXML
    private Pane characterPane;

    @FXML
    private Pane cloudPane;

    @FXML
    private ImageView coinImagePersonal;

    @FXML
    private ImageView coinImageTable;

    @FXML
    private Pane coinPanePersonal;

    @FXML
    private Pane coinPaneTable;

    @FXML
    private Text coinTextPersonal;

    @FXML
    private Text coinTextTable;

    @FXML
    private Text coinTextTable1;

    @FXML
    private Text coinTextTable11;

    @FXML
    private Text coinTextTable12;

    @FXML
    private Text coinTextTable121;

    @FXML
    private Text coinTextTable1211;

    @FXML
    private Text coinTextTable1212;

    @FXML
    private Text coinTextTable1213;

    @FXML
    private Text coinTextTable12131;

    @FXML
    private Text coinTextTable12132;

    @FXML
    private Text coinTextTable12133;

    @FXML
    private Text coinTextTable12134;

    @FXML
    private Text coinTextTable12135;

    @FXML
    private Button deckButton;

    @FXML
    private ImageView deckLogo;

    @FXML
    private Button doneButton;

    @FXML
    private Text doneText;

    @FXML
    private ImageView island0;

    @FXML
    private ImageView island1;

    @FXML
    private ImageView island10;

    @FXML
    private ImageView island11;

    @FXML
    private ImageView island2;

    @FXML
    private ImageView island3;

    @FXML
    private ImageView island4;

    @FXML
    private ImageView island5;

    @FXML
    private ImageView island6;

    @FXML
    private ImageView island7;

    @FXML
    private ImageView island8;

    @FXML
    private ImageView island9;

    @FXML
    private Pane islandPane;

    @FXML
    private ImageView motherEarth1;

    @FXML
    private ImageView motherEarth10;

    @FXML
    private ImageView motherEarth11;

    @FXML
    private ImageView motherEarth12;

    @FXML
    private ImageView motherEarth2;

    @FXML
    private ImageView motherEarth3;

    @FXML
    private ImageView motherEarth4;

    @FXML
    private ImageView motherEarth5;

    @FXML
    private ImageView motherEarth6;

    @FXML
    private ImageView motherEarth7;

    @FXML
    private ImageView motherEarth8;

    @FXML
    private ImageView motherEarth9;

    @FXML
    private Button otherSchoolButton;

    @FXML
    private Pane schoolPane;

    @FXML
    private Pane schoolPane1;

    @FXML
    private Pane schoolPane11;

    @FXML
    private Pane sxPane;

    @FXML
    private Text textCoinsOnTable;

    @FXML
    private ImageView tower1;

    @FXML
    private ImageView tower10;

    @FXML
    private ImageView tower11;

    @FXML
    private ImageView tower12;

    @FXML
    private ImageView tower2;

    @FXML
    private ImageView tower3;

    @FXML
    private ImageView tower4;

    @FXML
    private ImageView tower5;

    @FXML
    private ImageView tower6;

    @FXML
    private ImageView tower7;

    @FXML
    private ImageView tower8;

    @FXML
    private ImageView tower9;

    @FXML
    private ImageView trashLogo;

    @FXML
    private Pane trashPanePersonalSchool;

    @FXML
    private Pane turnPane;

    @FXML
    private Text turnText;

    @FXML
    private ImageView xCard1;

    @FXML
    private ImageView xCard10;

    @FXML
    private ImageView xCard11;

    @FXML
    private ImageView xCard12;

    @FXML
    private ImageView xCard2;

    @FXML
    private ImageView xCard3;

    @FXML
    private ImageView xCard4;

    @FXML
    private ImageView xCard5;

    @FXML
    private ImageView xCard6;

    @FXML
    private ImageView xCard7;

    @FXML
    private ImageView xCard8;

    @FXML
    private ImageView xCard9;

    /**
     * Updates Table including the islands
     * @param table of the match
     */
    public void updateTable(Table table){
        this.table = table;
        cloudController.updateStudents(table.getCloudNumber());
        updateIslands(table.getListOfIsland());

    }

    /**
     * Updates value of coins on table and shows it
     * @param coins on table
     */
    public void updateCoinOnTableAndCharacterCards(int coins, ArrayList<CharacterCard> characterCards){
        coinTextTable.setText(String.valueOf(coins));
        for(CharacterCard characterCard : characterCards){
            characterCardControllerMap.get(characterCard.getCardEffect()).updateStudentsCharacterCard(characterCard);
        }
    }
    /**
     * Shows the number of the round and which players turn it is
     * @param message with the info
     */
    public void updateRoundText(String message){
        turnText.setText(message);
        turnText.setVisible(true);
    }

    public void updateTurn(String message){
        turnText.setText(message);
    }

    /**
     * Updates players personal school
     * @param controller of players school
     * @param gameMode game mode of the match
     * @param teamMate name of the teammate
     * @throws IOException
     */
    public void updatePersonalSchool(SchoolController controller, GameMode gameMode, String teamMate) throws IOException {
        personalSchoolController = controller;
        personalSchoolController.addAllObservers(observers);
        FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader.setController(controller);
        AnchorPane personalSchool = loader.load();
        schoolPane.getChildren().setAll(personalSchool);
        if(controller.getTrash()!=null) {
            trashLogo.setVisible(false);
            ImageTrashPersonal.setVisible(true);
            ImageTrashPersonal.setImage(new Image(assistantCardMap.get(controller.getTrash().getAssistantName())));
        }else
            ImageTrashPersonal.setVisible(false);
        if(difficulty.equals(Difficulty.EXPERTMODE))
            coinTextPersonal.setText(String.valueOf(controller.getCoinsOfPlayer()));
        if(!gameMode.equals(GameMode.COOP)){
            PaneTeamMate.setVisible(false);
            PaneTeamMate.setDisable(true);
            TextTeamMate.setDisable(true);
            TextTeamMate.setVisible(false);
        }
        else
            TextTeamMate.setText("TeamMate: " + teamMate);
    }

    /**
     * Initializes Table of the match
     * Places Cloud cards and Character cards (if Expert Mode)
     * @param difficulty mode of the match
     * @param table of the match
     * @throws IOException
     */
    public void initializeTable(Difficulty difficulty, Table table) throws IOException {
        this.difficulty = difficulty;
        hide(table);
        updateIslands(table.getListOfIsland());

        switch(table.getCloudNumber().size()){
            case 2:
                FXMLLoader loader2 = new FXMLLoader(StartGUI.class.getResource("/fxml/cloudCards_2Players.fxml"));
                cloudController = new CloudCards();
                cloudController.setCloudCard(table.getCloudNumber());
                loader2.setController(cloudController);
                AnchorPane clouds2 = loader2.load();
                cloudPane.getChildren().addAll(clouds2);
                cloudController.addAllObservers(observers);
                break;
            case 3:
                FXMLLoader loader3 = new FXMLLoader(StartGUI.class.getResource("/fxml/cloudCards_3Players.fxml"));
                cloudController = new CloudCards();
                cloudController.setCloudCard(table.getCloudNumber());
                loader3.setController(cloudController);
                AnchorPane clouds3 = loader3.load();
                cloudPane.getChildren().addAll(clouds3);
                cloudController.addAllObservers(observers);
                break;
            case 4:
                FXMLLoader loader4 = new FXMLLoader(StartGUI.class.getResource("/fxml/cloudCards_4Players.fxml"));
                cloudController = new CloudCards();
                cloudController.setCloudCard(table.getCloudNumber());
                loader4.setController(cloudController);
                AnchorPane clouds4 = loader4.load();
                cloudPane.getChildren().addAll(clouds4);
                cloudController.addAllObservers(observers);
                break;
        }
        if(difficulty.equals(Difficulty.STANDARDMODE)){
            coinPaneTable.setVisible(false);
            coinPaneTable.setDisable(true);
            coinTextTable.setVisible(false);
            coinTextTable.setDisable(true);
            coinImageTable.setVisible(false);
            coinImageTable.setDisable(true);
            coinPanePersonal.setVisible(false);
            coinPanePersonal.setDisable(true);
            coinImagePersonal.setVisible(false);
            coinImagePersonal.setDisable(true);
            textCoinsOnTable.setDisable(true);
            textCoinsOnTable.setVisible(false);
            TextCoinscore.setVisible(false);
            TextCoinscore.setDisable(true);
            PaneCoinScore.setDisable(true);
            PaneCoinScore.setVisible(false);
            coinTextPersonal.setVisible(false);
            coinTextPersonal.setDisable(true);
            ImageTrashPersonal.setVisible(false);
            characterCardLayout.setVisible(false);
            characterPane.setVisible(false);
        }else{
            coinTextTable.setText(String.valueOf(table.getCoinsOnTable()));
            ArrayList<CharacterCard> characterCardsPlaying = table.getCharacterCardsOnTable();
            try{
                for(int i = 0; i < 3; i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/characterCard.fxml"));
                    AnchorPane characterCardImage = loader.load();

                    CharacterCardController characterCardController = loader.getController();
                    characterCardController.setData(characterCardsPlaying.get(i));
                    characterCardController.addAllObservers(observers);

                    switch (characterCardsPlaying.get(i).getCardEffect()) {
                        case STANDARDMODE:
                        case BEARER:
                        case HERALD:
                        case HOST:
                        case CENTAUR:
                        case KNIGHT:
                        case HERBALIST:
                        case BARD:
                        case JUNKDEALER:
                            characterCardController.hideAll();
                            break;
                        case ABBOT:
                        case ACROBAT:
                        case COURTESAN:
                            characterCardController.hideAll();
                            characterCardController.initializeStudentPane(characterCardsPlaying.get(i).getStudentsOnCard());
                            break;
                        case CURATOR:
                            characterCardController.hideAll();
                            characterCardController.initializeXCardPane(characterCardsPlaying.get(i).getXCardOnCard());
                            break;
                    }
                    characterCardLayout.getChildren().add(characterCardImage);
                    characterCardControllerMap.put(characterCardsPlaying.get(i).getCardEffect(), characterCardController);
                    characterCardLayout.setVisible(true);
                    disabilitateCharacterCards(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initialize buttons present in the Scene and enables students drag event
     */
    @FXML
    public void initialize(){
        addDragOver();

        otherSchoolButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                otherSchoolClicked(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        deckButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::deckButtonClicked);
    }

    /**
     * Handles the click on the "View schools" button
     * @param event opens a new scene showing the schools of other players
     * @throws IOException
     */
    private void otherSchoolClicked(Event event) throws IOException {
        switch(gameMode){
            case TWOPLAYERS:
                ViewOtherSchoolScene1 scene1 = new ViewOtherSchoolScene1();
                GuiManager.newStagePane(scene1,"/fxml/view_other_school_1_scene");
                scene1.updatePersonalSchool(schoolControllersMap);
                break;
            case THREEPLAYERS:
                ViewOtherSchoolScene2 scene2 = new ViewOtherSchoolScene2();
                GuiManager.newStagePane(scene2,"/fxml/view_other_school_2_scene");
                scene2.updatePersonalSchool(schoolControllersMap);
                break;
            case COOP:
                ViewOtherSchoolScene3 scene3 = new ViewOtherSchoolScene3();
                GuiManager.newStagePane(scene3, "/fxml/view_other_school_3_scene");
                scene3.updatePersonalSchool(schoolControllersMap);
                break;
        }
    }

    /**
     * Updates the schools of other players
     * @param controller school controller of each school
     * @param gameMode game mode of the match
     * @param nickname of the player
     */
    public void updateOtherSchool(SchoolController controller, GameMode gameMode, String nickname){
        this.gameMode = gameMode;
        if(schoolControllersMap.containsKey(nickname)){
            schoolControllersMap.remove(nickname);
            schoolControllersMap.put(nickname,controller);
        }else{
            schoolControllersMap.put(nickname,controller);
        }
    }

    /**
     * Handles the click on the Assistant Deck button
     * @param event opens a new scene showing players personal Assistant Deck allowing the player to play a certain Assistant Card
     */
    private void deckButtonClicked(Event event){
        GuiManager.newStagePane(assistantDeck, "/fxml/view_deck_scene");
        if(planning) {
            assistantDeck.activatePlayButton();
        }
    }

    /**
     * Updates the Assistant Deck Image for each player
     * @param viewDeckScene deck of each player
     */
    public void updateAssistantCardDeck(ViewDeckScene viewDeckScene){
        this.assistantDeck = viewDeckScene;
        switch(viewDeckScene.getDeckAssistant().getDeckName()){
            case WIZARD1:
                deckLogo.setImage((new Image("/images/assistantDeck/assistantCard_back_1.png")));
                break;
            case WIZARD2:
                deckLogo.setImage((new Image("/images/assistantDeck/assistantCard_back_2.png")));
                break;
            case WIZARD3:
                deckLogo.setImage((new Image("/images/assistantDeck/assistantCard_back_3.png")));
                break;
            case WIZARD4:
                deckLogo.setImage((new Image("/images/assistantDeck/assistantCard_back_4.png")));
                break;
        }
    }

    /**
     * Updates the gui of the islands
     * @param listOfIslands list of the islands after one or more merges
     */
    public void updateIslands(ArrayList<IslandCard> listOfIslands) {
        for(IslandCard islandCard : listOfIslands){
            ArrayList<Student> studentsOnIsland = (ArrayList<Student>) islandCard.getStudentOnIsland().clone();
            Pane island = (Pane) islandPane.getChildren().get(24+islandCard.getImmutableIdIsland());
            for(int islandId : islandCard.getListOfMinorIslands()){
                if(islandId!=islandCard.getImmutableIdIsland()){
                    Pane temp = new Pane();
                    switch(islandId){
                        case 1:
                            if(islandCard.getListOfMinorIslands().contains(2) || islandCard.getImmutableIdIsland()==2)
                                Bridge1_2.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(12) || islandCard.getImmutableIdIsland()==12)
                                Bridge12_1.setVisible(true);
                            temp = PaneIsland1;
                            break;
                        case 2:
                            if(islandCard.getListOfMinorIslands().contains(1) || islandCard.getImmutableIdIsland()==1)
                                Bridge1_2.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(3) || islandCard.getImmutableIdIsland()==3)
                                Bridge2_3.setVisible(true);
                            temp = PaneIsland2;
                            break;
                        case 3:
                            if(islandCard.getListOfMinorIslands().contains(2) || islandCard.getImmutableIdIsland()==2)
                                Bridge2_3.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(4) || islandCard.getImmutableIdIsland()==4)
                                Bridge3_4.setVisible(true);
                            temp = PaneIsland3;
                            break;
                        case 4:
                            if(islandCard.getListOfMinorIslands().contains(3) || islandCard.getImmutableIdIsland()==3)
                                Bridge3_4.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(5) || islandCard.getImmutableIdIsland()==5)
                                Bridge4_5.setVisible(true);
                            temp = PaneIsland4;
                            break;
                        case 5:
                            if(islandCard.getListOfMinorIslands().contains(4) || islandCard.getImmutableIdIsland()==4)
                                Bridge4_5.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(6) || islandCard.getImmutableIdIsland()==6)
                                Bridge5_6.setVisible(true);
                            temp = PaneIsland5;
                            break;
                        case 6:
                            if(islandCard.getListOfMinorIslands().contains(5) || islandCard.getImmutableIdIsland()==5)
                                Bridge5_6.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(7) || islandCard.getImmutableIdIsland()==7)
                                Bridge6_7.setVisible(true);
                            temp = PaneIsland6;
                            break;
                        case 7:
                            if(islandCard.getListOfMinorIslands().contains(6) || islandCard.getImmutableIdIsland()==6)
                                Bridge6_7.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(8) || islandCard.getImmutableIdIsland()==8)
                                Bridge7_8.setVisible(true);
                            temp = PaneIsland7;
                            break;
                        case 8:
                            if(islandCard.getListOfMinorIslands().contains(7) || islandCard.getImmutableIdIsland()==7)
                                Bridge7_8.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(9) || islandCard.getImmutableIdIsland()==9)
                                Bridge8_9.setVisible(true);
                            temp = PaneIsland8;
                            break;
                        case 9:
                            if(islandCard.getListOfMinorIslands().contains(8) || islandCard.getImmutableIdIsland()==8)
                                Bridge8_9.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(10) || islandCard.getImmutableIdIsland()==10)
                                Bridge9_10.setVisible(true);
                            temp = PaneIsland9;
                            break;
                        case 10:
                            if(islandCard.getListOfMinorIslands().contains(9) || islandCard.getImmutableIdIsland()==9)
                                Bridge9_10.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(11) || islandCard.getImmutableIdIsland()==11)
                                Bridge10_11.setVisible(true);
                            temp = PaneIsland10;
                            break;
                        case 11:
                            if(islandCard.getListOfMinorIslands().contains(10) || islandCard.getImmutableIdIsland()==10)
                                Bridge10_11.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(12) || islandCard.getImmutableIdIsland()==12)
                                Bridge11_12.setVisible(true);
                            temp = PaneIsland11;
                            break;
                        case 12:
                            if(islandCard.getListOfMinorIslands().contains(11) || islandCard.getImmutableIdIsland()==11)
                                Bridge11_12.setVisible(true);
                            if(islandCard.getListOfMinorIslands().contains(1) || islandCard.getImmutableIdIsland()==1)
                                Bridge12_1.setVisible(true);
                            temp = PaneIsland12;
                            break;
                    }
                    for(int i : islandListOfStudentsIdMap.get(temp)){
                        for(int pos  = 0; pos<studentsOnIsland.size() ;pos++){
                            if(studentsOnIsland.get(pos).getIdStudent()==i)
                                studentsOnIsland.remove(studentsOnIsland.get(pos));
                        }
                    }
                    if(islandCard.towerIsOnIsland()){
                        temp.getChildren().get(island.getChildren().size()-2).setVisible(true);
                        switch(islandCard.getTowerOnIsland().getTColour()){
                            case WHITE:
                                ((ImageView) temp.getChildren().get(temp.getChildren().size()-2)).setImage(new Image("/images/towers/Twhite.png"));
                                break;
                            case BLACK:
                                ((ImageView) temp.getChildren().get(temp.getChildren().size()-2)).setImage(new Image("/images/towers/Tblack.png"));
                                break;
                            case GREY:
                                ((ImageView) temp.getChildren().get(temp.getChildren().size()-2)).setImage(new Image("/images/towers/Tgrey.png"));
                                break;
                        }
                    }
                }
            }
            for(Student student : studentsOnIsland){
                if (island.getChildren().indexOf(island.getChildren().get(studentsOnIsland.indexOf(student)))!=(island.getChildren().size()-1) && island.getChildren().indexOf(island.getChildren().get(studentsOnIsland.indexOf(student)))!=(island.getChildren().size()-2)) {
                    island.getChildren().get(studentsOnIsland.indexOf(student)).setVisible(true);
                    if(!(islandListOfStudentsIdMap.containsKey(island))) {
                        islandListOfStudentsIdMap.put(island, new ArrayList<>());
                        islandListOfStudentsIdMap.get(island).add(student.getIdStudent());
                    }else{
                        if(!(islandListOfStudentsIdMap.get(island).contains(student.getIdStudent())));
                            islandListOfStudentsIdMap.get(island).add(student.getIdStudent());
                    }
                    switch (student.getSColor()) {
                        case GREEN:
                            ((ImageView) island.getChildren().get(studentsOnIsland.indexOf(student))).setImage(new Image("/images/students/Gstudent.png"));
                            break;
                        case RED:
                            ((ImageView) island.getChildren().get(studentsOnIsland.indexOf(student))).setImage(new Image("/images/students/Rstudent.png"));
                            break;
                        case YELLOW:
                            ((ImageView) island.getChildren().get(studentsOnIsland.indexOf(student))).setImage(new Image("/images/students/Ystudent.png"));
                            break;
                        case PINK:
                            ((ImageView) island.getChildren().get(studentsOnIsland.indexOf(student))).setImage(new Image("/images/students/Pstudent.png"));
                            break;
                        case BLUE:
                            ((ImageView) island.getChildren().get(studentsOnIsland.indexOf(student))).setImage(new Image("/images/students/Bstudent.png"));
                            break;
                    }
                }
            }
            if(islandCard.getMotherEarthOnIsland()){
                for(int i=1;i<13;i++){
                    ((Pane) islandPane.getChildren().get(24+i)).getChildren().get(((Pane) islandPane.getChildren().get(24+i)).getChildren().size()-1).setVisible(false);
                }
                island.getChildren().get(island.getChildren().size()-1).setVisible(true);
                islandMother=islandCard.getIdIsland();
            }
            if(islandCard.towerIsOnIsland()){
                island.getChildren().get(island.getChildren().size()-2).setVisible(true);
                switch(islandCard.getTowerOnIsland().getTColour()){
                    case WHITE:
                        ((ImageView) island.getChildren().get(island.getChildren().size()-2)).setImage(new Image("/images/towers/Twhite.png"));
                        break;
                    case BLACK:
                        ((ImageView) island.getChildren().get(island.getChildren().size()-2)).setImage(new Image("/images/towers/Tblack.png"));
                        break;
                    case GREY:
                        ((ImageView) island.getChildren().get(island.getChildren().size()-2)).setImage(new Image("/images/towers/Tgrey.png"));
                        break;
                }
            }
            if(islandCard.isXCardOnIsland()){
                if (PaneIsland1.equals(island)) {
                    PaneXCard1.setVisible(true);
                    PaneXCard1.getChildren().get(0).setVisible(true);
                    PaneXCard1.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard1.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland2.equals(island)) {
                    PaneXCard2.setVisible(true);
                    PaneXCard2.getChildren().get(0).setVisible(true);
                    PaneXCard2.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard2.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland3.equals(island)) {
                    PaneXCard3.setVisible(true);
                    PaneXCard3.getChildren().get(0).setVisible(true);
                    PaneXCard3.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard3.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland4.equals(island)) {
                    PaneXCard4.setVisible(true);
                    PaneXCard4.getChildren().get(0).setVisible(true);
                    PaneXCard4.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard4.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland5.equals(island)) {
                    PaneXCard5.setVisible(true);
                    PaneXCard5.getChildren().get(0).setVisible(true);
                    PaneXCard5.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard5.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland6.equals(island)) {
                    PaneXCard6.setVisible(true);
                    PaneXCard6.getChildren().get(0).setVisible(true);
                    PaneXCard6.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard6.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland7.equals(island)) {
                    PaneXCard7.setVisible(true);
                    PaneXCard7.getChildren().get(0).setVisible(true);
                    PaneXCard7.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard7.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland8.equals(island)) {
                    PaneXCard8.setVisible(true);
                    PaneXCard8.getChildren().get(0).setVisible(true);
                    PaneXCard8.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard8.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland9.equals(island)) {
                    PaneXCard9.setVisible(true);
                    PaneXCard9.getChildren().get(0).setVisible(true);
                    PaneXCard9.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard9.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland10.equals(island)) {
                    PaneXCard10.setVisible(true);
                    PaneXCard10.getChildren().get(0).setVisible(true);
                    PaneXCard10.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard10.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland11.equals(island)) {
                    PaneXCard11.setVisible(true);
                    PaneXCard11.getChildren().get(0).setVisible(true);
                    PaneXCard11.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard11.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                } else if (PaneIsland12.equals(island)) {
                    PaneXCard12.setVisible(true);
                    PaneXCard12.getChildren().get(0).setVisible(true);
                    PaneXCard12.getChildren().get(1).setVisible(true);
                    ((Text) PaneXCard12.getChildren().get(1)).setText(String.valueOf(islandCard.getXCardCounter()));
                }
            }
        }
    }

    public ViewDeckScene getAssistantDeck(){
        return assistantDeck;
    }

    public void setPlanning(boolean planning){
        this.planning = planning;
    }

    public boolean isPlanning(){
        return planning;
    }

    public Map<String,String> getAssistantCardMap(){
        return assistantCardMap;
    }

    public SchoolController getPersonalSchoolController() {
        return personalSchoolController;
    }

    public CloudCards getCloudController() {
        return cloudController;
    }

    /**
     * Sets the correct id for the island chosed
     * @param destinationIsland id of the island that has been chosen in gui
     */
    public void setIslandId(int destinationIsland) {
        boolean present = false;
        for(IslandCard islandCard : table.getListOfIsland()){
            if(islandCard.getImmutableIdIsland() == destinationIsland) {
                present = true;
                this.studentDestinationIslandId = islandCard.getIdIsland();
            }
        }
        if (!present){
            for (IslandCard islandCard : table.getListOfIsland()) {
                for(int i : islandCard.getListOfMinorIslands()) {
                    if (i == destinationIsland)
                        this.studentDestinationIslandId = islandCard.getIdIsland();
                }
            }
        }
    }

    /**
     * Sets the correct id for the island chosed
     * @param destinationIsland id of the island that has been chosen in gui
     */
    public void setMotherId(int destinationIsland) {
        boolean present = false;
        for(IslandCard islandCard : table.getListOfIsland()){
            if(islandCard.getImmutableIdIsland()==destinationIsland) {
                present = true;
                this.motherDestinationIslandId = islandCard.getIdIsland();
            }
        }
        if (!present)
            for (IslandCard islandCard : table.getListOfIsland()) {
                for(int i : islandCard.getListOfMinorIslands()){
                    if (i == destinationIsland)
                        this.motherDestinationIslandId = islandCard.getIdIsland();
            }
        }
    }

    public int getStudentDestinationIslandId(){
        return studentDestinationIslandId;
    }

    /**
     * Returns the maximum number of steps that Mother Earth can do
     * @return number of steps of Mother Earth
     */
    public int getMaxSteps(){
        return maxSteps;
    }

    /**
     * Hides panes that are not used yet
     * @param table of the match
     */
    public void hide(Table table){
        for(IslandCard islandCard : table.getListOfIsland()) {
            Pane island = (Pane) islandPane.getChildren().get(24 + islandCard.getImmutableIdIsland());
            for (Node node : island.getChildren()) {
                node.setVisible(false);
                node.setDisable(true);
            }
        }
        Bridge1_2.setVisible(false);
        Bridge1_2.setDisable(true);
        Bridge2_3.setVisible(false);
        Bridge2_3.setDisable(true);
        Bridge3_4.setVisible(false);
        Bridge3_4.setDisable(true);
        Bridge4_5.setVisible(false);
        Bridge4_5.setDisable(true);
        Bridge5_6.setVisible(false);
        Bridge5_6.setDisable(true);
        Bridge6_7.setVisible(false);
        Bridge6_7.setDisable(true);
        Bridge7_8.setVisible(false);
        Bridge7_8.setDisable(true);
        Bridge8_9.setVisible(false);
        Bridge8_9.setDisable(true);
        Bridge9_10.setVisible(false);
        Bridge9_10.setDisable(true);
        Bridge10_11.setVisible(false);
        Bridge10_11.setDisable(true);
        Bridge11_12.setVisible(false);
        Bridge11_12.setDisable(true);
        Bridge12_1.setVisible(false);
        Bridge12_1.setDisable(true);
        turnText.setVisible(false);
        PaneXCard1.setVisible(false);
        PaneXCard2.setVisible(false);
        PaneXCard3.setVisible(false);
        PaneXCard4.setVisible(false);
        PaneXCard5.setVisible(false);
        PaneXCard6.setVisible(false);
        PaneXCard7.setVisible(false);
        PaneXCard8.setVisible(false);
        PaneXCard9.setVisible(false);
        PaneXCard10.setVisible(false);
        PaneXCard11.setVisible(false);
        PaneXCard12.setVisible(false);
        doneButton.setVisible(false);
        doneText.setVisible(false);
        doneButton.setDisable(true);
    }

    /**
     * Disables Mother Earth
     * @param table of the match
     * @param maxSteps Mother earth can do according to the last Assistant Card played by the player
     * @param disable boolean that enables or disables Mother Earth
     */
    public void disabilitateMother(Table table, int maxSteps, boolean disable) {
        this.table = table;
        this.maxSteps = maxSteps;
        for (IslandCard islandCard : table.getListOfIsland()) {
            Pane island = (Pane) islandPane.getChildren().get(24 + islandCard.getImmutableIdIsland());
            if (islandCard.getMotherEarthOnIsland()) {
                island.getChildren().get(island.getChildren().size() - 1).setDisable(disable);
            }
        }
    }


    /**
     * Disables character cards
     * @param disable boolean that enables or disables the character cards
     */
    public void disabilitateCharacterCards(boolean disable){
        for(Node node : characterCardLayout.getChildren()){
            node.setDisable(disable);
        }
    }

    public Map<CardEffect, CharacterCardController> getCharacterCardControllerMap() {
        return characterCardControllerMap;
    }

    /**
     * Enables drag event of students
     * Enables drag event of Mother Earth
     */
    private void addDragOver(){
        Pane island = null;
        for(int i=1;i<13;i++) {
            island = ((Pane) islandPane.getChildren().get(24 + i));
            Pane finalIsland = island;
            island.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != finalIsland &&
                            event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }

                    event.consume();
                }
            });

            Pane finalIsland1 = island;
            island.setOnDragEntered(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != finalIsland1 &&
                            event.getDragboard().hasImage()) {
                    }

                    event.consume();
                }
            });

            island.setOnDragExited(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.consume();
                }
            });

            int finalId = i;
            island.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if(db.hasImage()) {
                        if (equalsMother(event.getGestureSource())){
                            setMotherId(finalId);
                            success = true;
                        } else {
                            if(cardSelected!=null){
                                if(cardSelected.getCardEffect().equals(CardEffect.ABBOT)) {
                                    if (characterCardControllerMap.get(CardEffect.ABBOT).getStudentMap().containsKey(event.getGestureSource())) {
                                        setIslandId(finalId);
                                        success = true;
                                    }
                                }else {
                                    personalSchoolController.setPlaceSelected("ISLAND");
                                    setIslandId(finalId);
                                    success = true;
                                }
                            }else {
                                personalSchoolController.setPlaceSelected("ISLAND");
                                setIslandId(finalId);
                                success = true;
                            }
                        }
                    }
                    event.setDropCompleted(success);

                    event.consume();
                }
            });
        }
        for (int i=1;i<13;i++) {
            Pane islandP = (Pane) islandPane.getChildren().get(24+i);
            Node motherNature = islandP.getChildren().get(islandP.getChildren().size()-1);
            motherNature.setOnDragDetected(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(cardSelected!=null) {
                        characterCardControllerMap.get(cardSelected.getCardEffect()).disableStudents(true);
                        disableDoneButton(true);
                    }
                    Dragboard db =  motherNature.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(((ImageView) motherNature).getImage());
                    db.setContent(content);
                    mouseEvent.consume();
                }
            });

            /**
             * Handles drag over of Mother Earth
             */
            motherNature.setOnDragDone(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        ArrayList<Integer> possibleIslands = new ArrayList<>();
                        if ((islandMother + maxSteps) > table.getListOfIsland().size()) {
                            for (int i = 1; i < (islandMother + maxSteps - table.getListOfIsland().size() + 1); i++) {
                                possibleIslands.add(i);
                            }
                        }
                        int steps = (motherDestinationIslandId - islandMother);
                        if (steps < 0) {
                            for (int i = 0; i < possibleIslands.size(); i++) {
                                if (possibleIslands.get(i) == motherDestinationIslandId) {
                                    steps = table.getListOfIsland().size() - islandMother + motherDestinationIslandId;
                                    int finalSteps = steps;
                                    notifyObserver(obs -> obs.chooseMotherEarthSteps(finalSteps, maxSteps, ""));
                                }
                            }

                        } else if (steps > 0) {
                            int finalSteps1 = steps;
                            notifyObserver(obs -> obs.chooseMotherEarthSteps(finalSteps1, maxSteps, ""));
                        }
                    }
                    event.consume();
                }
            });
        }
    }

    /**
     * Adds Mouse Event to Islands
     */
    public void addMouseEventToIslands(){
        for(int i=1;i<13;i++) {
            Pane island = ((Pane) islandPane.getChildren().get(24 + i));
            islandIdMap.put(island,i);
            island.addEventHandler(MouseEvent.MOUSE_CLICKED, this::islandClicked);
            island.setCursor(Cursor.HAND);
        }
    }
    /**
     * Handles the click on the island.
     * @param event the mouse click event.
     */
    private void islandClicked(Event event){
        setIslandId(islandIdMap.get(event.getSource()));
        removeMouseEventFromIslands();
    }


    /**
     * Removes Mouse Event to Islands
     */
    public void removeMouseEventFromIslands(){
        notifyObserver(obs -> obs.chooseCharacterCard(cardSelected.getCardEffect().toString(),true));
        for(int i=1;i<13;i++) {
            Pane island = ((Pane) islandPane.getChildren().get(24 + i));
            islandIdMap.remove(island);
            island.removeEventHandler(MouseEvent.MOUSE_CLICKED, this::islandClicked);
            island.setCursor(Cursor.DEFAULT);
        }
    }

    /**
     * @param o object referring Mother Earth's pane
     * @return if the source of the drag event is a "mother earth"
     */
    private boolean equalsMother(Object o){
        return (o.equals(motherEarth1) || o.equals(motherEarth2) || o.equals(motherEarth3) || o.equals(motherEarth4) ||
                o.equals(motherEarth5) || o.equals(motherEarth6) || o.equals(motherEarth7)|| o.equals(motherEarth8) ||
                o.equals(motherEarth9)|| o.equals(motherEarth10)|| o.equals(motherEarth11) || o.equals(motherEarth12));
    }

    public boolean isActionStudent() {
        return actionStudent;
    }

    public void setActionStudent(boolean actionStudent) {
        this.actionStudent = actionStudent;
    }

    public boolean isActionMother() {
        return actionMother;
    }

    public void setActionMother(boolean actionMother) {
        this.actionMother = actionMother;
    }

    public boolean isActionCloud() {
        return actionCloud;
    }

    public void setActionCloud(boolean actionCloud) {
        this.actionCloud = actionCloud;
    }

    public CharacterCard getCardSelected() {
        return cardSelected;
    }

    public void setCardSelected(CharacterCard cardSelected) {
        this.cardSelected = cardSelected;
    }

    public Table getTable(){
        return table;
    }

    /**
     * Reactivates the action phase after a card has been selected
     */
    public void reactivateActionPhase(){
        if(isActionStudent()){
            personalSchoolController.disableEntry(false);
        }else if(isActionMother()){
            disabilitateMother(GuiManager.getMainScene().getTable(),GuiManager.getMainScene().getMaxSteps(),false);
        }else if(isActionCloud()){
            cloudController.disabilitateCloud(false);
        }
    }

    /**
     * Disables done dutton
     * @param disable boolean that enables or disables done button
     */

    public void disableDoneButton(boolean disable){
        if(!disable){
            doneButton.setVisible(!disable);
            doneButton.setDisable(disable);
            doneText.setVisible(!disable);
            doneButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::doneClicked);
        }else{
            doneButton.setVisible(!disable);
            doneButton.setDisable(disable);
            doneText.setVisible(!disable);
            doneButton.removeEventHandler(MouseEvent.MOUSE_CLICKED,this::doneClicked);
        }
    }


    /**
     * Handles the click on the done button.
     * @param event the mouse click event.
     */
    private void doneClicked(Event event){
        disableSwitchStudents();
        disableDoneButton(true);
    }

    /**
     * Handles the case of less students selected for switch in bard and acrobat card
     */
    public void disableSwitchStudents(){
        if(GuiManager.getMainScene().getCardSelected()!=null){
            GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).disableStudents(true);
            if(GuiManager.getMainScene().getCardSelected().getCardEffect().equals(CardEffect.ACROBAT)) {
                int index1 = GuiManager.getMainScene().getPersonalSchoolController().getAcrobatIndex();
                int index2 = GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).getAcrobatIndex();
                if ((index1 + index2) == 5 || (index1 + index2) == 4) {
                    GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).getStudentSelectedForSwitch()[2] = -1;
                    GuiManager.getMainScene().getPersonalSchoolController().getStudentSelectedForSwitch()[2] = -1;
                } else if ((index1 + index2) == 3 || (index1 + index2) == 2) {
                    GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).getStudentSelectedForSwitch()[2] = -1;
                    GuiManager.getMainScene().getPersonalSchoolController().getStudentSelectedForSwitch()[2] = -1;
                    GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).getStudentSelectedForSwitch()[1] = -1;
                    GuiManager.getMainScene().getPersonalSchoolController().getStudentSelectedForSwitch()[1] = -1;
                }
                GuiManager.getMainScene().getPersonalSchoolController().removeMouseEventToStudentsOfEntry();
                GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).disableStudents(true);
                notifyObserver(obs -> obs.chooseCharacterCard(GuiManager.getMainScene().getCardSelected().getCardEffect().toString(), true));
            }else if(GuiManager.getMainScene().getCardSelected().getCardEffect().equals(CardEffect.BARD)){
                int index1 = GuiManager.getMainScene().getPersonalSchoolController().getAcrobatIndex();
                int index2 = GuiManager.getMainScene().getPersonalSchoolController().getAcrobatIndexHall();
                if ((index1 + index2) == 3 || (index1 + index2) == 2) {
                    GuiManager.getMainScene().getPersonalSchoolController().getStudentSelectedForSwitch()[1] = -1;
                    GuiManager.getMainScene().getPersonalSchoolController().getStudentSelectedForSwitchHall()[1] = -1;
                }
            }
        }
    }
}
