package com.github.cc3002.citricjuice.model.unit;

public class BossUnit extends AbstractEnemy{

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
    public BossUnit(String name, int hp, int atk, int def, int evd){
        super(name, hp,atk,def,evd);
    }

    /**
     * Creates a copy of this Boss
     */
    public BossUnit copy(){ return new BossUnit(name, maxHP, atk, def, evd);}

}
