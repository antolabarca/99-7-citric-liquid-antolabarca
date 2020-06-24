package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WinHandler implements PropertyChangeListener{
    private GameController controller;

    public WinHandler(GameController controller){
        this.controller= controller;
    }

    /**
     * Notifies the controller that a player has won
     * @param evt the propertyChangeEvent that signals a player won
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Player winner = (Player) evt.getNewValue();
        controller.playerWins(winner);
    }
}
