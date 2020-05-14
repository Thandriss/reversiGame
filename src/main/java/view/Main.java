package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Board;
import model.Colors;
import model.GameRules;
import javafx.scene.canvas.Canvas;
import java.io.InputStream;

public class Main extends Application {
    Scene windowWithField;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group rootSecondWind = new Group();
        Canvas canvas = new Canvas(800, 800);
        rootSecondWind.getChildren().add(canvas);
        Board  board = GameRules.startGame();
        GraphicsContext graph = canvas.getGraphicsContext2D();
        drawBoard(graph, board);
        Scene windowWithField = new Scene(rootSecondWind, 800, 800);
        InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("/startWindow.fxml"));
        Scene scene = new Scene(root, 1280, 800);
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBoard(GraphicsContext graph, Board board) {
        double x = 0.0;
        double y = 0.0;
        for (int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                graph.setStroke(Color.BLACK);
                graph.setLineWidth(3);
                graph.strokeRect(x, y, 100, 100);
                graph.setFill(Color.FORESTGREEN);
                graph.fillRect(x, y, 100, 100);
                if (board.valueAt(i, j) == Colors.CanPut) {
                    graph.setFill(Color.GRAY);
                    graph.fillOval(x, y, 100, 100);
                } else if (board.valueAt(i, j) == Colors.Black || board.valueAt(i, j) == Colors.White) {
                    if (board.valueAt(i, j) == Colors.Black) {
                        graph.setFill(Color.BLACK);
                        graph.fillOval(x, y, 100, 100);
                    } else if (board.valueAt(i, j) == Colors.White) {
                        graph.setFill(Color.WHITE);
                        graph.fillOval(x, y, 100, 100);
                    }
                }
                x += 100.0;
            }
            x = 0.0;
            y += 100.0;
        }
    }
    public static void main(String[] args) {
            launch(args);
        }
}
