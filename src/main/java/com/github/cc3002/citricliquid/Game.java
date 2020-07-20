package com.github.cc3002.citricliquid;

import com.github.cc3002.citricjuice.TurnPhases.Turn;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.controller.GameController;

public class Game {
    GameController controller = new GameController();

    public Game(){
        super();
    }

    /**
     * Returns this game's controller
     */
    public GameController getController() {
        return controller;
    }

    public void startGame(){
        while (!controller.isGameWon()){
            Turn turn = new Turn(controller);
            while (turn.isActive()){
                turn.getPhase().action();
            }
            controller.endTurn();
        }
    }
}
