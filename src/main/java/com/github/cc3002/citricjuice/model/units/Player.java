package com.github.cc3002.citricjuice.model.units;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player extends Unit {
  private final String name;
  private int normaLevel;

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
    super(hp, atk, def, evd);
    this.name = name;
    normaLevel = 1;
  }

  /**
   * Returns the character's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
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
  public void setATK(int new_atk){this.atk=new_atk;}

  /**
   * Sets the current player's def to a given amount
   */
  public void setDEF(int new_def){this.def=new_def;}

  /**
   * Sets the current player's evd to a given amount
   */
  public void setEVD(int new_evd){this.evd=new_evd;}

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(name, maxHP, atk, def, evd);
  }
}
