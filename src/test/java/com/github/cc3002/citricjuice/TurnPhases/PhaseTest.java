package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.unit.FightDecision;
import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PhaseTest {
    Turn turn;
    Player player;
    int i = 0;
    FirstPhase first;
    CardPhase card;
    LandAtPanelPhase landAtPanel;
    MovePhase move;
    RecoveryPhase recovery;
    StarsPhase stars;
    FightPhase fight;


    @BeforeEach
    public void setUp() {
        player = new Player("suguri", 5, 2, -1, 0);
        turn = new Turn(player, i);
        first = new FirstPhase();
        first.setTurn(turn);
        card = new CardPhase();
        card.setTurn(turn);
        landAtPanel = new LandAtPanelPhase();
        landAtPanel.setTurn(turn);
        move = new MovePhase(4);
        move.setTurn(turn);
        recovery = new RecoveryPhase();
        recovery.setTurn(turn);
        stars = new StarsPhase();
        stars.setTurn(turn);
        fight = new FightPhase();
        fight.setTurn(turn);
    }

    @Test
    public void testFirstPhaseConstructor() {
        assertEquals(first, first);
        assertNotEquals(first, new Object());
        assertFalse(first.equals(null));
        assertNotSame(new FirstPhase(), first);
        assertEquals(new FirstPhase(), first);
    }

    @Test
    public void testCardPhaseConstructor() {
        assertEquals(card, card);
        assertNotEquals(card, new Object());
        assertFalse(card.equals(null));
        assertNotSame(new CardPhase(), card);
        assertEquals(new CardPhase(), card);
    }

    @Test
    public void testLandAtPanelPhaseConstructor() {
        assertEquals(landAtPanel, landAtPanel);
        assertNotEquals(landAtPanel, new Object());
        assertFalse(landAtPanel.equals(null));
        assertNotSame(new LandAtPanelPhase(), landAtPanel);
        assertEquals(new LandAtPanelPhase(), landAtPanel);
    }

    @Test
    public void testMovePhaseConstructor() {
        assertEquals(move, move);
        assertNotEquals(move, new Object());
        assertFalse(move.equals(null));
        assertNotSame(new MovePhase(4), move);
        assertEquals(new MovePhase(4), move);
    }

    @Test
    public void testRecoveryPhaseConstructor() {
        assertEquals(recovery, recovery);
        assertNotEquals(recovery, new Object());
        assertFalse(recovery.equals(null));
        assertNotSame(new RecoveryPhase(), recovery);
        assertEquals(new RecoveryPhase(), recovery);
    }

    @Test
    public void testStarsPhaseConstructor() {
        assertEquals(stars, stars);
        assertNotEquals(stars, new Object());
        assertFalse(stars.equals(null));
        assertNotSame(new StarsPhase(), stars);
        assertEquals(new StarsPhase(), stars);
    }

    @Test
    public void testFightPhaseConstructor(){
        assertEquals(fight, fight);
        assertNotEquals(fight, new Object());
        assertFalse(fight.equals(null));
        assertNotSame(new FightPhase(), fight);
        assertEquals(new FightPhase(), fight);
    }

    @Test
    public void testSetTurn(){
        assertEquals(turn, first.getTurn());
        assertEquals(turn, card.getTurn());
        assertEquals(turn, landAtPanel.getTurn());
        assertEquals(turn, move.getTurn());
        assertEquals(turn, recovery.getTurn());
        assertEquals(turn, stars.getTurn());
        assertEquals(turn, fight.getTurn());
    }

    @Test
    public void testFirstPhaseAction(){
        player.setCurrentHP(3);
        first.action();
        assertEquals(new StarsPhase(), turn.getPhase());
        player.setCurrentHP(0);
        first.action();
        assertEquals(new RecoveryPhase(), turn.getPhase());
    }

    @RepeatedTest(100)
    public void testCardPhaseAction(){
        long random = new Random().nextLong();
        final var testRandom = new Random(random);
        player.setSeed(random);
        final int roll = testRandom.nextInt(6) + 1;
        card.action();
        assertEquals(new MovePhase(roll), turn.getPhase());
    }

    @Test
    public void testLandAtPanelPhaseAction(){
        IPanel panel= new Panel(1);
        player.changePanel(panel);
        landAtPanel.action();
        //card test
        //the panel being activated is tested specifically in the panels
        //and I don't know if there is an assert to check that a specific method was called
        //and the turn ends, so the next phase doesn't exist
    }

    @Test
    public void testMovePhaseAction(){
        IPanel panel= new Panel(0);
        IPanel panel1 = new Panel(1);
        panel.addNextPanel(panel1);
        panel1.addNextPanel(panel);
        player.changePanel(panel);
        move.action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel, player.getCurrentPanel());

        move = new MovePhase(1);
        turn.setPhase(move);
        IPanel panel2= new Panel(2);
        IPanel panel3 = new Panel(3);
        panel.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        player.setPanelDecision(panel2);
        move.action();
        assertEquals(new MovePhase(0), turn.getPhase());
        assertEquals(panel2, player.getCurrentPanel());
        turn.getPhase().action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel2, player.getCurrentPanel());

        move= new MovePhase(2);
        turn.setPhase(move);
        IPanel panel4 = new Panel(4);
        panel3.addNextPanel(panel4);
        Player player1 = new Player("kai", 2, 1, 1, 0);
        panel3.addPlayer(player1);
        player.setFightDecision(FightDecision.IGNORE);
        move.action();
        assertEquals(new MovePhase(1), turn.getPhase());
        assertEquals(panel3, player.getCurrentPanel());
        turn.getPhase().action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel4, player.getCurrentPanel());

        move= new MovePhase(2);
        turn.setPhase(move);
        player.changePanel(panel2);
        player.setFightDecision(FightDecision.ENGAGE);
        move.action();
        assertEquals(new FightPhase(), turn.getPhase());
        assertEquals(panel3, player.getCurrentPanel());

        move = new MovePhase(2);
        turn.setPhase(move);
        IPanel panel5 = new Panel(5);
        HomePanel home = new HomePanel(6);
        player.setHome(home);
        player.setHomeDecision(true);
        IPanel panel6 = new Panel(6);
        panel5.addNextPanel(home);
        home.addNextPanel(panel6);
        panel5.addPlayer(player);
        move.action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(home, player.getCurrentPanel());

        panel5.addPlayer(player);
        player.setHomeDecision(false);
        move.action();
        assertEquals(home, player.getCurrentPanel());
        assertEquals(new MovePhase(1), turn.getPhase());
        turn.getPhase().action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel6, player.getCurrentPanel());
    }

    @Test
    public void testRecoveryPhaseAction(){
        player.setRequiredRoll(4);
        recovery.action();
        assertTrue((turn.getPhase().equals(new StarsPhase()) && player.getCurrentHP()==player.getMaxHP()) || player.getRequiredRoll()==3);
    }

    @Test
    public void testStarsPhaseAction(){
        assertEquals(0, player.getStars());
        stars.action();
        assertEquals(turn.getChapter()/5 + 1, player.getStars());
        assertEquals(new CardPhase(), turn.getPhase());
    }

    @RepeatedTest(100)
    public void testFightPhaseAction(){
        assertFalse(player.isDown());
        IPanel panel = new Panel(1);
        Player other=new Player("marc", 5, 1, 0, 0);
        panel.addPlayer(player);
        panel.addPlayer(other);
        fight.action();
        assertTrue(player.isDown() || turn.getPhase().equals(new LandAtPanelPhase()));
    }
}