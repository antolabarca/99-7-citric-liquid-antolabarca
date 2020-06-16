package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController {
    protected int game_turn;
    protected ArrayList<Player> players;
    protected Set<IPanel> panelsSet = new HashSet<>();

    public GameController(){
        super();
        this.game_turn=0;
        this.players=new ArrayList<Player>();
    }

    public BonusPanel createBonusPanel(int id) {
        BonusPanel panel= new BonusPanel(id);
        panelsSet.add(panel);
        return panel;
    }

    public BossPanel createBossPanel(int id) {
        BossPanel panel = new BossPanel(id);
        panelsSet.add(panel);
        return panel;
    }

    public DropPanel createDropPanel(int id) {
        DropPanel panel = new DropPanel(id);
        panelsSet.add(panel);
        return panel;
    }

    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel panel = new EncounterPanel(id);
        panelsSet.add(panel);
        return panel;
    }

    public HomePanel createHomePanel(int id) {
        HomePanel panel = new HomePanel(id);
        panelsSet.add(panel);
        return panel;
    }

    public Panel createNeutralPanel(int id) {
        Panel panel = new Panel(id);
        panelsSet.add(panel);
        return panel;
    }

    public Player createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
        Player player = new Player(name,hitPoints,attack,defense,evasion);
        //algo con lo de que el player este en el IPanel
        players.add(player);
        return player;
    }

    public WildUnit createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new WildUnit(name,hitPoints,attack,defense,evasion);
    }

    public BossUnit createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new BossUnit(name,hitPoints,attack,defense,evasion);
    }

    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        Player player=getTurnOwner();
        player.setNormaGoal(goal);
    }

    public Player getTurnOwner() {
        int n_players=players.size();
        int current=game_turn%n_players;
        return players.get(current);
    }

    public int getChapter(){
        int n_players=players.size();
        int c=game_turn/n_players;
        return c+1; /* 1 is added due to the values of c starting from 0, but game chapters start from 1*/
    }

    public void setNextPanel(IPanel panel, IPanel panel1) { panel.addNextPanel(panel1); }

    public Set<IPanel> getPanels() { return panelsSet;  }

    public void movePlayer() {
        Player player = getTurnOwner();
        player.getPanel().removePlayer(player);
        /**
         * poner alguna wea aca
         */
        //IPanel newPanel = algo;
        player.changePanel(newPanel);
    }

    public IPanel getPlayerPanel(Player unit) {
        return unit.getPanel();
    }

    public void setPlayerHome(Player unit, HomePanel panel) {
        panel.setPlayer(unit);
    }

    public void endTurn() {
        game_turn++;
        /**
         * alguna wea mas? idk
         */
    }
}
