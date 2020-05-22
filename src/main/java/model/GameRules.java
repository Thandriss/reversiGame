package model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class GameRules {

    private static Board board = new Board();

    public static Board startGame() {
        return board;
    }

    public static boolean putTheChip (Board board, Integer x, Integer y, Colors color, List<Pair<Integer, Integer>> canBePut) {
        Pair<Integer, Integer> checking = new Pair<Integer, Integer>(x, y);
        if (canBePut.isEmpty() || !canBePut.contains(checking)) return false;
        board.putChip(x, y, color);
        return true;
    }

    public static void changeColor (Board board, Integer x, Integer y, Colors color) {
        List<Pair<Integer, Integer>> shouldBeChanged = sсannerOfNearToChange(board, x, y, color);
        for (Pair i: shouldBeChanged) {
            Integer newX = (Integer) i.getKey();
            Integer newY = (Integer) i.getValue();
            if (board.valueAt(newX, newY) == Colors.Black || board.valueAt(newX, newY) == Colors.White) {
                board.change(newX, newY, color);
            }
        }
    }

    public static void whereToStandPut(Board board, List<Pair<Integer, Integer>> canBePut) {
        for (Pair<Integer, Integer> i: canBePut) {
            board.putChip(i.getKey(), i.getValue(), Colors.CanPut);
        }
    }

    public static void deleteCanPut(Board board, List<Pair<Integer, Integer>> canBePut) {
        for (Pair<Integer, Integer> i: canBePut) {
            if (board.valueAt(i.getKey(), i.getValue()) == Colors.CanPut) {
                board.putChip(i.getKey(), i.getValue(), Colors.Empty);
            }
        }
    }

    private static List<Pair<Integer, Integer>> sсannerOfNearToChange (Board board, Integer x, Integer y, Colors color) {
        List<Pair<Integer, Integer>> sidesOfLook = new ArrayList<Pair<Integer, Integer>>();
        List<Pair<Integer, Integer>> answer = new ArrayList<Pair<Integer, Integer>>();
        List<Pair<Integer, Integer>> between = new ArrayList<Pair<Integer, Integer>>();
        Integer savedX = x;
        Integer savedY = y;
        Colors saved = color;
        sidesOfLook.add(new Pair<Integer, Integer>(0, 1));
        sidesOfLook.add(new Pair<Integer, Integer>(1, 0));
        sidesOfLook.add(new Pair<Integer, Integer>(0, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, 0));
        sidesOfLook.add(new Pair<Integer, Integer>(1, 1));
        sidesOfLook.add(new Pair<Integer, Integer>(1, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, 1));
        for (Pair i : sidesOfLook) {
            Integer naviX = (Integer) i.getKey();
            Integer naviY = (Integer) i.getValue();
            while (y >= 0 && x >= 0 && x <= 7 && y <= 7 ) {
                if (board.valueAt(x + naviX, y + naviY) != color
                        && board.valueAt(x + naviX, y + naviY) != Colors.Empty
                && board.valueAt(x + naviX, y + naviY) != Colors.CanPut
                        && y + 2*naviY >= 0 && x + 2*naviX >= 0 && x + 2*naviX <= 7
                        && y + 2*naviY <= 7) {
                    Pair<Integer, Integer> b = new Pair<Integer, Integer>(x + naviX, y + naviY);
                    between.add(b);
                } else if (board.valueAt(x + naviX, y + naviY) == color
                        && y + naviY >= 0 && x + naviX >= 0 && x + naviX <= 7 && y + naviY <= 7) {
                    answer.addAll(between);
                    between.clear();
                    break;
                } else {
                    between.clear();
                    break;
                }
                x = x + naviX;
                y = y + naviY;
                saved = color;
            }
            x = savedX;
            y = savedY;
            color = saved;
        }
        return answer;
    }

    public static List<Pair<Integer, Integer>> whereToStand(Colors color, Board board) {
        ArrayList<Pair<Integer, Integer>> placesToStand = new ArrayList<Pair<Integer, Integer>>();
        findPlaceAbleToPut(color,placesToStand, board);
        return placesToStand;
    }

    private static void findPlaceAbleToPut(Colors color, ArrayList<Pair<Integer, Integer>> places, Board board) {
        List<Pair<Integer, Integer>> sidesOfLook = new ArrayList<Pair<Integer, Integer>>();
        sidesOfLook.add(new Pair<Integer, Integer>(0, 1));
        sidesOfLook.add(new Pair<Integer, Integer>(1, 0));
        sidesOfLook.add(new Pair<Integer, Integer>(0, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, 0));
        sidesOfLook.add(new Pair<Integer, Integer>(1, 1));
        sidesOfLook.add(new Pair<Integer, Integer>(1, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, 1));
        for (int i = 0; i < 8; ++i) {
            for (int j= 0; j < 8; ++j) {
                int savedI = i;
                int savedJ = j;
                for (Pair<Integer, Integer> navi: sidesOfLook) {
                    if (board.valueAt(i, j) != color && board.valueAt(i, j) != Colors.Empty && board.valueAt(i, j) != Colors.CanPut) {
                        if (i - navi.getKey() >= 0 && j - navi.getValue() >= 0 && i - navi.getKey() <= 7 && j - navi.getValue() <= 7
                                && board.valueAt(i - navi.getKey(), j - navi.getValue()) == Colors.Empty) {
                            i = i + navi.getKey();
                            j = j + navi.getValue();
                            while (i < 7 && j < 7 && i > 0 && j > 0 && board.valueAt(i, j) != color && board.valueAt(i, j) != Colors.Empty
                                    && board.valueAt(i, j) != Colors.CanPut) {
                                i += navi.getKey();
                                j += navi.getValue();
                            }
                            if (i <= 7 && j <= 7 && board.valueAt(i, j) == color) {
                                places.add(new Pair<Integer, Integer>(savedI - navi.getKey(), savedJ - navi.getValue()));
                            }
                        }
                    }
                    i = savedI;
                    j = savedJ;
                }
            }
        }
    }
}
