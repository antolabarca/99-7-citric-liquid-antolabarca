package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import org.jetbrains.annotations.NotNull;

public class BonusPanel extends Panel {

  public BonusPanel(){
    this(0,0);
  }

  public BonusPanel(int x, int y){super(x,y);}

  /**
   * Increases the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  public void activatedBy(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
}
