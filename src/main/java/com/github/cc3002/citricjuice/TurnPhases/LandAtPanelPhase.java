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
        //if there is a card at the panel, do something
        turn.getPlayer().activatePanel();
        turn.end();
    }
}
