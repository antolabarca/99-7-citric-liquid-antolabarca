package com.github.cc3002.citricliquid;

import com.github.cc3002.citricliquid.TurnPhases.Turn;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.controller.GameController;

public class Game {
    GameController controller = new GameController();
    Board board = new Board(controller);

    public Game(){
        super();
    }

    /**
     * Returns this game's controller
     */
    public GameController getController() {
        return controller;
    }

    public void setUp(){
        Player suguri = controller.createPlayer("Suguri", 4,1,-1,2, board.getHomePanels().get(0));
        controller.setPlayerHome(suguri, board.getHomePanels().get(0));
        Player kai = controller.createPlayer("Kai", 5,1,0,0,board.getHomePanels().get(1));
        controller.setPlayerHome(kai, board.getHomePanels().get(1));
        Player qp = controller.createPlayer("QP", 5,0,0,0,board.getHomePanels().get(2));
        controller.setPlayerHome(qp, board.getHomePanels().get(2));
        Player marc = controller.createPlayer("Marc", 4,1,1,-1,board.getHomePanels().get(3));
        controller.setPlayerHome(marc, board.getHomePanels().get(3));
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
