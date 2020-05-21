package model;

import javafx.util.Pair;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameRulesTest {
    Board board = new Board();

    @Test
    public void putTheChip() {
        List<Pair<Integer, Integer>> a = GameRules.whereToStand(Colors.Black, board);
        assertTrue(GameRules.putTheChip(board, 3, 2, Colors.Black, a));
        assertFalse(GameRules.putTheChip(board, 4, 2, Colors.Black, a));
    }

    @Test
    public void changeColor() {
    }

    @Test
    public void whereToStandPut() {
    }

    @Test
    public void deleteCanPut() {
    }

    @Test
    public void whereToStand() {
    }

}