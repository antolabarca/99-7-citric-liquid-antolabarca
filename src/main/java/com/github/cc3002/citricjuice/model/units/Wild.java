package com.github.cc3002.citricjuice.model.units;

public class Wild extends AbstractUnit{

    /**
     * Creates a new Wild unit.
     *
     * @param hp
     *     the initial (and max) hit points of the wild unit.
     * @param atk
     *     the base damage the wild unit does.
     * @param def
     *     the base defense of the wild unit.
     * @param evd
     *     the base evasion of the wild unit.
     */
    public Wild(int hp, int atk, int def, int evd){
        super(hp,atk,def,evd);
    }

    /**
     * Creates a copy of this Wild unit
     */
    public Wild copy(){ return new Wild(maxHP, atk, def, evd);}

}
