package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class ControllerTest {
    private GameController newController;
    private GameController controller;
    private List<IPanel> panelSuppliers;
    private List<Player> testPlayers;
    private List<WildUnit> testWildUnits;
    private List<BossUnit> testBosses;
    private String[] player_names = {"suguri", "kai", "yuki"};
    private String[] boss_names = {"flying castle", "boss", "uwu"};
    private String[] wild_names = {"wild", "wilder", "wildest"};
    private int[] hp = {4,6,5};
    private int[] def = {2, -1, 0};
    private int[] atk = {1, 2, -1};
    private int[] evd = {1, -1, -2};

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        newController = new GameController();
        panelSuppliers =
                List.of(controller.createBonusPanel(0), controller.createBossPanel(1), controller.createDropPanel(2),
                        controller.createEncounterPanel(3), controller.createHomePanel(4),
                        controller.createNeutralPanel(5));
        testPlayers = new ArrayList<Player>();
        for (int i=0; i<3; i++){testPlayers.add(controller.createPlayer(player_names[i],hp[i],atk[i],def[i],evd[i], panelSuppliers.get(i)));}
        testWildUnits = new ArrayList<WildUnit>();
        for (int i=0; i<3; i++){testWildUnits.add(controller.createWildUnit(wild_names[i],hp[i],atk[i],def[i],evd[i]));}
        testBosses = new ArrayList<BossUnit>();
        for (int i=0; i<3; i++){testBosses.add(controller.createBossUnit(boss_names[i],hp[i],atk[i],def[i],evd[i]));}
    }

    @Test
    public void testConstructor(){
        assertEquals(newController, newController);
        assertNotEquals(newController, new Object());
        assertFalse(newController.equals(null));
        assertNotSame(new GameController(), newController);
        assertEquals(new GameController(), newController);
    }

    @Test
    public void testCreateBonusPanel(){
        assertEquals(new BonusPanel(0), panelSuppliers.get(0));
        assertTrue(controller.getPanels().contains(panelSuppliers.get(0)));
    }

    @Test
    public void testCreateBossPanel(){
        assertEquals(new BossPanel(1), panelSuppliers.get(1));
        assertTrue(controller.getPanels().contains(panelSuppliers.get(1)));
    }

    @Test
    public void testCreateDropPanel(){
        assertEquals(new DropPanel(2), panelSuppliers.get(2));
        assertTrue(controller.getPanels().contains(panelSuppliers.get(2)));
    }

    @Test
    public void testCreateEncounterPanel(){
        assertEquals(new EncounterPanel(3), panelSuppliers.get(3));
        assertTrue(controller.getPanels().contains(panelSuppliers.get(3)));
    }

    @Test
    public void testCreateSetHomePanel(){
        assertEquals(new HomePanel(4), panelSuppliers.get(4));
        assertTrue(controller.getPanels().contains(panelSuppliers.get(4)));
        controller.setPlayerHome(testPlayers.get(0), (HomePanel) panelSuppliers.get(4));
        assertEquals(panelSuppliers.get(4), testPlayers.get(0).getHome());
    }

    @Test
    public void testCreateNeutralPanel(){
        assertEquals(new Panel(5), panelSuppliers.get(5));
        assertTrue(controller.getPanels().contains(panelSuppliers.get(5)));
    }

    @Test
    public void testCreatePlayer(){
        Player expectedPlayer = new Player(player_names[1],hp[1],atk[1],def[1],evd[1]);
        System.out.println(testPlayers.size());
        assertEquals(expectedPlayer, testPlayers.get(1));
        assertEquals(panelSuppliers.get(1),testPlayers.get(1).getCurrentPanel());
        assertTrue(panelSuppliers.get(1).getPlayers().contains(testPlayers.get(1)));
        assertTrue(controller.getPlayers().contains(testPlayers.get(1)));
    }

    @Test
    public void testGetPlayers(){
        List<Player> expectedPlayers = List.of(testPlayers.get(0), testPlayers.get(1), testPlayers.get(2));
        assertEquals(expectedPlayers, controller.getPlayers());
        assertTrue(newController.getPlayers().isEmpty());
    }

    @Test
    public void testCreateBoss(){
        BossUnit expectedBoss = new BossUnit(boss_names[0],hp[0],atk[0],def[0],evd[0]);
        assertEquals(expectedBoss, testBosses.get(0));
    }

    @Test
    public void testCreateWild(){
        WildUnit expectedWild = new WildUnit(wild_names[2],hp[2],atk[2],def[2],evd[2]);
        assertEquals(expectedWild, testWildUnits.get(2));
    }

    @Test
    public void testTurnAndChapter(){
        assertEquals(testPlayers.get(0), controller.getTurnOwner());
        assertEquals(1, controller.getChapter());
        controller.endTurn();
        assertEquals(testPlayers.get(1), controller.getTurnOwner());
        assertEquals(1, controller.getChapter());
        controller.endTurn();
        assertEquals(testPlayers.get(2),controller.getTurnOwner());
        controller.endTurn();
        assertEquals(testPlayers.get(0), controller.getTurnOwner());
        controller.endTurn();
        assertEquals(2, controller.getChapter());
    }

    @Test
    public void testHandlerProperties(){
        controller.playerWins(testPlayers.get(0));
        assertEquals(testPlayers.get(0), controller.getWinner());
        assertTrue(controller.isGameWon());
        controller.playerReachedNorma4(testPlayers.get(0));
        assertTrue(controller.isNorma4());
        controller.bossDefeated(testBosses.get(0));
        assertTrue(controller.isBossDefeated());
    }

    @Test
    public void testSetNextPanels(){
        assertTrue(panelSuppliers.get(0).getNextPanels().isEmpty());
        final var expectedPanel1 = panelSuppliers.get(1);
        final var expectedPanel2 = panelSuppliers.get(2);

        panelSuppliers.get(0).addNextPanel(panelSuppliers.get(0));
        assertTrue(panelSuppliers.get(0).getNextPanels().isEmpty());

        panelSuppliers.get(0).addNextPanel(expectedPanel1);
        assertEquals(1, panelSuppliers.get(0).getNextPanels().size());
        assertTrue(panelSuppliers.get(0).getNextPanels().contains(expectedPanel1));

        panelSuppliers.get(0).addNextPanel(expectedPanel2);
        assertEquals(2, panelSuppliers.get(0).getNextPanels().size());

        panelSuppliers.get(0).addNextPanel(expectedPanel2);
        assertEquals(2, panelSuppliers.get(0).getNextPanels().size());

        assertEquals(Set.of(expectedPanel1, expectedPanel2), panelSuppliers.get(0).getNextPanels());
    }

    //region: move player tests
    @Test
    public void testMeetPlayer() {
        controller.setNextPanel(panelSuppliers.get(0), panelSuppliers.get(2));
        assertEquals(1, panelSuppliers.get(0).getPlayers().size());
        assertTrue(panelSuppliers.get(0).getPlayers().contains(testPlayers.get(0)));
        assertEquals(1, panelSuppliers.get(2).getPlayers().size());
        assertTrue(panelSuppliers.get(2).getPlayers().contains(testPlayers.get(2)));
        System.out.println(1);
        controller.movePlayer();
        System.out.println(2);
        assertEquals(0, panelSuppliers.get(0).getPlayers().size());
        assertEquals(2, panelSuppliers.get(2).getPlayers().size());
        assertTrue(panelSuppliers.get(2).getPlayers().contains(testPlayers.get(0)));
        assertTrue(panelSuppliers.get(2).getPlayers().contains(testPlayers.get(2)));
    }

    @Test
    public void testPlayerHome() {
        IPanel panel1 = panelSuppliers.get(0);
        HomePanel homePanel = (HomePanel) panelSuppliers.get(4);
        IPanel panel2 = panelSuppliers.get(1);
        Player player = testPlayers.get(0);
        controller.setNextPanel(panel1, homePanel);
        controller.setNextPanel(homePanel, panel2);
        controller.setPlayerHome(player, homePanel);
        controller.movePlayer();
        assertTrue(homePanel.getPlayers().contains(player), "Player didn't stop at it's home panel");
    }

    @Test
    public void testMultipleNextPanels() {
        IPanel panel1 = panelSuppliers.get(0);
        IPanel panel2 = panelSuppliers.get(2);
        IPanel panel3 = panelSuppliers.get(3);
        IPanel panel4 = panelSuppliers.get(4);
        controller.setNextPanel(panel1, panel2);
        controller.setNextPanel(panel2, panel3);
        controller.setNextPanel(panel2, panel4);
        Player player = testPlayers.get(2);
        controller.movePlayer();
        assertTrue(panel2.getPlayers().contains(player), "Player didn't stop at split");
    }


}
