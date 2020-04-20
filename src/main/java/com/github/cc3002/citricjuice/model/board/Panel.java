package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public class Panel { /*this is a neutral panel*/
  //private final PanelType type;
  private final Set<Panel> nextPanels = new HashSet<>();

  /**
   * Creates a new panel.
   */
  public Panel() { }

  /**
   * Returns a copy of this panel's next ones.
   */
  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel
   *     the panel to be added.
   */
  public void addNextPanel(final Panel panel) {
    nextPanels.add(panel);
  }

  /**
   * Creo que no es necesario dejar este metodo, ya que en este caso no hace nada, pero originalmente
   * también estaba el caso de que type=neutral en activatedBy, asi que preferi dejarlo por si acaso
   */
  public void activatedBy(final Player player) {
    return null;
  }
}


public class BonusPanel extends Panel {

  public BonusPanel(){
    super();
  }

  /**
   * Increases the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  private static void activatedBy(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
}


public class DropPanel extends Panel{

  public DropPanel(){
    super();
  }

  /**
   * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
   */
  private static void activatedBy(final @NotNull Player player) {
    player.reduceStarsBy(player.roll() * player.getNormaLevel());
  }
}


public class HomePanel extends Panel{

  public HomePanel(){
    super();
  }

  /**
   * Restores a player's HP in 1.
   */
  private static void activatedBy(final @NotNull Player player) {
    player.setCurrentHP(player.getCurrentHP() + 1);
  }
}

public class EncounterPanel extends Panel{

  public EncounterPanel(){
    super();
  }
}

public class BossPanel extends Panel{

  public BossPanel(){
    super();
  }
}

