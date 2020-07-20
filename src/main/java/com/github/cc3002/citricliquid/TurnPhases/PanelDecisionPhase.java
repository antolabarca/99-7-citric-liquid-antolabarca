package com.github.cc3002.citricliquid.TurnPhases;

import com.github.cc3002.citricjuice.model.board.IPanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Set;

public class PanelDecisionPhase extends AbstractMovementPhase{

    public PanelDecisionPhase(int y) {
        super(y);
    }

    /**
     * Gets the players panel decision and moves to that panel. Creates a new MovePhase to keep moving
     */
    @Override
    public void action() {
        Set<IPanel> nextPanels = turn.getPlayer().getCurrentPanel().getNextPanels();


        while (turn.getPlayer().getPanelDecision()==null){
            ;
        }
        IPanel decision = turn.getPlayer().getPanelDecision();
        turn.getPlayer().changePanel(decision);
        turn.getPlayer().setPanelDecision(null);
        turn.setPhase(new MovePhase(y-1));
    }

}
