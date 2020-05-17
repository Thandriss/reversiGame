package model;

import java.util.Arrays;

public class Board {
    private Integer countWhite = 0;
    private Integer countBlack = 0;
    private Colors [][] grid = new Colors[8][8];
    private int width = 8;
    private int height = 8;

    public String getCountBlack() {
        return countBlack.toString();
    }

    public String getCountWhite() {
        return countWhite.toString();
    }

    public Board () {
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7;++j) {
                Colors a = Colors.Empty;
                grid[i][j] = a;
            }
        }
        countWhite += 2;
        countBlack += 2;
        grid[3][3] = Colors.White;
        grid[4][3] = Colors.Black;
        grid[3][4] = Colors.Black;
        grid[4][4] = Colors.White;
    }

    public boolean isContained (int x, int y) {
        if ((x < 0 || x > width || y < 0 || y > height) && valueAt(x, y) != Colors.Empty) return false;
        return true;
    }

    public Colors valueAt (int x, int y) {
        if ((x >= 0 && x < width && y >= 0 && y < height)) return grid[x][y];
        return null;
    }

    public boolean isGameOver() {
        return countBlack + countWhite == 64;
    }

    public boolean isBlackWin() {
        return isGameOver() && countBlack > countWhite;
    }

    public boolean isWhiteWin() {
        return isGameOver() && countBlack < countWhite;
    }


    public boolean isContained (BlackOrWhite chip) {
        return isContained(chip.getParamX(), chip.getParamY());
    }

    public void putChip (Integer x, Integer y, Colors value) {
        if (isContained(x, y))
            if (value == Colors.Black) {
                countBlack += 1;
            } else if (value == Colors.White) countWhite += 1;
        grid[x][y] = value;
    }

    public void change (int x, int y, Colors color) {
        if(valueAt(x, y) != color) {
            grid[x][y] = color;
        }
        if (color == Colors.Black) {
            countWhite -= 1;
            countBlack += 1;
        } else {
            countBlack -= 1;
            countWhite += 1;
        }
    }
    public void putChip (BlackOrWhite chip) {
        putChip(chip.getParamX(), chip.getParamY(), chip.getColor());
    }


    @Override
    public String toString() {
        return "Board{" +
                "grid=" + Arrays.toString(grid) +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
