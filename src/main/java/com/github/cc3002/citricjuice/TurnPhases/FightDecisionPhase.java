package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.unit.FightDecision;

public class FightDecisionPhase extends AbstractMovementPhase{

    public FightDecisionPhase(int y) {
        super(y);
    }


    /**
     * gets the players fight decision and creates a new FightPhase if its ENGAGE, a new MovePhase if its IGNORE
     */
    @Override
    public void action() {
        FightDecision decision = turn.getPlayer().getFightDecision();
        if (decision== FightDecision.IGNORE){
            turn.setPhase(new MovePhase(y));
        }else{
            turn.setPhase(new FightPhase());
        }
    }
}
