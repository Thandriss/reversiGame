package view;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.BlackOrWhite;
import model.Colors;

public class ViewBlackOrWhite {
     public ViewBlackOrWhite (BlackOrWhite chip) {
         if (chip.getColor() == Colors.White || chip.getColor() == Colors.Black) {
             Circle chipe = new Circle();
             chipe.setRadius(10.0);
             chipe.setCenterX(chip.getParamX());
             chipe.setCenterY(chip.getParamY());
             if (chip.getColor() == Colors.Black) {
                 chipe.setFill(Color.BLACK);
             } else if (chip.getColor() == Colors.White) {
                 chipe.setFill(Color.WHITE);
             }
         } else {
             if (chip.getColor() == Colors.Empty) {
                 Rectangle chipe = new Rectangle();
                 chipe.setHeight(20.0);
                 chipe.setWidth(20.0);
                 chipe.setX(chip.getParamX() - 10);
                 chipe.setY(chip.getParamY() - 10);
                 chipe.setFill(Color.FORESTGREEN);
                 chipe.setStrokeWidth(3.0);
                 chipe.setStroke(Color.BURLYWOOD);
             } else if (chip.getColor() == Colors.CanPut) {
                 Circle chipe = new Circle();
                 chipe.setRadius(10.0);
                 chipe.setCenterX(chip.getParamX());
                 chipe.setCenterY(chip.getParamY());
                 chipe.setFill(Color.GRAY);
             }
         }
     }
}
