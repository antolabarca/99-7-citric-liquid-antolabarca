package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.board.IPanel;

public class LandAtPanelPhase extends AbstractPhase{

    LandAtPanelPhase(){

    }

    /**
     * The player activates the panel where she landed, and the turn ends
     */
    @Override
    public void action() {
        turn.getPlayer().activatePanel();
        turn.end();
    }
}
