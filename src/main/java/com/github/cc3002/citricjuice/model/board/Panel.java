package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

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
public class Panel implements IPanel { /*this is a neutral panel*/
    protected final int id; /* id of Panel*/
    protected final Set<IPanel> nextPanels = new HashSet<>();

    /**
     * Creates a new panel with coordenates x,y
     * @param id the id of the panel
     */
  public Panel(int id){
      this.id=id;
  }

    /**
     * Checks if an object o is equal to this Panel
     * @param o the object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panel panel = (Panel) o;
        return id == panel.id &&
                Objects.equals(nextPanels, panel.nextPanels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nextPanels);
    }

    /**
     * Returns this panel's id
     */
    public int getId(){ return this.id; }

    /**
    * Returns a copy of this panel's next ones.
    */
    public Set<IPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel
   *     the panel to be added.
   */
  public void addNextPanel(final IPanel panel) {
    nextPanels.add(panel);
  }

  /**
   *
   */
  public void activatedBy(Player player) {  }
}


