package model;

import java.util.Arrays;

public class Board {
    private int countWhite = 0;
    private int countBlack = 0;
    private Colors [][] grid = new Colors[8][8];
    private int width, height;

    public int getCountBlack() {
        return countBlack;
    }

    public int getCountWhite() {
        return countWhite;
    }

    public void Board () {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; i <= 7; i++) {
                Colors a = Colors.Empty;
                grid[i][j] = a;
            }
        }
        grid[3][3] = Colors.White;
        grid[4][3] = Colors.Black;
        grid[3][4] = Colors.Black;
        grid[4][4] = Colors.White;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isContained (int x, int y) {
        if ((x < 0 || x > width || y < 0 || y > height) && valueAt(x, y) == Colors.Empty) return false;
        return true;
    }

    public Colors valueAt (int x, int y) {
        if (isContained(x, y)) return grid[x][y];
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
            } else countWhite += 1;
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
            countBlack += 1;
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
