package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.IPanel;

import static com.github.cc3002.citricjuice.model.NormaGoal.STARS;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player extends AbstractUnit {
  private int normaLevel;
  private NormaGoal normaGoal;
  private IPanel panel;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def, final int evd) {
    super(name, hp, atk, def, evd);
    this.normaLevel = 1;
    this.normaGoal = STARS;
  }

  /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Returns the player's norma goal
   */
  public NormaGoal getNormaGoal(){ return normaGoal; }

  /**
   * Returns the panel where the player is
   */
  public IPanel getPanel() { return panel;}

  /**
   * Changes the panel where the player is
   * @param newPanel
   *      the players new panel
   */
  public void changePanel(IPanel newPanel) {
    this.panel = newPanel;
    newPanel.addPlayer(this);
    newPanel.activatedBy(this);
  }

  /**
   * Checks if the player has reached their norma goal
   */
  public boolean checkNorma(){
    int [] stars_goal = {-1, 10, 30, 70, 120, 200}; /*amount of stars needed to get norma i+1*/
    int [] wins_goal = {-1, -1, 2, 5, 9, 14}; /*amount of wins needed to get norma i+1*/
    /* the arrays have -1 in the position 0, as norma levels start at 1. wins has a -1 in position 1 as the first goal is always stars */
    if (this.getNormaGoal()==STARS){return this.getStars()>= stars_goal[this.getNormaLevel()];}
    else {return this.getWins()>= wins_goal[this.getNormaLevel()];}
    /*this is horrible, I'm sorry*/
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
  }

  /**
   * Sets the players new norma goal
   * @param goal
   *      the new norma goal for the player
   */
  public void setNormaGoal(NormaGoal goal) {
    this.normaGoal=goal;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    final Player player = (Player) o;
    return getMaxHP() == player.getMaxHP() &&
           getAtk() == player.getAtk() &&
           getDef() == player.getDef() &&
           getEvd() == player.getEvd() &&
           getNormaLevel() == player.getNormaLevel() &&
           getStars() == player.getStars() &&
           getCurrentHP() == player.getCurrentHP() &&
           getName().equals(player.getName());
  }

  /**
   * Sets the current player's atk to a given amount
   */
  public void setAtk(int new_atk){this.atk=new_atk;}

  /**
   * Sets the current player's def to a given amount
   */
  public void setDef(int new_def){this.def=new_def;}

  /**
   * Sets the current player's evd to a given amount
   */
  public void setEvd(int new_evd){this.evd=new_evd;}

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(name, maxHP, atk, def, evd);
  }

  /**
   * This player is defeated by another unit
   * @param winner
   *      the unit that defeated this player
   */
  @Override
  public void defeatedBy(IUnit winner) {
    winner.defeatPlayer(this);
  }

  /**
   * This player defeats another player, increases this player's wins and stars, reduces the loosing player's stars
   * @param player
   *      the player that lost
   */
  @Override
  public void defeatPlayer(Player player) {
    this.increaseWinsBy(2);
    int star = player.getStars()/2;
    this.increaseStarsBy(star);
    player.reduceStarsBy(star);
  }

  /**
   * This player defeats a boss unit, increases this player's wins and stars, reduces the boss units stars
   * @param boss
   *      the defeated boss
   */
  @Override
  public void defeatBoss(BossUnit boss) {
    this.increaseWinsBy(3);
    int star = boss.getStars();
    this.increaseStarsBy(star);
    boss.reduceStarsBy(star);
  }

  /**
   * This player defeats a wild unit, increases this player's wins and stars, reduces the wild units stars
   * @param wild
   *      the defeated wild unit
   */
  @Override
  public void defeatWild(WildUnit wild) {
    this.increaseWinsBy(1);
    int star = wild.getStars();
    this.increaseStarsBy(star);
    wild.reduceStarsBy(star);
  }


}
