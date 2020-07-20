package com.github.cc3002.citricjuice.TurnPhases;

public interface IMovementPhase extends IPhase{
    int getY();
    boolean equals(Object o);
    void action();
}
