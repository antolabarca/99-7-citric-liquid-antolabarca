package com.github.cc3002.citricjuice.TurnPhases;

public class StarsPhase extends AbstractPhase{

    /**
     * The player gets the amount of stars required as per the chapter of the game.
     * The phase changes to a card phase
     */
    @Override
    public void action() {
        int stars = turn.getChapter()/5 + 1;
        turn.getPlayer().increaseStarsBy(stars);
        turn.setPhase(new CardPhase());
    }

}
