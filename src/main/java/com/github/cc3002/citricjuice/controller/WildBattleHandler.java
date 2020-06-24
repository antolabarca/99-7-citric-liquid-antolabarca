package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.unit.WildUnit;
import com.github.cc3002.citricjuice.model.unit.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WildBattleHandler implements PropertyChangeListener {
    private GameController controller;

    public WildBattleHandler(GameController controller){
        this.controller= controller;
    }

    /**
     * Notifies the controller that a fight must begin
     * @param evt the propertyChangeEvent that signals a player landed on an enemy panel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        IUnit enemy = (WildUnit) evt.getNewValue();
        controller.battle(controller.getTurnOwner(), enemy);
    }
}
