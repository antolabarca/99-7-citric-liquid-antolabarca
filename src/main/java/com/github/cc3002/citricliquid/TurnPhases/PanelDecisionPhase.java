package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.board.IPanel;

public class PanelDecisionPhase extends AbstractMovementPhase{

    public PanelDecisionPhase(int y) {
        super(y);
    }

    /**
     * Gets the players panel decision and moves to that panel. Creates a new MovePhase to keep moving
     */
    @Override
    public void action() {
        while (turn.getPlayer().getPanelDecision()==null){
            ;
        }
        IPanel decision = turn.getPlayer().getPanelDecision();
        turn.getPlayer().changePanel(decision);
        turn.getPlayer().setPanelDecision(null);
        turn.setPhase(new MovePhase(y-1));
    }
}
