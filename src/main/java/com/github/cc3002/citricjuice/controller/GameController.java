package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController implements PropertyChangeListener {
    protected int game_turn;
    public ArrayList<Player> players;
    public Set<IPanel> panelsSet = new HashSet<>();
    protected boolean gameIsWon;
    protected Player winner;

    public GameController(){
        super();
        this.game_turn=0;
        this.players=new ArrayList<Player>();
        gameIsWon = false;
        winner = null;
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
        player.changePanel(panel);
        panel.addPlayer(player);
        players.add(player);
        player.addPlayerListener(this);
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

    public void setNextPanel(IPanel panel, IPanel panel1) {
        if (panel != panel1){
            panel.addNextPanel(panel1);
        }
    }

    public Set<IPanel> getPanels() { return panelsSet;  }


    public void movePlayer() {
        Player player = getTurnOwner();
        int diceRoll = player.roll();
        player.increaseStarsBy(1 + getChapter()/5);
        for (int i=0; i<diceRoll; i++){
            System.out.println("holi toi en el lup");
            if(player.getCurrentPanel()==player.getHome()){
                if (player.checkNorma()){
                    player.normaClear();
                }
                break;
            }
            if( player.getCurrentPanel().getNextPanels().size()==1 &&player.getCurrentPanel().getPlayers().size()==1) {
                    player.getCurrentPanel().removePlayer(player);
                    IPanel newPanel = player.getCurrentPanel().getNextPanels().iterator().next();
                    player.changePanel(newPanel);
                    newPanel.addPlayer(player);
            }
            else { break;}
        }
        player.activatePanel();
        //endTurn();
    }

    public IPanel getPlayerPanel(Player unit) {
        return unit.getCurrentPanel();
    }

    public void setPlayerHome(Player unit, HomePanel panel) {
        unit.setHome(panel);
    }

    public void endTurn() {
        game_turn++;
        /**
         * alguna wea mas? idk
         */
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gameIsWon= true;
        winner = (Player) evt.getNewValue();
    }
}
