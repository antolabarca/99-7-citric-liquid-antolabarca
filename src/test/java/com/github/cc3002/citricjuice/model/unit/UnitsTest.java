package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.NormaGoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class UnitsTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static String BOSS_NAME = "Boss";
  private final static String WILD_NAME = "Wild";
  private Player suguri;
  private BossUnit testBossUnit;
  private WildUnit testWildUnit;


  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    testBossUnit = new BossUnit(BOSS_NAME, 7, 2,1,-1);
    testWildUnit = new WildUnit(WILD_NAME, 5, 1,0,-1);
  }

  @Test
  public void PlayerConstructorTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    assertEquals(suguri,suguri);
    assertEquals(expectedSuguri, suguri);
    assertNotSame(expectedSuguri, suguri);
  }

  @Test
  public void BossConstructorTest(){
    final var expectedBoss = new BossUnit(BOSS_NAME,7,2,1,-1);
    assertEquals(testBossUnit, testBossUnit);
    assertEquals(expectedBoss, testBossUnit);
    assertNotSame(expectedBoss, testBossUnit);
  }

  @Test
  public void WildConstructorTest(){
    final var expectedWild = new WildUnit(WILD_NAME,5,1,0,-1);
    assertEquals(testWildUnit, testWildUnit);
    assertEquals(expectedWild, testWildUnit);
    assertNotSame(expectedWild, testWildUnit);
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    assertNotEquals(suguri, o);
    assertEquals(suguri, suguri);
    assertNotEquals(testBossUnit, testWildUnit);
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    assertEquals(expectedSuguri, suguri);
    final var unexpected=new WildUnit(BOSS_NAME, 7,2,1,-1);
    assertNotEquals(unexpected, testBossUnit);
  }

  @Test
  public void getNameTest(){
    assertEquals(PLAYER_NAME, suguri.getName());
  }

  @Test
  public void getStatsTest(){
    assertEquals(4, suguri.getMaxHP());
    assertEquals(1, suguri.getAtk());
    assertEquals(-1, suguri.getDef());
    assertEquals(2, suguri.getEvd());
    assertEquals(4, suguri.getCurrentHP());
  }

  @Test
  public void PlayerSetStatsTest(){
    assertEquals(1, suguri.getAtk());
    suguri.setAtk(2);
    assertEquals(2, suguri.getAtk());
    assertEquals(-1, suguri.getDef());
    suguri.setDef(0);
    assertEquals(0, suguri.getDef());
    assertEquals(2, suguri.getEvd());
    suguri.setEvd(1);
    assertEquals(1, suguri.getEvd());
  }

  @Test
  public void StarsTest() {
    assertEquals(0, suguri.getStars());
    suguri.increaseStarsBy(10);
    assertEquals(10, suguri.getStars());
    suguri.reduceStarsBy(4);
    assertEquals(6, suguri.getStars());
    suguri.reduceStarsBy(8);
    assertEquals(0, suguri.getStars());
  }

  @Test
  public void WinsTest(){
    assertEquals(0, suguri.getWins());
    suguri.increaseWinsBy(2);
    assertEquals(2, suguri.getWins());
  }

  @Test
  public void HitPointsTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.setCurrentHP(2);
    assertEquals(2, suguri.getCurrentHP());
    suguri.setCurrentHP(-1);
    assertEquals(0, suguri.getCurrentHP());
    suguri.setCurrentHP(5);
    assertEquals(4, suguri.getCurrentHP());
  }

  @Test
  public void normaTest() {
    assertEquals(NormaGoal.STARS, suguri.getNormaGoal());
    assertEquals(1, suguri.getNormaLevel());
    suguri.increaseStarsBy(12);
    assertTrue(suguri.checkNorma());
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
    suguri.setNormaGoal(NormaGoal.WINS);
    assertEquals(NormaGoal.WINS, suguri.getNormaGoal());
    suguri.increaseWinsBy(3);
    assertTrue(suguri.checkNorma());
    suguri.normaClear();
    assertEquals(3, suguri.getNormaLevel());
  }

  @Test
  public void PlayerCopyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(suguri, actualSuguri);
  }

  @Test
  public void BossCopyTest() {
    final var expectedBoss = new BossUnit(BOSS_NAME,7,2,1,-1);
    final var actualBoss = testBossUnit.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedBoss, actualBoss);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(testBossUnit, actualBoss);
  }

  @Test
  public void WildCopyTest() {
    final var expectedWild = new WildUnit(WILD_NAME,5,1,0,-1);
    final var actualWild = testWildUnit.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedWild, actualWild);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(testWildUnit, actualWild);
  }

  // region : consistency tests
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHP() + 1)
                       - 2 * suguri.getMaxHP();
    suguri.setCurrentHP(testHP);
    assertTrue(0 <= suguri.getCurrentHP()
               && suguri.getCurrentHP() <= suguri.getMaxHP(),
               suguri.getCurrentHP() + "is not a valid HP value"
               + System.lineSeparator() + "Test failed with random seed: "
               + testSeed);
  }

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
                 "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    assertTrue(roll >= 1 && roll <= 6,
               roll + "is not in [1, 6]" + System.lineSeparator()
               + "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void AttackTest() {
    long testSeed = new Random().nextLong();
    testBossUnit.setSeed(testSeed);
    final int bossAtk = suguri.attackedBy(testBossUnit);
    assertTrue(bossAtk>=3 && bossAtk <= 8, bossAtk + "is not in [3, 8]" + System.lineSeparator()
            + "Boss attacking test failed with random seed: "+ testSeed);
    long testSeed1 = new Random().nextLong();
    testWildUnit.setSeed(testSeed1);
    final int wildAtk = testBossUnit.attackedBy(testWildUnit);
    assertTrue(wildAtk>=2 && wildAtk <=7, wildAtk + "is not in [2,7]" + System.lineSeparator()+
            "Wild attacking test failed with random seed: "+ testSeed1);
    long testSeed2 = new Random().nextLong();
    suguri.setSeed(testSeed2);
    final int playerAtk = testWildUnit.attackedBy(suguri);
    assertTrue(playerAtk >= 2 && playerAtk <= 7, playerAtk + "is not in [2,7]" + System.lineSeparator()+
            "Suguri attacking test failed with random seed: "+testSeed2);
  }

  @RepeatedTest(100)
  public void defendsTest(){
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final long testSeed1 = new Random().nextLong();
    testBossUnit.setSeed(testSeed1);
    int attack = suguri.attackedBy(testBossUnit);
    suguri.defendsFrom(attack);
    int suguri_damage= suguri.getMaxHP() - suguri.getCurrentHP();
    assertTrue((suguri_damage==1 || suguri_damage==suguri.getMaxHP() || (suguri_damage <= attack - suguri.getDef() -1 && suguri_damage >= attack - suguri.getDef() -6)),
            suguri_damage + "is not in [1,"+ (attack- suguri.getDef() -1)+"]. defends" +
                    "test failed with random seeds: "+testSeed + ", " + testSeed1);
  }

  @RepeatedTest(100)
  public void evadesTest(){
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final long testSeed1 = new Random().nextLong();
    testWildUnit.setSeed(testSeed1);
    int attack = testWildUnit.attackedBy(suguri);
    testWildUnit.evades(attack);
    int testWild_damage = testWildUnit.getMaxHP() - testWildUnit.getCurrentHP();
    assertTrue((testWild_damage==0 || testWild_damage == attack || testWild_damage == testWildUnit.getMaxHP()), testWild_damage +
            "is not in {0," + attack + "}. evades test failed with random seeds: "+ testSeed + ", "+ testSeed1);
  }

  // endregion
}
