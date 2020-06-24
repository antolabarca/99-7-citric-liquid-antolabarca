package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.FightDecision;

import java.util.Objects;

public class MovePhase extends AbstractPhase{
    int x;

    public MovePhase(int x){
        super();
        this.x=x;
    }

    /**
     * Checks if another object equals this one
     * @param o the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovePhase)) return false;
        if (!super.equals(o)) return false;
        MovePhase movePhase = (MovePhase) o;
        return x == movePhase.x;
    }


    /**
     * Moves the player to a new panel, stopping along the way if decision needs to be made, and changes phase to the land at panel phase
     */
    @Override
    public void action() {
        int y = turn.getPlayer().move(x);
        if (y==0){ //the player moved all the steps they had left
            turn.setPhase(new LandAtPanelPhase());
        } else if (turn.getPlayer().getCurrentPanel().getPlayers().size()>1){ //there is another player in the panel
            FightDecision decision = turn.getPlayer().getFightDecision();
            if (decision== FightDecision.IGNORE){
                turn.setPhase(new MovePhase(y));
            }else{
                turn.setPhase(new FightPhase());
            }
        }
        else if (turn.getPlayer().getCurrentPanel().getNextPanels().size()>1){ //the player needs to decide a panel
            IPanel decision = turn.getPlayer().getPanelDecision();
            turn.getPlayer().changePanel(decision);
            turn.setPhase(new MovePhase(y-1));
        } else if (turn.getPlayer().getCurrentPanel()==turn.getPlayer().getHome()){
            boolean stopAtHome = turn.getPlayer().getHomeDecision();
            if (stopAtHome){
                turn.setPhase(new LandAtPanelPhase());
            } else{
                turn.setPhase(new MovePhase(y));
            }
        }

    }
}
