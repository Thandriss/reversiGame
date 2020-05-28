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
        GameRules.changeColor(board, 3, 2, Colors.Black);
        assertFalse(GameRules.putTheChip(board, 4, 2, Colors.Black, a));
        a = GameRules.whereToStand(Colors.White, board);
        assertTrue(GameRules.putTheChip(board, 4, 2, Colors.White, a));
        GameRules.changeColor(board, 4, 2, Colors.White);
        assertFalse(GameRules.putTheChip(board, 5, 4, Colors.White, a));
        a = GameRules.whereToStand(Colors.Black, board);
        assertTrue(GameRules.putTheChip(board, 5, 3, Colors.Black, a));
        List<Pair<Integer, Integer>> finAl = a;
        assertThrows(IllegalArgumentException.class, () -> GameRules.putTheChip(board, 7, 8, Colors.White, finAl));
    }

    @Test
    public void changeColor() {
        List<Pair<Integer, Integer>> a = GameRules.whereToStand(Colors.Black, board);
        GameRules.putTheChip(board, 3, 2, Colors.Black, a);
        GameRules.changeColor(board, 3, 2, Colors.Black);
        ArrayList<Pair<Integer, Integer>> b = new ArrayList<>();
        b.add(new Pair<>(3, 3));
        CheckTheBoard(board, b, Colors.Black, 4);
        b.clear();
        a = GameRules.whereToStand(Colors.White, board);
        GameRules.putTheChip(board, 2, 2, Colors.White, a);
        GameRules.changeColor(board, 2, 2, Colors.White);
        b.add(new Pair<>(3, 3));
        CheckTheBoard(board, b, Colors.White, 3);
    }

    @Test
    public void whereToStandPut() {
        List<Pair<Integer, Integer>> a = GameRules.whereToStand(Colors.Black, board);
        GameRules.whereToStandPut(board, a);
        CheckStandChip(board, a);
        GameRules.putTheChip(board, 3, 2, Colors.Black, a);
        GameRules.changeColor(board, 3, 2, Colors.Black);
        GameRules.deleteCanPut(board, a);
        a = GameRules.whereToStand(Colors.White, board);
        GameRules.whereToStandPut(board, a);
        CheckStandChip(board, a);
        GameRules.putTheChip(board, 4, 2, Colors.White, a);
        GameRules.changeColor(board, 4, 2, Colors.White);
        GameRules.deleteCanPut(board, a);
        a = GameRules.whereToStand(Colors.Black, board);
        GameRules.whereToStandPut(board, a);
        CheckStandChip(board, a);
        GameRules.putTheChip(board, 5, 1, Colors.Black, a);
        GameRules.changeColor(board, 5, 1, Colors.Black);
        GameRules.deleteCanPut(board, a);
        a = GameRules.whereToStand(Colors.White, board);
        GameRules.whereToStandPut(board, a);
        CheckStandChip(board, a);
        GameRules.putTheChip(board, 2, 5, Colors.White, a);
        GameRules.changeColor(board, 2, 5, Colors.White);
        GameRules.deleteCanPut(board, a);
    }

    @Test
    public void deleteCanPut() {
        List<Pair<Integer, Integer>> a = GameRules.whereToStand(Colors.Black, board);
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 4, 5, Colors.Black, a);
        GameRules.changeColor(board, 4, 5, Colors.Black);
        GameRules.deleteCanPut(board, a);
        CheckDeleteChip(board, a);
        a = GameRules.whereToStand(Colors.White, board);
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 5, 3, Colors.White, a);
        GameRules.changeColor(board, 5, 3, Colors.White);
        GameRules.deleteCanPut(board, a);
        CheckDeleteChip(board, a);
        a = GameRules.whereToStand(Colors.Black, board);
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 5, 2, Colors.Black, a);
        GameRules.changeColor(board, 5, 2, Colors.Black);
        GameRules.deleteCanPut(board, a);
        CheckDeleteChip(board, a);
        a = GameRules.whereToStand(Colors.White, board);
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 3, 5, Colors.White, a);
        GameRules.changeColor(board, 3, 5, Colors.White);
        GameRules.deleteCanPut(board, a);
        CheckDeleteChip(board, a);
        a = GameRules.whereToStand(Colors.Black, board);
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 2, 5, Colors.Black, a);
        GameRules.changeColor(board, 2, 5, Colors.Black);
        GameRules.deleteCanPut(board, a);
        CheckDeleteChip(board, a);
    }

    @Test
    public void whereToStand() {
        List<Pair<Integer, Integer>> a = GameRules.whereToStand(Colors.Black, board);
        List<Pair<Integer, Integer>> b = new ArrayList<Pair<Integer, Integer>>();
        b.add(new Pair<>(3, 2));
        b.add(new Pair<>(2, 3));
        b.add(new Pair<>(5, 4));
        b.add(new Pair<>(4, 5));
        assertArrayEquals(b, a);
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 4, 5, Colors.Black, a);
        GameRules.changeColor(board, 4, 5, Colors.Black);
        GameRules.deleteCanPut(board, a);
        b.clear();
        a = GameRules.whereToStand(Colors.White, board);
        b.add(new Pair<>(5, 3));
        b.add(new Pair<>(5, 5));
        b.add(new Pair<>(3, 5));
        assertArrayEquals(b, a);
        b.clear();
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 3, 5, Colors.White, a);
        GameRules.changeColor(board, 3, 5, Colors.White);
        GameRules.deleteCanPut(board, a);
        a = GameRules.whereToStand(Colors.Black, board);
        b.add(new Pair<>(2, 2));
        b.add(new Pair<>(2, 3));
        b.add(new Pair<>(2, 4));
        b.add(new Pair<>(2, 5));
        b.add(new Pair<>(2, 6));
        assertArrayEquals(b, a);
        b.clear();
        GameRules.whereToStandPut(board, a);
        GameRules.putTheChip(board, 2, 2, Colors.Black, a);
        GameRules.changeColor(board, 2, 2, Colors.Black);
        GameRules.deleteCanPut(board, a);

    }

    private boolean CheckTheBoard (Board board, ArrayList<Pair<Integer, Integer>> coordForcheck, Colors color, int count) {
        boolean flagOfTruth;
        if (color == Colors.Black) {
            flagOfTruth = board.getCountBlack() == count;
        } else if (color == Colors.White) {
            flagOfTruth = board.getCountWhite() == count;
        } else throw new AssertionError();
        if (flagOfTruth) {
            for (Pair<Integer, Integer> coord : coordForcheck) {
                if (board.valueAt(coord.getKey(), coord.getValue()) != color) throw new AssertionError();
            }
        }
        return true;
    }

    private boolean CheckStandChip(Board board, List<Pair<Integer, Integer>> coordForcheck) {
        for (Pair<Integer, Integer> coord: coordForcheck) {
            if (board.valueAt(coord.getKey(), coord.getValue()) == Colors.CanPut) {
                continue;
            } else throw new AssertionError();
        }
        return true;
    }

    private boolean CheckDeleteChip(Board board, List<Pair<Integer, Integer>> coordForcheck) {
        for (Pair<Integer, Integer> coord: coordForcheck) {
            if (board.valueAt(coord.getKey(), coord.getValue()) != Colors.CanPut) {
                continue;
            } else throw new AssertionError();
        }
        return true;
    }

    private boolean assertArrayEquals(List<Pair<Integer, Integer>> expected, List<Pair<Integer, Integer>> actual) {
        if (expected.size() == actual.size()) {
            for (int i = 0; i != expected.size(); i++) {
                if (actual.contains(expected.get(i))) {
                    continue;
                } else throw new AssertionError();
            }
        } else throw new AssertionError();
        return true;
    }
}