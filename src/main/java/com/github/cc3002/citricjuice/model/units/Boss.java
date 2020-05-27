package com.github.cc3002.citricjuice.model.units;

public class Boss extends AbstractUnit{

    /**
     * Creates a new Boss.
     *
     * @param hp
     *     the initial (and max) hit points of the boss.
     * @param atk
     *     the base damage the boss does.
     * @param def
     *     the base defense of the boss.
     * @param evd
     *     the base evasion of the boss.
     */
    public Boss(int hp, int atk, int def, int evd){
        super(hp,atk,def,evd);
    }

    /**
     * Creates a copy of this Boss
     */
    public Boss copy(){ return new Boss(maxHP, atk, def, evd);}

}
