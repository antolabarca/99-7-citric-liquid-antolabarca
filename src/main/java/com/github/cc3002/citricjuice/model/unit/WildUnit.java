package com.github.cc3002.citricjuice.model.unit;

public class WildUnit extends AbstractEnemy{

    /**
     * Creates a new Wild unit.
     *
     * @param name
     *  the name of the wild unit
     * @param hp
     *     the initial (and max) hit points of the wild unit.
     * @param atk
     *     the base damage the wild unit does.
     * @param def
     *     the base defense of the wild unit.
     * @param evd
     *     the base evasion of the wild unit.
     */
    public WildUnit(String name, int hp, int atk, int def, int evd){
        super(name, hp,atk,def,evd);
    }

    /**
     * Creates a copy of this Wild unit
     */
    public WildUnit copy(){ return new WildUnit(name, maxHP, atk, def, evd);}

}
