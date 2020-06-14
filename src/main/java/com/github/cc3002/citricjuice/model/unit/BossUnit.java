package com.github.cc3002.citricjuice.model.unit;

public class Boss extends AbstractEnemy{

    /**
     * Creates a new Boss.
     *
     * @param  name
     *      the name of the boss
     * @param hp
     *     the initial (and max) hit points of the boss.
     * @param atk
     *     the base damage the boss does.
     * @param def
     *     the base defense of the boss.
     * @param evd
     *     the base evasion of the boss.
     */
    public Boss(String name, int hp, int atk, int def, int evd){
        super(name, hp,atk,def,evd);
    }

    /**
     * Creates a copy of this Boss
     */
    public Boss copy(){ return new Boss(name, maxHP, atk, def, evd);}

}
