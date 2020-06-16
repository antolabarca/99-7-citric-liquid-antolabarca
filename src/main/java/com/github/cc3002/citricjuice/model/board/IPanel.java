package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.Set;

public interface IPanel {
    boolean equals(Object o);
    int getId();
    Set<IPanel> getNextPanels();
    void addNextPanel(final IPanel panel);
    void activatedBy(Player player);
    Set<Player> getPlayers();
    void removePlayer(Player player);
}
