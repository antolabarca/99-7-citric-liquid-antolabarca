package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.controller.GameController;

import java.util.Objects;

public class Turn {
    private final Player player;
    private IPhase phase;
    private final int turn_number;
    private boolean isActive;
    private GameController controller;

    public Turn(GameController controller){
        setPhase(new FirstPhase());
        this.controller = controller;
        this.player=controller.getTurnOwner();
        this.turn_number=controller.getGameTurn();
        isActive=true;
    }

    /**
     * Checks if an object is equal to this turn
     * @param o the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return turn_number == turn.turn_number &&
                Objects.equals(player, turn.player);
                // && phase.equals(turn.phase); //this was removed to avoid infinite recurssion, it's not really relevant to check whether turns are equal anyways
    }


    /**
     * Sets this turns current phase
     * @param phase
     *      the phase the turn is on
     */
    public void setPhase(IPhase phase) {
        this.phase = phase;
        this.phase.setTurn(this);
    }

    /**
     * Returns this turns current phase
     */
    public IPhase getPhase(){
        return this.phase;
    }

    /**
     * Returns the current player
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Returns the current chapter of the game
     */
    public int getChapter(){
        return controller.getChapter();
    }

    /**
     * Ends the current turn
     */
    public void end(){
        isActive = false;
    }

    /**
     * Checks if the current turn is active
     */
    public Boolean isActive(){
        return isActive;
    }


    /**
     * Returns this turns controller
     */
    public GameController getController() {
        return controller;
    }
}
