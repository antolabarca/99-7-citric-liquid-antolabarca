package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Norma4Handler implements PropertyChangeListener {
    private GameController controller;

    public Norma4Handler(GameController controller){
        this.controller= controller;
    }

    /**
     * Notifies the controller that a player has reached norma 4
     * @param evt the propertyChangeEvent that signals a player won
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Player norma4Player = (Player) evt.getNewValue();
        controller.playerReachedNorma4(norma4Player);
    }
}
