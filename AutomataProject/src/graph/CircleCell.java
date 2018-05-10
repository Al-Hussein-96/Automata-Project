package graph;



import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author Al-Hussein
 */
public class CircleCell extends Cell {

    public CircleCell(String cellId) {
        super(cellId);

        Circle view = new Circle(25);

        Text text = new Text(cellId);
        text.setBoundsType(TextBoundsType.VISUAL);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(view, text);
        
        view.setStroke(Color.BLACK);
        view.setFill(Color.WHITE);
        
        setView(stack);

    }

}
