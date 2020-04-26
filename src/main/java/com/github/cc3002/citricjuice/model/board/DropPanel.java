package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

public class DropPanel extends Panel{

  public DropPanel(){
    super();
  }

  /**
   * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
   */
  public void activatedBy(final @NotNull Player player) {
    player.reduceStarsBy(player.roll() * player.getNormaLevel());
  }
}
