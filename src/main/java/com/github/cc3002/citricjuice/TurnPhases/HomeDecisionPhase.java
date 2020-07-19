package com.github.cc3002.citricjuice.TurnPhases;

public class HomeDecisionPhase extends AbstractMovementPhase{

    public HomeDecisionPhase(int y) {
        super(y);
    }

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
