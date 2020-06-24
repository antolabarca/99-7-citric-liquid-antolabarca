package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BossDefeatHandler implements PropertyChangeListener {
    private GameController controller;

    public BossDefeatHandler(GameController controller){
        this.controller= controller;
    }

    /**
     * Notifies the controller that a boss has been defeated
     * @param evt the propertyChangeEvent that signals a boss was defeated
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BossUnit boss = (BossUnit) evt.getNewValue();
        controller.bossDefeated(boss);
    }
}
