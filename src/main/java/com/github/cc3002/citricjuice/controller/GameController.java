package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.Boss;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Wild;

import java.util.ArrayList;

public class GameController {
    protected int game_turn;
    protected ArrayList<Player> players;

    public GameController(){
        super();
        this.game_turn=0;
        this.players=new ArrayList<Player>();
    }

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
        players.add(player);
        return player;
    }

    public Wild createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new Wild(name,hitPoints,attack,defense,evasion);
    }

    public Boss createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new Boss(name,hitPoints,attack,defense,evasion);
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
}
