# 99.7% Citric Liquid

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by [Fruitbat Factory](https://fruitbatfactory.com).

This project is not yet playable, but methods are tested. Those tests have been passed all of the times they were run. If those tests are correctly made, it can be asumed that the classes and methods tested are also correct.

For now, it is assumed that user interface and communication will be implemented in the future.



##Board

The design of the board consists of a *Panel* class (that can be instantiated as a "neutral" Panel in the game, meaning a Panel that does nothing and has no special attribute), and subclasses that extend *Panel* for all other panel types: *Bonus*, *Boss*, *Drop*, *Encounter*, *Home*. *Panel* implements the *IPanel* interface. The method *activatedBy* is implemented for *Bonus*, *Drop*, *Encounter* and *Home*.
For *Boss* and *Encounter* panels, an observer pattern is used to signal the start of an attack to the controller. They both implement IEnemyPanel, and in the future maybe an AbstractEnemyPanel class could be used.

The initial boss/wild of the Enemy panels need to be implemented correctly, since for now it just adds a Enemy unit at the beginning of each panel, but it doesn't really fit the rules of random enemies and Boss panels starting with a wild.

####IEnemyPanels
Have methods to set and get the enemy unit that is in the panel



##Units

The units consist of *AbstractEnemy* and *Player* subclasses of an abstract *AbstractUnit* class, which implements *IUnit* Most methods are defined in the abstract class (including those for being attacked by another unit, defending and evading attacks).

Each class also has a defeatedBy(IUnit) and defeatsPlayer, defeatsWild and defeatsBoss to implement a double dispatch when an unit defeats another one. This is to assign the correct amount of stars and wins to the winner unit.

It is asumed that an unit's HP cannot be less than 0 or more than their max HP. It is asumed that when defending against an attack, an unit will lose the minimum between 1 HP or the attack amount minus the unit's defense and the unit's def value. It is asumed that when evading an attack, an unit will lose 0 HP if the evasion is successful, and the amount of the attack if it isn't. An evasion is successful if the attacked unit's def amount plus a dice roll is greater or equal to the amount of the attack.

The attack method recieves the attacker (an unit) as argument, and returns an int value: the "amount" of the attack, obtained with a dice roll plus the attacking unit's atk value. Both defend and evade methods take this int value as argument, and modify the unit's HP accordingly.

Units also have a dies() method, which in case of the player sets the required roll to 6, and in case of the Enemy units, creates a copy of the unit in the same panel.

There is a game rule that changes some of the Encounter panels to boss Panels and then back to encounter panels. This needs to be implemented in the future when I understand how that's supposed to work.

In the future, player interactions to decide wether to evade or defend are required. This needs an interface to be implemented. In the case of Enemy Units, this is decided by a random.

####Player
The *Player* class has unique methods for setting atk, def and evd, and for getting and increasing the player's Norma level. The players also have an observer method to signal if Norma reaches 4 or 6, since norma 4 makes a boss appear and norma 6 means she won the game.

The player also has a Home panel (with getter and setter), a move method, getter and setter for the panel the player is currently at,  and getters and setters for the players decisions during the game (to evade or defend from an attack, to stop at their home panel or not, to fight or move along if there is another player on the way while they are moving). In the future, this methods will be used with the user's interaction.

####Boss Unit
Has an observer method for the controller to verify when a boss has been defeated (this has to change if more bosses appear, I don't really understand what exactly has to happen, but the controller will need a way to know)

####Enemy Units
Extend the AbstractEnemyUnit class, which has methods to set and get the panel where the unit is, and a method to get battle decisions (this just decides a random decision, with 50% probability for each option)




##Game Controller
The game controller has all of the methods required by the Mediator. In the future, this could be implemented using a facade pattern, but for now it just is a class with a lot of methods.

The controller has methods for creating all of the types of panels and units described previously, and these methods also add them to the controllers list of panels and players when necessary, and add the listeners for the observer patterns.



##Turn Phases
These are implemented using a state pattern. The turn class has a player and a turn number as attributes, and there are phases for the different stages of a turn, that all share an IPhase interfase and AbstractPhase class. The phases are:
* First Phase: in which it is checked if the player isDown, in which case it moves to a recovery phase. If not, it continues to a Stars Phase
* Stars Phase: the player earns the specified amount of stars (currentchapter/5 +1) and moves on to a card Phase
* Card Phase: the player has the option to play a card. For now, this phase does nothing, as cards are not yet implemented. It rolls the dice and continues to a Move Phase for the amount of steps rolled
* Move Phase: recieves an int x as an attribute. The player moves according to the rules, at most x steps. 
The getters for the player's decision are used for the cases in which a decision is required (if there are multiple next panels or another player in the next panel). If the player stops, a land at panel phase starts, if she decides to keep moving another move phase starts for the amount of steps the player has left (x - the amount of steps already done).
* Land at Panel Phase: the player finally stops a panel. If there is a card, it is played (not yet implemented) and the panel is activated by the player. This is the last phase of the turn.
* Recovery phase: the player rolls the dice. If the amount is more or equal to the required amount, they recover their full HP and move on to a stars Phase. If not, their required amount decreases by one, and their turn ends.

----------------------------------------------------------

*este es el readme que estaba cuando entregué:*



# 99.7% Citric Liquid

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by [Fruitbat Factory](https://fruitbatfactory.com).

This project is not yet playable, but methods are tested. Those tests have been passed all of the times they were run. If those tests are correctly made, it can be asumed that the classes and methods tested are also correct.

For now, it is assumed that user interface and communication will be implemented in the future.



##Board

The design of the board consists of a *Panel* class (that can be instantiated as a "neutral" Panel in the game, meaning a Panel that does nothing and has no special attribute), and subclasses that extend *Panel* for all other panel types: *Bonus*, *Boss*, *Drop*, *Encounter*, *Home*. *Panel* implements the *IPanel* interface. The method *activatedBy* is implemented for *Bonus*, *Drop*, *Encounter* and *Home*.
For *Boss* and *Encounter* panels, an observer pattern is used to signal the start of an attack to the controller. They both implement IEnemyPanel, and in the future maybe an AbstractEnemyPanel class could be used.

The initial boss/wild of the Enemy panels need to be implemented correctly, since for now it just adds a Enemy unit at the beginning of each panel, but it doesn't really fit the rules of random enemies and Boss panels starting with a wild.

####IEnemyPanels
Have methods to set and get the enemy unit that is in the panel



##Units

The units consist of *AbstractEnemy* and *Player* subclasses of an abstract *AbstractUnit* class, which implements *IUnit* Most methods are defined in the abstract class (including those for being attacked by another unit, defending and evading attacks).

Each class also has a defeatedBy(IUnit) and defeatsPlayer, defeatsWild and defeatsBoss to implement a double dispatch when an unit defeats another one. This is to assign the correct amount of stars and wins to the winner unit.

It is asumed that an unit's HP cannot be less than 0 or more than their max HP. It is asumed that when defending against an attack, an unit will lose the minimum between 1 HP or the attack amount minus the unit's defense and the unit's def value. It is asumed that when evading an attack, an unit will lose 0 HP if the evasion is successful, and the amount of the attack if it isn't. An evasion is successful if the attacked unit's def amount plus a dice roll is greater or equal to the amount of the attack.

The attack method recieves the attacker (an unit) as argument, and returns an int value: the "amount" of the attack, obtained with a dice roll plus the attacking unit's atk value. Both defend and evade methods take this int value as argument, and modify the unit's HP accordingly.

Units also have a dies() method, which in case of the player sets the required roll to 6, and in case of the Enemy units, creates a copy of the unit in the same panel.

There is a game rule that changes some of the Encounter panels to boss Panels and then back to encounter panels. This needs to be implemented in the future when I understand how that's supposed to work.

In the future, player interactions to decide wether to evade or defend are required. This needs an interface to be implemented. In the case of Enemy Units, this is decided by a random.

####Player
The *Player* class has unique methods for setting atk, def and evd, and for getting and increasing the player's Norma level. The players also have an observer method to signal if Norma reaches 4 or 6, since norma 4 makes a boss appear and norma 6 means she won the game.

The player also has a Home panel (with getter and setter), a move method, getter and setter for the panel the player is currently at,  and getters and setters for the players decisions during the game (to evade or defend from an attack, to stop at their home panel or not, to fight or move along if there is another player on the way while they are moving). In the future, this methods will be used with the user's interaction.

####Boss Unit
Has an observer method for the controller to verify when a boss has been defeated (this has to change if more bosses appear, I don't really understand what exactly has to happen, but the controller will need a way to know)

####Enemy Units
Extend the AbstractEnemyUnit class, which has methods to set and get the panel where the unit is, and a method to get battle decisions (this just decides a random decision, with 50% probability for each option)




##Game Controller
The game controller has all of the methods required by the Mediator. In the future, this could be implemented using a facade pattern, but for now it just is a class with a lot of methods.

The controller has methods for creating all of the types of panels and units described previously, and these methods also add them to the controllers list of panels and players when necessary, and add the listeners for the observer patterns.



##Turn Phases
These are implemented using a state pattern. The turn class has a player and a turn number as attributes, and there are phases for the different stages of a turn, that all share an IPhase interfase and AbstractPhase class. The phases are:
* First Phase: in which it is checked if the player isDown, in which case it moves to a recovery phase. If not, it continues to a Stars Phase
* Stars Phase: the player earns the specified amount of stars (currentchapter/5 +1) and moves on to a card Phase
* Card Phase: the player has the option to play a card. For now, this phase does nothing, as cards are not yet implemented. It rolls the dice and continues to a Move Phase for the amount of steps rolled
* Move Phase: recieves an int x as an attribute. The player moves according to the rules, at most x steps. 
The getters for the player's decision are used for the cases in which a decision is required (if there are multiple next panels or another player in the next panel). If the player stops, a land at panel phase starts, if she decides to keep moving another move phase starts for the amount of steps the player has left (x - the amount of steps already done).
* Land at Panel Phase: the player finally stops a panel. If there is a card, it is played (not yet implemented) and the panel is activated by the player. This is the last phase of the turn.
* Recovery phase: the player rolls the dice. If the amount is more or equal to the required amount, they recover their full HP and move on to a stars Phase. If not, their required amount decreases by one, and their turn ends.
