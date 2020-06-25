package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.Objects;

public class Turn {
    private final Player player;
    private IPhase phase;
    private final int turn_number;

    public Turn(Player player, int turn_number){
        setPhase(new FirstPhase());
        this.player=player;
        this.turn_number=turn_number;
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
        int c=turn_number/4;
        return c+1;
    }

    /**
     * Ends the current turn
     */
    public void end(){

    }


}
