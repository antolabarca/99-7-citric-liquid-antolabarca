package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.BattleDecision;
import com.github.cc3002.citricjuice.model.unit.IUnit;
import com.github.cc3002.citricjuice.model.unit.Player;

public class FightPhase extends AbstractPhase {


    @Override
    public void action() {
        System.out.println(12);
        Player current = turn.getPlayer();
        IPanel panel = current.getCurrentPanel();
        panel.removePlayer(current);
        Player panelPlayer = panel.getPlayers().iterator().next(); //this is assuming that the panel only has one other player, I don't know what should happen in other cases
        panel.addPlayer(current);                                  // and this is awful, I know, but it guarantees getting a different player
        battle(current, panelPlayer);
        if (current.isDown()){
            turn.end();
        }
        else {
            turn.setPhase(new LandAtPanelPhase());
        }
    }



    //this is the exact same method from controller, but I thought that the controller
    //can't interact with the phases, so in case that it can, it can be fixed very easily
    /**
     * Battle between unit1 and unit2
     * @param unit1
     * @param unit2
     */
    public void battle(IUnit unit1, IUnit unit2){
        if (!unit2.isDown()) {
            BattleDecision decision2 = unit2.getBattleDecision();
            unit1.battleRound(unit2, decision2);
            if (unit2.isDown()) {
                unit2.defeatedBy(unit1);
                unit2.dies();
            } else {
                BattleDecision decision1 = unit1.getBattleDecision();
                unit2.battleRound(unit1, decision1);
                if (unit1.isDown()) {
                    unit1.defeatedBy(unit2);
                    unit1.dies();
                }
            }
        }
    }
}
