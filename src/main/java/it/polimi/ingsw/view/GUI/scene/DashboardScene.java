package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.controller.GameController;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardScene extends ObservableView implements GenericScene {

    private Map<String,SchoolController> schoolControllers = new HashMap<>();

    private CloudCards cloudController;
    private SchoolController personalSchoolController;
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
    private GameController gameController;
    private Difficulty difficulty;

    private boolean planning = false;

    private boolean actionStudent = false;

    private Map<Pane, ArrayList<Integer>> islandMap = new HashMap<>();

    private int islandId;


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
    private Text TextCoinscore;

    @FXML
    private Text TextTeamMate;

    @FXML
    private VBox characterCardLayout;

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
    private Button deckButton;

    @FXML
    private ImageView deckLogo;

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
    private Pane schoolPane12;

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
    private Pane trashPanePersonalSchool;



    public void updateTable(Table table){
        this.table = table;
        cloudController.updateStudents(table.getCloudNumber());
        updateIslands(table.getListOfIsland());
    }

    public void updatePersonalSchool(SchoolController controller, GameMode gameMode, String teamMate) throws IOException {
        personalSchoolController = controller;
        FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader.setController(controller);
        AnchorPane personalSchool = loader.load();
        schoolPane.getChildren().setAll(personalSchool);
        if(controller.getTrash()!=null) {
            ImageTrashPersonal.setVisible(true);
            ImageTrashPersonal.setImage(new Image(assistantCardMap.get(controller.getTrash().getAssistantName())));
        }else
            ImageTrashPersonal.setVisible(false);
        if(difficulty.equals(Difficulty.EXPERTMODE))
            coinTextPersonal.setText(String.valueOf(controller.getCoins()));
        if(!gameMode.equals(GameMode.COOP)){
            PaneTeamMate.setVisible(false);
            PaneTeamMate.setDisable(true);
            TextTeamMate.setDisable(true);
            TextTeamMate.setVisible(false);
        }
        else
            TextTeamMate.setText("TeamMate: " + teamMate);
    }

    public void initializeTable(Difficulty difficulty, Table table) throws IOException {
        this.difficulty = difficulty;

        hide(table);

        updateIslands(table.getListOfIsland());

        switch(table.getCloudNumber().size()){
            case 2:
                FXMLLoader loader2 = new FXMLLoader(StartGUI.class.getResource("/fxml/cloudCards_2Players.fxml"));
                cloudController = new CloudCards(table.getCloudNumber());
                loader2.setController(cloudController);
                AnchorPane clouds2 = loader2.load();
                cloudPane.getChildren().addAll(clouds2);
                break;
            case 3:
                FXMLLoader loader3 = new FXMLLoader(StartGUI.class.getResource("/fxml/cloudCards_3Players.fxml"));
                cloudController = new CloudCards(table.getCloudNumber());
                loader3.setController(cloudController);
                AnchorPane clouds3 = loader3.load();
                cloudPane.getChildren().addAll(clouds3);
                break;
            case 4:
                FXMLLoader loader4 = new FXMLLoader(StartGUI.class.getResource("/fxml/cloudCards_4Players.fxml"));
                cloudController = new CloudCards(table.getCloudNumber());
                loader4.setController(cloudController);
                AnchorPane clouds4 = loader4.load();
                cloudPane.getChildren().addAll(clouds4);
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
                    characterCardLayout.getChildren().add(characterCardImage);
                }
                characterCardLayout.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

    private void otherSchoolClicked(Event event) throws IOException {
        switch(gameMode){
            case TWOPLAYERS:
                ViewOtherSchoolScene1 scene1 = new ViewOtherSchoolScene1();
                GuiManager.newStagePane(scene1,"/fxml/view_other_school_1_scene");
                scene1.updatePersonalSchool(schoolControllers);
                break;
            case THREEPLAYERS:
                ViewOtherSchoolScene2 scene2 = new ViewOtherSchoolScene2();
                GuiManager.newStagePane(scene2,"/fxml/view_other_school_2_scene");
                scene2.updatePersonalSchool(schoolControllers);
                break;
            case COOP:
                ViewOtherSchoolScene3 scene3 = new ViewOtherSchoolScene3();
                GuiManager.newStagePane(scene3, "/fxml/view_other_school_3_scene");
                scene3.updatePersonalSchool(schoolControllers);
                break;
        }
    }

    public void updateOtherSchool(SchoolController controller, GameMode gameMode, String nickname){
        this.gameMode = gameMode;
        if(schoolControllers.containsKey(nickname)){
            schoolControllers.remove(nickname);
            schoolControllers.put(nickname,controller);
        }else{
            schoolControllers.put(nickname,controller);
        }

    }

    private void deckButtonClicked(Event event){
        GuiManager.newStagePane(assistantDeck, "/fxml/view_deck_scene");
        if(planning) {
            assistantDeck.activatePlayButton();
        }
    }

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

    public void updateIslands(ArrayList<IslandCard> listOfIslands) {
        for(IslandCard islandCard : listOfIslands){
            ArrayList<Student> studentsOnIsland = (ArrayList<Student>) islandCard.getStudentOnIsland().clone();
            Pane island = (Pane) islandPane.getChildren().get(24+islandCard.getImmutableIdIsland());
            for(int islandId : islandCard.getListOfMinorIslands()){
                if(islandId!=islandCard.getImmutableIdIsland()){
                    Pane temp = new Pane();
                    switch(islandId){
                        case 1:
                            temp = PaneIsland1;
                            break;
                        case 2:
                            temp = PaneIsland2;
                            break;
                        case 3:
                            temp = PaneIsland3;
                            break;
                        case 4:
                            temp = PaneIsland4;
                            break;
                        case 5:
                            temp = PaneIsland5;
                            break;
                        case 6:
                            temp = PaneIsland6;
                            break;
                        case 7:
                            temp = PaneIsland7;
                            break;
                        case 8:
                            temp = PaneIsland8;
                            break;
                        case 9:
                            temp = PaneIsland9;
                            break;
                        case 10:
                            temp = PaneIsland10;
                            break;
                        case 11:
                            temp = PaneIsland11;
                            break;
                        case 12:
                            temp = PaneIsland12;
                            break;
                    }
                    for(int i : islandMap.get(temp)){
                        for(Student student : studentsOnIsland){
                            if(student.getIdStudent()==i)
                                studentsOnIsland.remove(student);
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
                    if(!(islandMap.containsKey(island))) {
                        islandMap.put(island, new ArrayList<>());
                        islandMap.get(island).add(student.getIdStudent());
                    }else{
                        islandMap.get(island).add(student.getIdStudent());
                    }
                    switch (student.getsColour()) {
                        case GREEN:
                            ((ImageView) island.getChildren().get(islandCard.getStudentOnIsland().indexOf(student))).setImage(new Image("/images/students/Gstudent.png"));
                            break;
                        case RED:
                            ((ImageView) island.getChildren().get(islandCard.getStudentOnIsland().indexOf(student))).setImage(new Image("/images/students/Rstudent.png"));
                            break;
                        case YELLOW:
                            ((ImageView) island.getChildren().get(islandCard.getStudentOnIsland().indexOf(student))).setImage(new Image("/images/students/Ystudent.png"));
                            break;
                        case PINK:
                            ((ImageView) island.getChildren().get(islandCard.getStudentOnIsland().indexOf(student))).setImage(new Image("/images/students/Pstudent.png"));
                            break;
                        case BLUE:
                            ((ImageView) island.getChildren().get(islandCard.getStudentOnIsland().indexOf(student))).setImage(new Image("/images/students/Bstudent.png"));
                            break;
                    }
                }
            }
            if(islandCard.getMotherEarthOnIsland()){
                for(int i=1;i<13;i++){
                    ((Pane) islandPane.getChildren().get(24+i)).getChildren().get(((Pane) islandPane.getChildren().get(24+i)).getChildren().size()-1).setVisible(false);
                }
                island.getChildren().get(island.getChildren().size()-1).setVisible(true);
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
        }
    }


    public ViewDeckScene getAssistantDeck(){
        return assistantDeck;
    }

    public void setPlanning(boolean planning){
        this.planning = planning;
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

    public void setIslandId(int island) {
        boolean present = false;
        for(IslandCard islandCard : table.getListOfIsland()){
            if(islandCard.getImmutableIdIsland()==island)
                present = true;
        }
        if (present)
            this.islandId = island;
        else{
            for (IslandCard islandCard : table.getListOfIsland()) {
                for(int i : islandCard.getListOfMinorIslands())
                    if (i == island)
                        this.islandId = islandCard.getImmutableIdIsland();
            }
        }
    }

    public int getIslandId() {
        return islandId;
    }

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
    }

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
                            event.getDragboard().hasString()) {
                        finalIsland1.setVisible(false);
                    }

                    event.consume();
                }
            });

            island.setOnDragExited(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    island1.setVisible(true);

                    event.consume();
                }
            });

            int finalId = i;
            island.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        personalSchoolController.setPlaceSelected("ISLAND");
                        setIslandId(finalId);
                        success = true;
                    }
                    event.setDropCompleted(success);

                    event.consume();
                }
            });
        }
    }
}
