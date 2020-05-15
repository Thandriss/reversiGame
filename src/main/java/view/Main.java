package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("/startWindow.fxml"));
        Scene scene = new Scene(root, 1280, 800);
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(scene);
        MainContr a = new MainContr();
        Button toTheNextScene = a.getStartGameButton();
        toTheNextScene.setOnAction(event -> System.out.println("Oh!!"));
        primaryStage.show();
    }

    public static void main(String[] args) {
            launch(args);
        }
}
