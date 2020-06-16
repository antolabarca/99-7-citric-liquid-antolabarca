package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends Panel{
  private Player player;

  public HomePanel(int id){
    super(id);
  }

  /**
   * Restores a player's HP in 1.
   */
  public void activatedBy(final @NotNull Player player) {
    player.setCurrentHP(player.getCurrentHP() + 1);
  }

  /**
   * Assigns the owner of this Home panel
   * @param player
   *      the player whose home this is
   */
  public void setPlayer(Player player){
    this.player = player;
  }

  /**
   * Returns the player that owns this home panel
   */
  public Player getPlayer(){
    return this.player;
  }
}
