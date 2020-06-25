package com.github.cc3002.citricjuice.TurnPhases;

import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {
    Turn turn;
    Player player = new Player("suguri", 5, 2, -1, 0);
    int i=0;

    @BeforeEach
    public void setUp(){
        turn = new Turn(player, i);
    }

    @Test
    public void testTurnConstructor(){
        assertEquals(turn, turn);
        assertNotEquals(turn, new Object());
        assertFalse(turn.equals(null));
        assertNotSame(new Turn(player, i), turn);
        assertEquals(new Turn(player, i), turn);//uwu
        FirstPhase expected = new FirstPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
    }

    @Test
    public void testPhase(){
        AbstractPhase expected;
        turn.setPhase(new FirstPhase());
        expected = new FirstPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new RecoveryPhase());
        expected = new RecoveryPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new StarsPhase());
        expected = new StarsPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new CardPhase());
        expected = new CardPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new MovePhase(4));
        expected = new MovePhase(4);
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new LandAtPanelPhase());
        expected = new LandAtPanelPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
    }

    @Test
    public void testPlayer(){
        assertEquals(player, turn.getPlayer());
    }

    @Test
    public void getChapter(){
        assertEquals(1, turn.getChapter());
        Turn turn1 = new Turn(new Player("sugurint", 4, 1,1,0),3);
        assertEquals(1, turn1.getChapter());
        Turn turn2 = new Turn(new Player("kai", 4, 1,1,0),5);
        assertEquals(2, turn2.getChapter());

    }

}