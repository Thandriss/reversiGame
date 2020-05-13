package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.BlackOrWhite;
import model.Board;
import model.Colors;
import model.GameRules;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {
    Scene windowWithField;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane rootLayout = new Pane();
        Board  board = GameRules.startGame();
        Canvas canvas = new Canvas();

        InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("/startWindow.fxml"));
        Scene scene = new Scene(root, 1280, 800);
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void drawBoard(Canvas canvas, Board board) {
        int x = 0;
        int y = 0;
        for (int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                if(board.valueAt(i, j) == Colors.Empty) {
                    BlackOrWhite empty = new BlackOrWhite(i, j, Colors.Empty);
                    javafx.scene.shape.Rectangle chipe = new Rectangle();
                    chipe.setHeight(20.0);
                    chipe.setWidth(20.0);
                    chipe.setX(x);
                    chipe.setY(y);
                    chipe.setFill(javafx.scene.paint.Color.FORESTGREEN);
                    chipe.setStrokeWidth(3.0);
                    chipe.setStroke(Color.BURLYWOOD);
                } else if (board.valueAt(i, j) == Colors.CanPut) {
                    Circle chipe = new Circle();
                    chipe.setRadius(10.0);
                    chipe.setCenterX(x);
                    chipe.setCenterY(y);
                    chipe.setFill(Color.GRAY);
                } else if (board.valueAt(i, j) == Colors.Black || board.valueAt(i, j) == Colors.White) {
                    Circle chipe = new Circle();
                    chipe.setRadius(10.0);
                    chipe.setCenterX(x - 10.0);
                    chipe.setCenterY(y - 10.0);
                    if (board.valueAt(i, j) == Colors.Black) {
                        chipe.setFill(Color.BLACK);
                    } else if (board.valueAt(i, j) == Colors.White) {
                        chipe.setFill(Color.WHITE);
                    }
                }
                x += 20.0;
            }
        }
    }
    public static void main(String[] args) {
            launch(args);
        }
}
