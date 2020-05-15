package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Board;
import model.Colors;
import model.GameRules;

public class MainContr {

    @FXML
    private Button startGameButton;


    public Button getStartGameButton() {
        return startGameButton;
    }


    /*@FXML
    private void click(ActionEvent event) {
        Stage a = new Stage();
        Group rootSecondWind = new Group();
        Canvas canvas = new Canvas(800, 800);
        rootSecondWind.getChildren().add(canvas);
        Board board = GameRules.startGame();
        GraphicsContext graph = canvas.getGraphicsContext2D();
        drawBoard(graph, board);
        Scene windowWithField = new Scene(rootSecondWind, 800, 800);
        a.setScene(windowWithField);
        a.show();
    }

    private void drawBoard(GraphicsContext graph, Board board) {
        double x = 0.0;
        double y = 0.0;
        for (int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                graph.setStroke(Color.BLACK);
                graph.setLineWidth(3);
                graph.strokeRect(x, y, 100, 100);
                graph.setFill(Color.SLATEGRAY);
                graph.fillRect(x, y, 100, 100);
                if (board.valueAt(i, j) == Colors.CanPut) {
                    graph.setStroke(Color.GRAY);
                    graph.setLineWidth(5);
                    graph.strokeOval(x + 10, y + 10, 80, 80);
                }else if (board.valueAt(i, j) == Colors.Black) {
                    graph.setStroke(Color.BLACK);
                    graph.setLineWidth(5);
                    graph.strokeOval(x + 10, y + 10, 80, 80);
                } else if (board.valueAt(i, j) == Colors.White) {
                    graph.setStroke(Color.WHITE);
                    graph.setLineWidth(5);
                    graph.strokeOval(x + 10, y + 10, 80, 80);
                }

                x += 100.0;
            }
            x = 0.0;
            y += 100.0;
        }
    }*/
}
