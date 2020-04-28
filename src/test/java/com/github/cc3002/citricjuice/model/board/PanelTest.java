package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private HomePanel testHomePanel;
  private Panel testNeutralPanel;
  private BonusPanel testBonusPanel;
  private DropPanel testDropPanel;
  private EncounterPanel testEncounterPanel;
  private BossPanel testBossPanel;
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel();
    testBossPanel = new BossPanel();
    testDropPanel = new DropPanel();
    testEncounterPanel = new EncounterPanel();
    testHomePanel = new HomePanel();
    testNeutralPanel = new Panel();
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void equalsTest() {
    assertNotEquals(testNeutralPanel, new Object());
    assertEquals(testNeutralPanel, testNeutralPanel);
    assertEquals(testNeutralPanel, new Panel());
    assertNotEquals(testNeutralPanel, testDropPanel);
    assertNotEquals(testDropPanel, testNeutralPanel);
    assertNotEquals(testBonusPanel,testDropPanel);
    assertNotEquals(new Panel(), new DropPanel());
    assertNotEquals(new Panel(1,2),new DropPanel(1,2));
    assertNotEquals(new DropPanel(1,2), new Panel(1,2));
    assertNotEquals(new DropPanel(), new Panel());
    assertNotEquals(new BonusPanel(), new DropPanel());
  }

  @Test
  public void PanelConstructorTest() {
    assertEquals(testNeutralPanel, testNeutralPanel);
    assertNotEquals(testNeutralPanel, new Object());
    assertFalse(testNeutralPanel.equals(null));
    assertNotSame(new Panel(), testNeutralPanel);
    assertEquals(new Panel(), testNeutralPanel);
  }

  @Test
  public void BonusPanelConstructorTest() {
    assertEquals(testBonusPanel, testBonusPanel);
    assertNotEquals(testBonusPanel, new Object());
    assertFalse(testBonusPanel.equals(null));
    assertNotSame(new BonusPanel(), testBonusPanel);
    assertEquals(new BonusPanel(), testBonusPanel);
  }

  @Test
  public void BossPanelConstructorTest() {
    assertEquals(testBossPanel, testBossPanel);
    assertNotEquals(testBossPanel, new Object());
    assertFalse(testBossPanel.equals(null));
    assertNotSame(new BossPanel(), testBossPanel);
    assertEquals(new BossPanel(), testBossPanel);
  }

  @Test
  public void DropPanelConstructorTest() {
    assertEquals(testDropPanel, testDropPanel);
    assertNotEquals(testDropPanel, new Object());
    assertFalse(testDropPanel.equals(null));
    assertNotSame(new DropPanel(), testDropPanel);
    assertEquals(new DropPanel(), testDropPanel);
  }

  @Test
  public void EncounterPanelConstructorTest() {
    assertEquals(testEncounterPanel, testEncounterPanel);
    assertNotEquals(testEncounterPanel, new Object());
    assertFalse(testEncounterPanel.equals(null));
    assertNotSame(new EncounterPanel(), testEncounterPanel);
    assertEquals(new EncounterPanel(), testEncounterPanel);
  }

  @Test
  public void HomePanelConstructorTest() {
    assertEquals(testHomePanel, testHomePanel);
    assertNotEquals(testHomePanel, new Object());
    assertFalse(testHomePanel.equals(null));
    assertNotSame(new HomePanel(), testHomePanel);
    assertEquals(new HomePanel(), testHomePanel);
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new Panel();
    final var expectedPanel2 = new Panel(1,2);

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2),
            testNeutralPanel.getNextPanels());
  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());

    suguri.setCurrentHP(1);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getCurrentHP());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
  // endregion
}