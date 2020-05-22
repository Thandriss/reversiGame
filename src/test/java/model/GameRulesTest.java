package model;

import javafx.util.Pair;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GameRulesTest {
    Board board = new Board();

    @Test
    public void putTheChip() {
        List<Pair<Integer, Integer>> a = GameRules.whereToStand(Colors.Black, board);
        assertTrue(GameRules.putTheChip(board, 3, 2, Colors.Black, a));
        assertFalse(GameRules.putTheChip(board, 4, 2, Colors.Black, a));
        a = GameRules.whereToStand(Colors.White, board);
        assertTrue(GameRules.putTheChip(board, 4, 2, Colors.White, a));
        assertFalse(GameRules.putTheChip(board, 5, 4, Colors.White, a));
        List<Pair<Integer, Integer>> finalA = a;
        assertThrows(IllegalArgumentException.class, () -> GameRules.putTheChip(board, 7, 8, Colors.White, finalA));
    }

    @Test
    public void changeColor() {
        List<Pair<Integer, Integer>> a = GameRules.whereToStand(Colors.Black, board);
        GameRules.putTheChip(board, 3, 2, Colors.Black, a);
        GameRules.changeColor(board, 3, 2, Colors.Black);
        ArrayList<Pair<Integer, Integer>> b = new ArrayList<>();
        b.add(new Pair<>(3, 3));
        CheckTheBoard(board, b, Colors.Black);
        b.clear();
        a = GameRules.whereToStand(Colors.White, board);
        GameRules.putTheChip(board, 2, 2, Colors.White, a);
        GameRules.changeColor(board, 2, 2, Colors.White);
        b.add(new Pair<>(3, 3));
        CheckTheBoard(board, b, Colors.White);
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

    private boolean CheckTheBoard (Board board, ArrayList<Pair<Integer, Integer>> coordForcheck, Colors color) {
        for (Pair<Integer, Integer> coord: coordForcheck) {
            if (board.valueAt(coord.getKey(), coord.getValue()) != color) throw new AssertionError();
        }
        return true;
    }
}