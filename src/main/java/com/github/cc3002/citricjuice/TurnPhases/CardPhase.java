package com.github.cc3002.citricjuice.TurnPhases;

public class CardPhase extends AbstractPhase{

    /**
     * Asks the player if she will play a card, and changes the phase to MovePhase
     */
    @Override
    public void action() {
        //something with playing a card (or choosing not to)
        int x = turn.getPlayer().roll();
        turn.setPhase(new MovePhase(x));
    }
}
