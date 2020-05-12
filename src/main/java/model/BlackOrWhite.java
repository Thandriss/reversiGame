package model;

public class BlackOrWhite {

    private int paramX;
    private int paramY;
    private Colors color;

    public BlackOrWhite(int x, int y, Colors color) {
        this.paramX = x;
        this.paramY = y;
        this.color = color;
    }
    public int getParamX() {
        return paramX;
    }

    public int getParamY() {
        return paramY;
    }

    public void setParamX(int paramX) {
        this.paramX = paramX;
    }

    public void setParamY(int paramY) {
        this.paramY = paramY;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }

    public void changeColor() {
        if (color == Colors.Black) {
            this.color = Colors.White;
        } else if (color == Colors.White) {
            this.color = Colors.Black;
        }
    }
}
