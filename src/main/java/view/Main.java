package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.BlackOrWhite;
import model.Board;
import model.Colors;
import model.GameRules;
import java.io.InputStream;

public class Main extends Application {
    private Stage mainWindow;
    private Scene sceneOfGame;
    private Board board1;
    Board board2 = board1;
    private Canvas canvas = new Canvas(1000, 800);
    private GraphicsContext graph = canvas.getGraphicsContext2D();
    private final  Text White = new Text(800, 100, "White: ");
    private final  Text Black = new Text(800, 600, "Black: ");
    private final Text scoreBlack = new Text(900, 600, "");
    private final Text scoreWhte = new Text(900, 100, "");
    private static int i = 0;
    private Group rootWind;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = primaryStage;
        InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
        Image image = new Image(iconStream);
        mainWindow.getIcons().add(image);
        board1 = GameRules.startGame();
        White.setFont(Font.font(30.0));
        Black.setFont(Font.font(30.0));
        rootWind = new Group(White, Black, scoreBlack, scoreWhte);
        rootWind.getChildren().add(canvas);
        drawBoard(graph, board1);
        sceneOfGame = new Scene(rootWind, 1000, 788);
        canvas.setOnMouseClicked(mouseHandler);
        mainWindow.setResizable(false);
        mainWindow.setTitle("Reversi");
        mainWindow.setScene(sceneOfGame);
        mainWindow.show();
    }

    public static void main(String[] args) { launch(args); }

    private void drawBoard(GraphicsContext graph, Board board) {
        Colors color = Colors.Black;
        board2 = board1;
        GameRules.whereToStandPut(board2, color);
        double x = 0.0;
        double y = 0.0;
        for (int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                graph.setStroke(Color.BLACK);
                graph.setLineWidth(3);
                graph.strokeRect(x, y, 100, 100);
                graph.setFill(Color.SLATEGRAY);
                graph.fillRect(x, y, 100, 100);
                if (board2.valueAt(i, j) == Colors.CanPut) {
                    graph.setStroke(Color.DARKRED);
                    graph.setLineWidth(5);
                    graph.strokeOval(x + 10, y + 10, 80, 80);
                }else if (board2.valueAt(i, j) == Colors.Black) {
                    graph.setStroke(Color.BLACK);
                    graph.setLineWidth(5);
                    graph.strokeOval(x + 10, y + 10, 80, 80);
                } else if (board2.valueAt(i, j) == Colors.White) {
                    graph.setStroke(Color.WHITE);
                    graph.setLineWidth(5);
                    graph.strokeOval(x + 10, y + 10, 80, 80);
                }

                x += 100.0;
            }
            x = 0.0;
            y += 100.0;
        }
        scoreBlack.setText(board1.getCountBlack());
        scoreWhte.setText(board1.getCountWhite());
    }
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            int x = (int) event.getX();
            int y = (int)  event.getY();
            Colors color;
            if (i % 2 == 0) {
                color = Colors.Black;
            }else{
                color = Colors.White;
            }
            BlackOrWhite chip = new BlackOrWhite(x / 100, y / 100, color);
            GameRules.putTheChip(board1, chip);
            drawBoard(graph, board1);
            rootWind.getChildren().add(canvas);
        }
    };
}
