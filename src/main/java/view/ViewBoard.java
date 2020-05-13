package view;

import javafx.scene.layout.Pane;
import model.BlackOrWhite;
import model.Board;
import model.Colors;

public class ViewBoard {
    private Board board;
    public ViewBoard () {
        for (int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                if(board.valueAt(i, j) == Colors.Empty) {
                    BlackOrWhite empty = new BlackOrWhite(i, j, Colors.Empty);

                }
            }
        }
    }
}
