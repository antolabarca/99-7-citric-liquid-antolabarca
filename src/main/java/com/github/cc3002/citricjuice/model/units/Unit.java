package com.github.cc3002.citricjuice.model.units;

import java.util.Random;

public abstract class Unit {
    protected final Random random;
    protected final int maxHP;
    protected int currentHP;
    protected int wins;
    protected int stars;
    protected int atk;
    protected int def;
    protected int evd;

    /**
     * Creates a new Unit.
     *
     * @param hp
     *     the initial (and max) hit points of the unit.
     * @param atk
     *     the base damage the unit does.
     * @param def
     *     the base defense of the unit.
     * @param evd
     *     the base evasion of the unit.
     */
    public Unit(final int hp, final int atk, final int def, final int evd){
        this.maxHP=hp;
        this.currentHP=hp;
        this.atk=atk;
        this.def=def;
        this.evd=evd;
        this.wins=0;
        this.stars=0;
        this.random = new Random();
    }

    /**
     * Checks if another object equals this unit
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return maxHP == unit.maxHP &&
                currentHP == unit.currentHP &&
                wins == unit.wins &&
                stars == unit.stars &&
                atk == unit.atk &&
                def == unit.def &&
                evd == unit.evd;
    }

    /**
     * Returns this units's star count.
     */
    public int getStars() {
        return stars;
    }

    /**
     * Increases this unit's star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Reduces this unit's star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }

    /**
     * Returns this unit's amount of wins
     */
    public int getWins(){return wins;}

    /**
     * Increases this unit's win count by a given amount
     */
    public void increaseWinsBy(int amount) {wins += amount;}

    /**
     * Set's the seed for this unit's random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * Returns the unit's max hit points.
     */
    public int getMaxHP() { return maxHP; }

    /**
     * Returns the current unit's attack points.
     */
    public int getAtk() { return atk; }

    /**
     * Returns the current unit's defense points.
     */
    public int getDef() { return def; }

    /**
     * Returns the current unit's evasion points.
     */
    public int getEvd() { return evd; }

    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() { return currentHP; }

    /**
     * Sets the current unit's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    public void setCurrentHP(final int newHP) {
        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }

    /**
     * This unit gets attacked by another one, returns the amount of the attack
     * @param unit
     *      the unit that attacks
     */
    public int attackedBy(Unit unit){
        int roll = unit.roll();
        int attack = roll + unit.getAtk();
        return attack;
    }

    /**
     * This unit defends from an attack with a fixed amount
     * Sets the new HP of the unit
     * @param attack
     *      the amount of the attack
     */
    public void defendsFrom(int attack){
        int roll = this.roll();
        int defense = roll + this.getDef();
        int dmg = Math.max(1, (attack-defense));
        this.setCurrentHP(this.getCurrentHP()-dmg);
    }

    /**
     * This unit attempts to evade an attack with a fixed amount.
     * Sets the new HP if evading is unsuccessful
     * @param attack
     *      the amount of the attack
     */
    public void evades(int attack){
        int roll = this.roll();
        int evade = roll + this.getEvd();
        if (attack > evade){
            this.setCurrentHP(this.getCurrentHP()-attack);
        }
    }


}
