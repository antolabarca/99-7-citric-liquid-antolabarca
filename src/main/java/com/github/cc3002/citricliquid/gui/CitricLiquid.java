package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.FightDecision;
import com.github.cc3002.citricjuice.model.unit.HomeDecision;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.Board;
import com.github.cc3002.citricliquid.Game;
import com.github.cc3002.citricliquid.controller.GameController;
import com.github.cc3002.citricliquid.gui.choicehandlers.FightChoiceHandler;
import com.github.cc3002.citricliquid.gui.choicehandlers.HomeChoiceHandler;
import com.github.cc3002.citricliquid.gui.choicehandlers.PanelChoiceHandler;
import com.github.cc3002.citricliquid.gui.node.MovableNode;
import com.github.cc3002.citricliquid.gui.node.MovableNodeBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class CitricLiquid extends Application {
    private static Game game = new Game();
    private static Credits credits = new Credits();
    private static Map<IPanel, Button> panelButtons = new HashMap<>();
    private static Map<Button, IPanel> buttonPanels = new HashMap<>();
    private static Set<Button> homeDecisionButtons = new HashSet<>();
    private static Set<Button> fightChoiceButtons = new HashSet<>();
    private PlayerMovementHandler playerMovementHandler = new PlayerMovementHandler(this);
    private PanelChoiceHandler panelChoiceHandler = new PanelChoiceHandler(this);
    private HomeChoiceHandler homeChoiceHandler = new HomeChoiceHandler(this);
    private FightChoiceHandler fightChoiceHandler = new FightChoiceHandler(this);
    private static final String RESOURCE_PATH = "src/resources/";

    @Override
    public void start(@NotNull Stage mainStage) throws FileNotFoundException {
        mainStage.setTitle("99.7% Citric Liquid");
        Group root = new Group();
        int width = 1280;
        int height = 720;
        Scene scene = new Scene(root, width, height);
        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "board.png")));
        root.getChildren().add(background);

        GameController controller = game.getController();
        controller.setBoard();
        Board board = controller.getBoard();
        game.setUp();

        ArrayList<Player> players = controller.getPlayers();
        for (int i = 0; i < 4; i++) {
            players.get(i).addPlayerMovementListener(playerMovementHandler);
            players.get(i).addPanelChoiceListener(panelChoiceHandler);
            players.get(i).addHomeChoiceListener(homeChoiceHandler);
            players.get(i).addFightChoiceListener(fightChoiceHandler);
        }
        Player suguri = players.get(0);
        Player kai = players.get(1);
        Player qp = players.get(2);
        Player marc = players.get(3);


        var sprite0 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite0.png")
                .setPosition(410, 470)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite0.getNode());
        controller.setPlayerSprite(suguri, sprite0);

        var sprite1 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite1.png")
                .setPosition(480, 130)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite1.getNode());
        controller.setPlayerSprite(kai, sprite1);

        var sprite2 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite2.png")
                .setPosition(750, 540)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite2.getNode());
        controller.setPlayerSprite(qp, sprite2);

        var sprite3 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite3.png")
                .setPosition(820, 200)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite3.getNode());
        controller.setPlayerSprite(marc, sprite3);

        root.getChildren().add(creditsButton());

        List<IPanel> panelList = controller.getPanelsList();
        for(int i=0; i<panelList.size();i++){
            root.getChildren().add(panelChoiceButton(panelList.get(i)));
        }

        root.getChildren().add(stopAtHomeButton());
        root.getChildren().add(movePastHomeButton());

        root.getChildren().add(fightPlayerButton());
        root.getChildren().add(ignorePlayerButton());

        qp.move(3);

        mainStage.setScene(scene);
        mainStage.show();
    }

    private Node ignorePlayerButton() {
        Button b = new Button("Ignore");
        b.setLayoutX(630);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::ignorePlayer);
        fightChoiceButtons.add(b);
        b.setVisible(false);
        return b;
    }

    private static void ignorePlayer(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setFightDecision(FightDecision.IGNORE);
        Iterator iterator = fightChoiceButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    private Node fightPlayerButton() {
        Button b = new Button("Fight!");
        b.setLayoutX(570);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::fightPlayer);
        fightChoiceButtons.add(b);
        b.setVisible(false);
        return b;
    }

    private static void fightPlayer(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setFightDecision(FightDecision.ENGAGE);
        Iterator iterator = fightChoiceButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    private Node stopAtHomeButton() {
        Button b = new Button("Stop");
        b.setLayoutX(570);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::stopAtHome);
        homeDecisionButtons.add(b);
        b.setVisible(false);
        return b;
    }

    private static void stopAtHome(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setHomeDecision(HomeDecision.STOP);
        Iterator iterator = homeDecisionButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    private Node movePastHomeButton() {
        Button b = new Button("Keep Moving");
        b.setLayoutX(630);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::keepMovingHome);
        homeDecisionButtons.add(b);
        b.setVisible(false);
        return b;
    }

    private static void keepMovingHome(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setHomeDecision(HomeDecision.KEEPMOVING);
        Iterator iterator = homeDecisionButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    private Node panelChoiceButton(IPanel panel) {
        Button b = new Button();
        buttonPanels.put(b,panel);
        panelButtons.put(panel,b);
        Pair<Integer,Integer> position = game.getController().getPanelPosition(panel);
        b.setLayoutX(position.getKey()+15);
        b.setLayoutY(position.getValue()+15);
        b.setOnAction(CitricLiquid::panelChosen);
        b.setVisible(false);
        return b;
    }

    private static void panelChosen(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        IPanel panel = buttonPanels.get(b);
        game.getController().getTurnOwner().setPanelDecision(panel);
        System.out.println(game.getController().getTurnOwner().getPanelDecision());
        Set<Button> buttonSet = buttonPanels.keySet();
        Iterator iterator = buttonSet.iterator();
        while (iterator.hasNext()){ //disable all the panel buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }


    public void moveSprite(Player player, IPanel panel) {
        MovableNode sprite = game.getController().getPlayerSprite(player);
        Pair<Integer, Integer> position = game.getController().getPanelPosition(panel);
        sprite.moveTo(position.getKey(), position.getValue());
    }

    private Node creditsButton() {
        Button b = credits.getButton();
        return b;
    }

    public void enablePanelButtons(Set<IPanel> panels) {
        Iterator iterator = panels.iterator();
        while (iterator.hasNext()){ //enable all the panel buttons
            IPanel next = (IPanel) iterator.next();
            Button b = panelButtons.get(next);
            b.setVisible(true);
        }
    }

    public void enableHomeButtons() {
        Iterator iterator = homeDecisionButtons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(true);
        }
    }

    public void enableFightChoiceButtons() {
        Iterator iterator = fightChoiceButtons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(true);
        }
    }
}
