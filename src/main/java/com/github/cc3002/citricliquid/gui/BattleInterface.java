package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.unit.BattleDecision;
import com.github.cc3002.citricjuice.model.unit.IUnit;
import com.github.cc3002.citricliquid.Game;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BattleInterface {
    private static IUnit unit1;
    private static IUnit unit2;
    Game game;

    public BattleInterface(IUnit unit1, IUnit unit2, Game game) throws FileNotFoundException {
        super();
        this.unit1 = unit1;
        this.unit2 = unit2;
        this.game = game;
        show();
    }


    public void show() throws FileNotFoundException {
        Stage stage = new Stage();
        stage.setTitle("Battle between "+unit1.getName()+" and "+unit2.getName());
        Group root = new Group();
        int width = 720;
        int height = 500;
        Scene scene = new Scene(root, width, height);

        var unit1Img = new ImageView(new Image(new FileInputStream(unit1.getIcon())));
        unit1Img.setX(100);
        unit1Img.setY(45);
        root.getChildren().add(unit1Img);

        var unit2Img = new ImageView(new Image(new FileInputStream(unit2.getIcon())));
        unit2Img.setX(550);
        unit2Img.setY(45);
        root.getChildren().add(unit2Img);

        root.getChildren().add(unit1EvadeButton());
        root.getChildren().add(unit2EvadeButton());
        root.getChildren().add(unit1DefendButton());
        root.getChildren().add(unit2DefendButton());

        stage.setScene(scene);
        stage.show();
    }

    private Node unit2DefendButton() {
        Button b = new Button(unit1.getName()+" defends");
        b.setLayoutY(400);
        b.setLayoutX(570);
        b.setOnAction(BattleInterface::unit2Defend);
        return b;
    }

    private static void unit2Defend(ActionEvent actionEvent) {
        unit2.setBattleDecision(BattleDecision.DEFEND);
    }

    private Node unit1DefendButton() {
        Button b = new Button(unit1.getName()+" defends");
        b.setLayoutY(400);
        b.setLayoutX(130);
        b.setOnAction(BattleInterface::unit1Defend);
        return b;
    }

    private static void unit1Defend(ActionEvent actionEvent) {
        unit1.setBattleDecision(BattleDecision.DEFEND);
    }

    private Node unit2EvadeButton() {
        Button b = new Button(unit1.getName()+" evades");
        b.setLayoutY(400);
        b.setLayoutX(600);
        b.setOnAction(BattleInterface::unit2Evade);
        return b;
    }

    private static void unit2Evade(ActionEvent actionEvent) {
        unit2.setBattleDecision(BattleDecision.EVADE);
    }

    private Node unit1EvadeButton() {
        Button b = new Button(unit1.getName()+" evades");
        b.setLayoutY(400);
        b.setLayoutX(100);
        b.setOnAction(BattleInterface::unit1Evade);
        return b;
    }

    private static void unit1Evade(ActionEvent actionEvent) {
        unit1.setBattleDecision(BattleDecision.EVADE);
    }

}
