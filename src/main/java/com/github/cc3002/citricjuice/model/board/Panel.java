package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
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
    protected final int x; /* x coord of Panel*/
    protected final int y; /* y coord of Panel*/
    protected final Set<Panel> nextPanels = new HashSet<>();

  /**
   * Creates a new panel.
   */
  public Panel() {
      this(0,0);
  }

    /**
     * Creates a new panel with coordenates x,y
     * @param x the x coordenate of the panel
     * @param y the y coordenate of the panel
     */
  public Panel(int x, int y){
      this.x=x;
      this.y=y;
  }

  /**
   * Checks if a panel equals this one
   */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panel panel = (Panel) o;
        return x == panel.x &&
                y == panel.y &&
                Objects.equals(nextPanels, panel.nextPanels);
    }

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
   *
   */
  public void activatedBy(final Player player) {  }
}


