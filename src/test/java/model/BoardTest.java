package model;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board = new Board();

    @Test
    public void putTheChip() {
        List<Pair<Integer, Integer>> a = board.whereToStand(Colors.Black);
        assertTrue(board.putTheChip(3, 2, Colors.Black, a));
        board.changeColor(3, 2, Colors.Black);
        assertFalse(board.putTheChip(4, 2, Colors.Black, a));
        a = board.whereToStand(Colors.White);
        assertTrue(board.putTheChip(4, 2, Colors.White, a));
        board.changeColor(4, 2, Colors.White);
        assertFalse(board.putTheChip(5, 4, Colors.White, a));
        a = board.whereToStand(Colors.Black);
        assertTrue(board.putTheChip(5, 3, Colors.Black, a));
        List<Pair<Integer, Integer>> finAl = a;
        assertThrows(IllegalArgumentException.class, () -> board.putTheChip(7, 8, Colors.White, finAl));
    }

    @Test
    public void changeColor() {
        List<Pair<Integer, Integer>> a = board.whereToStand(Colors.Black);
        board.putTheChip(3, 2, Colors.Black, a);
        board.changeColor(3, 2, Colors.Black);
        ArrayList<Pair<Integer, Integer>> b = new ArrayList<>();
        b.add(new Pair<>(3, 3));
        CheckTheBoard(board, b, Colors.Black, 4);
        b.clear();
        a = board.whereToStand(Colors.White);
        board.putTheChip(2, 2, Colors.White, a);
        board.changeColor(2, 2, Colors.White);
        b.add(new Pair<>(3, 3));
        CheckTheBoard(board, b, Colors.White, 3);
    }

    @Test
    public void whereToStandPut() {
        List<Pair<Integer, Integer>> a = board.whereToStand(Colors.Black);
        board.whereToStandPut(a);
        CheckStandChip(board, a);
        board.putTheChip(3, 2, Colors.Black, a);
        board.changeColor(3, 2, Colors.Black);
        board.deleteCanPut(a);
        a = board.whereToStand(Colors.White);
        board.whereToStandPut(a);
        CheckStandChip(board, a);
        board.putTheChip(4, 2, Colors.White, a);
        board.changeColor(4, 2, Colors.White);
        board.deleteCanPut(a);
        a = board.whereToStand(Colors.Black);
        board.whereToStandPut(a);
        CheckStandChip(board, a);
        board.putTheChip(5, 1, Colors.Black, a);
        board.changeColor(5, 1, Colors.Black);
        board.deleteCanPut(a);
        a = board.whereToStand(Colors.White);
        board.whereToStandPut(a);
        CheckStandChip(board, a);
        board.putTheChip(2, 5, Colors.White, a);
        board.changeColor(2, 5, Colors.White);
        board.deleteCanPut(a);
    }

    @Test
    public void deleteCanPut() {
        List<Pair<Integer, Integer>> a = board.whereToStand(Colors.Black);
        board.whereToStandPut(a);
        board.putTheChip(4, 5, Colors.Black, a);
        board.changeColor(4, 5, Colors.Black);
        board.deleteCanPut(a);
        CheckDeleteChip(board, a);
        a = board.whereToStand(Colors.White);
        board.whereToStandPut(a);
        board.putTheChip(5, 3, Colors.White, a);
        board.changeColor(5, 3, Colors.White);
        board.deleteCanPut(a);
        CheckDeleteChip(board, a);
        a = board.whereToStand(Colors.Black);
        board.whereToStandPut(a);
        board.putTheChip(5, 2, Colors.Black, a);
        board.changeColor(5, 2, Colors.Black);
        board.deleteCanPut(a);
        CheckDeleteChip(board, a);
        a = board.whereToStand(Colors.White);
        board.whereToStandPut(a);
        board.putTheChip(3, 5, Colors.White, a);
        board.changeColor(3, 5, Colors.White);
        board.deleteCanPut(a);
        CheckDeleteChip(board, a);
        a = board.whereToStand(Colors.Black);
        board.whereToStandPut(a);
        board.putTheChip(2, 5, Colors.Black, a);
        board.changeColor(2, 5, Colors.Black);
        board.deleteCanPut(a);
        CheckDeleteChip(board, a);
    }

    @Test
    public void whereToStand() {
        List<Pair<Integer, Integer>> a = board.whereToStand(Colors.Black);
        List<Pair<Integer, Integer>> b = new ArrayList<Pair<Integer, Integer>>();
        b.add(new Pair<>(3, 2));
        b.add(new Pair<>(2, 3));
        b.add(new Pair<>(5, 4));
        b.add(new Pair<>(4, 5));
        assertArrayEquals(b, a);
        board.whereToStandPut(a);
        board.putTheChip(4, 5, Colors.Black, a);
        board.changeColor(4, 5, Colors.Black);
        board.deleteCanPut(a);
        b.clear();
        a = board.whereToStand(Colors.White);
        b.add(new Pair<>(5, 3));
        b.add(new Pair<>(5, 5));
        b.add(new Pair<>(3, 5));
        assertArrayEquals(b, a);
        b.clear();
        board.whereToStandPut(a);
        board.putTheChip(3, 5, Colors.White, a);
        board.changeColor(3, 5, Colors.White);
        board.deleteCanPut(a);
        a = board.whereToStand(Colors.Black);
        b.add(new Pair<>(2, 2));
        b.add(new Pair<>(2, 3));
        b.add(new Pair<>(2, 4));
        b.add(new Pair<>(2, 5));
        b.add(new Pair<>(2, 6));
        assertArrayEquals(b, a);
        b.clear();
        board.whereToStandPut(a);
        board.putTheChip(2, 2, Colors.Black, a);
        board.changeColor(2, 2, Colors.Black);
        board.deleteCanPut(a);
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