package com.github.cc3002.citricjuice.TurnPhases;

public class HomeDecisionPhase extends AbstractMovementPhase{

    public HomeDecisionPhase(int y) {
        super(y);
    }

    /**
     * Checks the players homeDecision and creates a new LandAtPanelPhase if its true, a new MovePhase if its false
     */
    @Override
    public void action() {
        boolean stopAtHome = turn.getPlayer().getHomeDecision();
        if (stopAtHome){
            turn.setPhase(new LandAtPanelPhase());
        } else{
            turn.setPhase(new MovePhase(y));
        }
    }
}
