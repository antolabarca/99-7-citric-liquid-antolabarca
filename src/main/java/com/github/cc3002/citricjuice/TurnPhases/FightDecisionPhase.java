package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.unit.FightDecision;

public class FightDecisionPhase extends AbstractMovementPhase{

    public FightDecisionPhase(int y) {
        super(y);
    }


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
