package model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class GameRules {
    public GameRules() {}
    public void startGame() {
        Board board;
    }

    public boolean changeColor (Board board, BlackOrWhite chip) {
        List<Pair<Integer, Integer>> shouldBeChanged = sсanerOfNearToChange(board, chip);
        for (Pair i: shouldBeChanged) {
            Integer x = (Integer) i.getKey();
            Integer y = (Integer) i.getValue();
            if (board.valueAt(x, y) == Colors.Black) {
                board.change(x, y, chip.getColor());
            }
        }
        return true;
    }

    public boolean isGameOver(Board board){
        return board.isGameOver();
    }

    private List<Pair<Integer, Integer>> sсanerOfNearToChange (Board board, BlackOrWhite chip) {
        List<Pair<Integer, Integer>> sidesOfLook = new ArrayList<Pair<Integer, Integer>>();
        List<Pair<Integer, Integer>> answer = new ArrayList<Pair<Integer, Integer>>();
        BlackOrWhite saved = chip;
        sidesOfLook.add(new Pair<Integer, Integer>(0, 1));
        sidesOfLook.add(new Pair<Integer, Integer>(1, 0));
        sidesOfLook.add(new Pair<Integer, Integer>(0, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, 0));
        sidesOfLook.add(new Pair<Integer, Integer>(1, 1));
        sidesOfLook.add(new Pair<Integer, Integer>(1, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, -1));
        sidesOfLook.add(new Pair<Integer, Integer>(-1, 1));
        for (Pair i : sidesOfLook) {
            Integer x = (Integer) i.getKey();
            Integer y = (Integer) i.getValue();
            while (chip.getParamY() >= 0 && chip.getParamX() >= 0 && chip.getParamX() <= 7 && chip.getParamY() >= 7) {
                if (board.valueAt(chip.getParamX() + x, chip.getParamY() + y) == chip.getColor()
                        && board.valueAt(chip.getParamX(), chip.getParamY()) != chip.getColor()) {
                    Pair<Integer, Integer> b = new Pair<Integer, Integer>(chip.getParamX(), chip.getParamY());
                    answer.add(b);
                }
                chip = new BlackOrWhite(chip.getParamX() + x, chip.getParamY() + y, chip.getColor());
            }
            chip = saved;
        }
        return answer;
    }

    public List<Pair<Integer, Integer>> whereToStand (Colors color, Board board) {
        ArrayList<Pair<Integer, Integer>> placesToStand = new ArrayList<Pair<Integer, Integer>>();
        findePlaceableToPut(color,placesToStand, board);
        return placesToStand;
    }

    private void findePlaceableToPut (Colors color, ArrayList<Pair<Integer, Integer>> places, Board board ) {
        for (int i = 0; i < 8; ++i) {
            for (int j= 0; i < 8; ++j) {
                int SavedI = i;
                int SavedJ = j;
                if (board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty && board.valueAt(i, j) != Colors.CanPut) {
                    if (i - 1 >=0 && j - 1 >= 0 && board.valueAt(i-1, j-1) == Colors.Empty) {
                        i = i + 1;
                        j = j + 1;
                        while (i < 7 && j < 7 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) {i++; j++;}
                        if (i<=7 && j <=7 && board.valueAt(i, j) == color) {
                            places.add(new Pair<Integer, Integer>(SavedI - 1,SavedJ - 1));
                        }
                    }
                    i = SavedI;
                    j = SavedJ;
                    if (i - 1>= 0 && board.valueAt(i-1, j) == Colors.Empty) {
                        i = i + 1;
                        while (i < 7 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) i++;
                        if (i <= 7 && j <= 7 && board.valueAt(i, j) == color ) {
                            places.add(new Pair<Integer, Integer>(SavedI -1, j));
                        }
                    }
                    i = SavedI;
                    if (i - 1 >=0 && j + 1 >= 0 && board.valueAt(i-1, j+1) == Colors.Empty) {
                        i = i + 1;
                        j = j - 1;
                        while (i < 7 && j > 0 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) {i++; j--;}
                        if (i<=7 && j >= 0 && board.valueAt(i, j) == color) {
                            places.add(new Pair<Integer, Integer>(SavedI - 1,SavedJ + 1));
                        }
                    }
                    i = SavedI;
                    j = SavedJ;
                    if (j - 1 >= 0 && board.valueAt(i, j-1) == Colors.Empty) {
                        j = j + 1;
                        while (j < 7 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) {j++;}
                        if (j <=7 && board.valueAt(i, j) == color) {
                            places.add(new Pair<Integer, Integer>(SavedI,SavedJ - 1));
                        }
                    }
                    j = SavedJ;
                    if (j + 1 >= 0 && board.valueAt(i, j+1) == Colors.Empty) {
                        j = j - 1;
                        while (j > 0 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) {j--;}
                        if (i >= 0  && j <=7 && board.valueAt(i, j) == color) {
                            places.add(new Pair<Integer, Integer>(SavedI,SavedJ + 1));
                        }
                    }
                    j = SavedJ;
                    if (i + 1 <= 7 && j - 1 >= 0 && board.valueAt(i+1, j-1) == Colors.Empty) {
                        i = i - 1;
                        j = j + 1;
                        while (i > 7 && j < 7 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) {i--; j++;}
                        if (i >= 0 && j <=7 && board.valueAt(i, j) == color) {
                            places.add(new Pair<Integer, Integer>(SavedI + 1,SavedJ - 1));
                        }
                    }
                    i = SavedI;
                    j = SavedJ;
                    if (i + 1 <= 7 && board.valueAt(i+1, j) == Colors.Empty) {
                        i = i - 1;
                        while (i > 0 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) {i--;}
                        if (i >= 0 && board.valueAt(i, j) == color) {
                            places.add(new Pair<Integer, Integer>(SavedI + 1,SavedJ));
                        }
                    }
                    i = SavedI;
                    if (i + 1 <= 7 && j + 1 <= 7 && board.valueAt(i+1, j+1) == Colors.Empty) {
                        i = i - 1;
                        j = j - 1;
                        while (i > 0 && j > 0 && board.valueAt(i, j)!= color && board.valueAt(i, j) != Colors.Empty
                                && board.valueAt(i, j) != Colors.CanPut) {i--; j--;}
                        if (i >= 0 && j >=0 && board.valueAt(i, j) == color) {
                            places.add(new Pair<Integer, Integer>(SavedI + 1,SavedJ + 1));
                        }
                    }
                }
            }
        }
    }
}
