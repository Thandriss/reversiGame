package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
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


    @Override
    public void start(Stage primaryStage) throws Exception{
        InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/startWindow.fxml"));
        primaryStage.setResizable(false);
        Parent root = loader.load();
        Scene sceneOfGame = new Scene(root, 1280, 800);
        MainContr contr = loader.getController();
        contr.setStage(primaryStage);
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(sceneOfGame);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
