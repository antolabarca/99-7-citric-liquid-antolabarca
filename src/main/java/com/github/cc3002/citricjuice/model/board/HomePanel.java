package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends Panel{

  public HomePanel(){
    super();
  }

  public HomePanel(int x, int y){super(x,y);}

  /**
   * Restores a player's HP in 1.
   */
  public void activatedBy(final @NotNull Player player) {
    player.setCurrentHP(player.getCurrentHP() + 1);
  }
}
