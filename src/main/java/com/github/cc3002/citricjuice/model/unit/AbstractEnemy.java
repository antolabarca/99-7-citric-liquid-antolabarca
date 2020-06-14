package com.github.cc3002.citricjuice.model.unit;

public abstract class AbstractEnemy extends AbstractUnit {
    /**
     * Creates a new Unit.
     *
     * @param name
     * @param hp   the initial (and max) hit points of the unit.
     * @param atk  the base damage the unit does.
     * @param def  the base defense of the unit.
     * @param evd
     */
    public AbstractEnemy(String name, int hp, int atk, int def, int evd) {
        super(name, hp, atk, def, evd);
    }
}
