package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.Boss;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Wild;

public class GameController {

    public BonusPanel createBonusPanel(int id) {
        return new BonusPanel(id);
    }

    public BossPanel createBossPanel(int id) {
        return new BossPanel(id);
    }

    public DropPanel createDropPanel(int id) {
        return new DropPanel(id);
    }

    public EncounterPanel createEncounterPanel(int id) {
        return new EncounterPanel(id);
    }

    public HomePanel createHomePanel(int id) {
        return new HomePanel(id);
    }

    public Panel createNeutralPanel(int id) {
        return new Panel(id);
    }

    public Player createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
        Player player = new Player(name,hitPoints,attack,defense,evasion);
        //algo con lo de que el player este en el IPanel
        return player;
    }

    public Wild createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new Wild(name,hitPoints,attack,defense,evasion);
    }

    public Boss createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new Boss(name,hitPoints,attack,defense,evasion);
    }

}
