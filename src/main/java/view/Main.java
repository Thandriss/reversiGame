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
import model.Board;
import model.Colors;
import model.GameRules;
import java.io.InputStream;
import java.util.List;

public class Main extends Application {
    private Board board1;
    private final  Text White = new Text(800, 200, "White: ");
    private final  Text Black = new Text(800, 600, "Black: ");
    private final Text scoreBlack = new Text(900, 600, "");
    private final Text scoreWhite = new Text(900, 200, "");
    private final Text turn = new Text(800, 400, "Turn: ");
    private final Text currentPlayer = new Text(900, 400, "");
    private final Text winner = new Text(800, 500, "");
    private int i = 0;
    private GridPane viewOfBoard = new GridPane();
    private Canvas[][] SaveCanvas = new Canvas[8][8];
    private Colors color = Colors.Black;
    private List<Pair<Integer, Integer>> canBePut;


    @Override
    public void start(Stage primaryStage) throws Exception{
        InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        board1 = GameRules.startGame();
        White.setFont(Font.font(30.0));
        Black.setFont(Font.font(30.0));
        turn.setFont(Font.font(30.0));
        scoreWhite.setFont(Font.font(30.0));
        scoreBlack.setFont(Font.font(30.0));
        currentPlayer.setFont(Font.font(30.0));
        winner.setFont(Font.font(30.0));
        Group rootWind = new Group(White, Black, scoreBlack, scoreWhite, turn, currentPlayer, viewOfBoard, winner);
        fullCanvasForView();
        canBePut = GameRules.whereToStand(color, board1);
        GameRules.whereToStandPut(board1, canBePut);
        drawBoard();
        Scene sceneOfGame = new Scene(rootWind, 1000, 788);
        sceneOfGame.setOnMouseClicked(mouseHandler);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(sceneOfGame);
        primaryStage.show();
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
        checking();
        for (int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                GraphicsContext first = SaveCanvas[i][j].getGraphicsContext2D();
                first.setStroke(Color.BLACK);
                first.setLineWidth(3);
                first.strokeRect(x, y, 100, 100);
                first.setFill(Color.SLATEGRAY);
                first.fillRect(x, y, 99, 99);
                if (board1.valueAt(i, j) == Colors.CanPut) {
                    first.setFill(Color.POWDERBLUE);
                    first.fillOval(40, 40,  20, 20);
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
        currentPlayer.setText(color.toString());
        scoreBlack.setText(board1.getCountBlack());
        scoreWhite.setText(board1.getCountWhite());
    }

    private EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            int x = (int) event.getX() / 100;
            int y = (int) event.getY() / 100;
            checking();
            if (!canBePut.isEmpty()) {
                if (GameRules.putTheChip(board1, x, y, color, canBePut)) {
                    GameRules.changeColor(board1, x, y, color);
                    GameRules.deleteCanPut(board1, canBePut);
                    if (board1.isGameOver()) {
                        if (board1.isBlackWin()) {
                            winner.setText("Winner is Black");
                        } else if (board1.isWhiteWin()) {
                            winner.setText("Winner is White");
                        } else {
                            winner.setText("We are the champions");
                        }
                    }
                    drawBoard();
                    canBePut.clear();
                    i += 1;
                    checking();
                    canBePut = GameRules.whereToStand(color, board1);
                    if (canBePut.isEmpty()) {
                        i += 1;
                        checking();
                        canBePut = GameRules.whereToStand(color, board1);
                    }
                    GameRules.whereToStandPut(board1, canBePut);
                    drawBoard();
                }
            } else {
                i += 1;
                checking();
                canBePut = GameRules.whereToStand(color, board1);
                GameRules.whereToStandPut(board1, canBePut);
                drawBoard();
            }
        }
    };

    private void checking () {
        if (i % 2 == 0) {
            color = Colors.Black;
        }else{
            color = Colors.White;
        }
    }
}
