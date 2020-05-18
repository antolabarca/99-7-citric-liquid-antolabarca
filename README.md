<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by [Fruitbat Factory](https://fruitbatfactory.com).

This project is not yet playable, but all methods are tested in the *UnitsTest* and *PanelTest* classes, the only classes that can be run at this point. Those tests have been passed all of the times they were run. If those tests are correctly made, it can be asumed that the classes and methods tested are also correct.

This project is made asuming that a combat interface and user interaction will be implemented in the future, since for now there are only methods for the different parts (attack, defense, evade).


###Board

The design of the board consists of a *Panel* class (that can be instantiated as a "neutral" Panel in the game, meaning a Panel that does nothing and has no special attribute), and subclasses that extend *Panel* for all other panel types: *Bonus*, *Boss*, *Drop*, *Encounter*, *Home*. The method *activatedBy* is implemented for *Bonus*, *Drop*, *Encounter* and *Home*. It has not been implemented for *Boss* and *Encounter* panels, since the combat and user interactions in combat have not been done yet, and *activatedBy* for those panels should begin a combat.

Work that could be done in the future includes:
* Panels should have a way of knowing wich players are currently standing in them (since 2 players in the same panel, no matter which panel type, can also begin a combat).
* *Boss* and *Encounter* panels could both be subclasses of a "fight panel" abstract class, since they are both very similar, only changing the type of enemy that the player encounters.
* *Home* panels also need an atribute to know which player's home they are, since it should be different if a player lands on their own home or someone else's.


###Units

The units consist of *Wild*, *Boss* and *Player* subclasses of an abstract *Unit* class. Most methods are defined in the abstract class (including those for being attacked by another unit, defending and evading attacks) but the *Player* class has unique methods for setting atk, def and evd and for getting the player's name, and for getting and increasing the player's Norma level.

It is asumed that an unit's HP cannot be less than 0 or more than their max HP. It is asumed that when defending against an attack, an unit will lose the minimum between 1 HP or the attack amount minus the unit's defense and the unit's def value. It is asumed that when evading an attack, an unit will lose 0 HP if the evasion is successful, and the amount of the attack if it isn't. An evasion is successful if the attacked unit's def amount plus a dice roll is greater or equal to the amount of the attack.

The attack method recieves the attacker (an unit) as argument, and returns an int value: the "amount" of the attack, obtained with a dice roll plus the attacking unit's atk value. Both defend and evade methods take this int value as argument, and modify the unit's HP accordingly.

In the future, the combat needs to be implemented, as well as player interactions. Also, a method to decide if non player units will defend or evade an attack. A method for a player to win a combat is also necessary. This method should be able to increase the wins and stars of the winner, and decrease the stars for the loser.