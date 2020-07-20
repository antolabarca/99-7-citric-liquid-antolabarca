package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.Game;
import com.github.cc3002.citricliquid.controller.GameController;
import com.github.cc3002.citricliquid.gui.node.MovableNodeBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CitricLiquid extends Application{
    private Game game = new Game();
    private static final String RESOURCE_PATH = "src/main/resources/";

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

        Player suguri = controller.createPlayer("Suguri", 4,1,-1,2, board.getHomePanels().get(0));
        controller.setPlayerHome(suguri, board.getHomePanels().get(0));
        Player kai = controller.createPlayer("Kai", 5,1,0,0,board.getHomePanels().get(1));
        controller.setPlayerHome(kai, board.getHomePanels().get(1));
        Player qp = controller.createPlayer("QP", 5,0,0,0,board.getHomePanels().get(2));
        controller.setPlayerHome(qp, board.getHomePanels().get(2));
        Player marc = controller.createPlayer("Marc", 4,1,1,-1,board.getHomePanels().get(3));
        controller.setPlayerHome(marc, board.getHomePanels().get(3));


        var sprite0 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite0.png")
                .setPosition(410, 470)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite0.getNode());
        suguri.setSprite(sprite0);

        var sprite1 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite1.png")
                .setPosition(480, 130)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite1.getNode());
        kai.setSprite(sprite1);

        var sprite2 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite2.png")
                .setPosition(750, 540)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite2.getNode());
        qp.setSprite(sprite2);

        var sprite3 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite3.png")
                .setPosition(820, 200)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite3.getNode());
        marc.setSprite(sprite3);


        mainStage.setScene(scene);
        mainStage.show();
    }
}
