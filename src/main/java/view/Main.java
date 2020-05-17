package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.BlackOrWhite;
import model.Board;
import model.Colors;
import model.GameRules;
import java.io.InputStream;
import java.util.List;

public class Main extends Application {
    private Stage mainWindow;
    private Scene sceneOfGame;
    private Board board1;
    private final  Text White = new Text(800, 100, "White: ");
    private final  Text Black = new Text(800, 600, "Black: ");
    private final Text scoreBlack = new Text(900, 600, "");
    private final Text scoreWhte = new Text(900, 100, "");
    private int i = 0;
    private Group rootWind;
    private GridPane viewOfBoard = new GridPane();
    private Canvas[][] SaveCanvas = new Canvas[8][8];
    Colors color = Colors.Black;
    private List<Pair<Integer, Integer>> canBePut;
    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = primaryStage;
        InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
        Image image = new Image(iconStream);
        mainWindow.getIcons().add(image);
        board1 = GameRules.startGame();
        White.setFont(Font.font(30.0));
        Black.setFont(Font.font(30.0));
        rootWind = new Group(White, Black, scoreBlack, scoreWhte, viewOfBoard);
        fullCanvasForView();
        canBePut = GameRules.whereToStand(color, board1);
        GameRules.whereToStandPut(board1, canBePut);
        drawBoard();
        sceneOfGame = new Scene(rootWind, 1000, 788);
        sceneOfGame.setOnMouseClicked(mouseHandler);
        mainWindow.setResizable(false);
        mainWindow.setTitle("Reversi");
        mainWindow.setScene(sceneOfGame);
        mainWindow.show();
    }

    public static void main(String[] args) { launch(args); }

    private void fullCanvasForView() {
        for (int i = 0; i <=7; i++){
            for (int j = 0; j <=7; j++) {
                SaveCanvas[i][j] = new Canvas(100, 100);
                viewOfBoard.add(SaveCanvas[i][j], i, j);
            }
        }
    }

    private void drawBoard() {
        double x = 0.0;
        double y = 0.0;
        if (i % 2 == 0) {
            color = Colors.Black;
        }else{
            color = Colors.White;
        }
        for (int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                GraphicsContext first = SaveCanvas[i][j].getGraphicsContext2D();
                first.setStroke(Color.BLACK);
                first.setLineWidth(3);
                first.strokeRect(x, y, 100, 100);
                first.setFill(Color.SLATEGRAY);
                first.fillRect(x, y, 99, 99);
                if (board1.valueAt(i, j) == Colors.CanPut) {
                    first.setStroke(Color.DARKRED);
                    first.setLineWidth(5);
                    first.strokeOval(10, 10,  80, 80);
                }else if (board1.valueAt(i, j) == Colors.Black) {
                    first.setStroke(Color.BLACK);
                    first.setLineWidth(5);
                    first.strokeOval(10, 10, 80, 80);
                } else if (board1.valueAt(i, j) == Colors.White) {
                    first.setStroke(Color.WHITE);
                    first.setLineWidth(5);
                    first.strokeOval(10, 10, 80, 80);
                }
            }
        }
        scoreBlack.setText(board1.getCountBlack());
        scoreWhte.setText(board1.getCountWhite());
    }
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            int x = (int) event.getX();
            int y = (int)  event.getY();
            if (i % 2 == 0) {
                color = Colors.Black;
            }else{
                color = Colors.White;
            }
            BlackOrWhite chip = new BlackOrWhite(x / 100, y / 100, color);
            GameRules.putTheChip(board1, chip, canBePut);
            GameRules.changeColor(board1, chip);
            GameRules.deletCanPut(board1, canBePut);
            drawBoard();
            canBePut.clear();
            i += 1;
            if (i % 2 == 0) {
                color = Colors.Black;
            }else{
                color = Colors.White;
            }
            canBePut = GameRules.whereToStand(color, board1);
            GameRules.whereToStandPut(board1, canBePut);
            drawBoard();
        }
    };
}
