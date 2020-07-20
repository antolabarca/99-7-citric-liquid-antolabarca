package com.github.cc3002.citricliquid.TurnPhases;

public class RecoveryPhase extends AbstractPhase{


    /**
     * The player rolls the dice. If the roll is equal or more than her required roll, it changes to a Stars phase
     * If the roll is less, the turn ends.
     */
    public void action(){
        int x = turn.getPlayer().roll();
        int required_roll = turn.getPlayer().getRequiredRoll();
        if (x >= required_roll){
            turn.getPlayer().setCurrentHP(turn.getPlayer().getMaxHP());
            turn.setPhase(new StarsPhase());
        }else {
            turn.getPlayer().setRequiredRoll(turn.getPlayer().getRequiredRoll()-1);
            turn.end();
        }
    }
}
