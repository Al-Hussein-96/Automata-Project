package graph;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SimpleAutomata;
import model.State;
import model.Transitions;

public class Main {

    Graph graph = new Graph();

    public Main(SimpleAutomata DFA) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxml.fxml"));
        BorderPane root = fxml.load();
        
        fxmlController c = fxml.getController();
        
        c.setDFA(DFA);

        graph = new Graph();
        
        
        root.setCenter(graph.getScrollPane());
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        stage.setScene(scene);
        stage.show();

        addGraphComponents(DFA);

        Layout layout = new RandomLayout(graph);
        layout.execute();
    }

//    @Override
//    public void start(Stage primaryStage) {
//        BorderPane root = new BorderPane();
//
//        graph = new Graph();
//
//        root.setCenter(graph.getScrollPane());
//
//        Scene scene = new Scene(root, 1024, 768);
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        addGraphComponents();
//
//        Layout layout = new RandomLayout(graph);
//        layout.execute();
//
//    }
//
    private void addGraphComponents(SimpleAutomata DFA) {

        Model model = graph.getModel();

        graph.beginUpdate();
        model.addCell("T");
        for (int i = 0; i < DFA.getNumberOfStates(); i++) {
            model.addCell("q" + String.valueOf(i));
        }

        for (int i = 0; i < DFA.getNumberOfStates(); i++) {
            State state = DFA.getState(i);
            for (Transitions u : state.getTrans()) {
//                System.out.println(state.getName() + " : " + u.getTo().getName());
                model.addEdge(state.getName(), (u.getTo().getName().equals("DeadState") ? "T" : u.getTo().getName()), u.getCh());
            }
        }

//        model.addCell("q0");
//        model.addCell("q1");
//        model.addCell("q2");
//        model.addCell("T");
////         model.addCell("1");
////         model.addCell("2");
////         model.addCell("3");
////         model.addCell("4");
////         model.addCell("5");
////         model.addCell("6");
//
//        model.addEdge("q0", "q1");
//        model.addEdge("q0", "T");
//        model.addEdge("q1", "T");
//        model.addEdge("q1", "q2");
//         model.addEdge("q2", "q2");
//         model.addEdge("T", "1");
//         model.addEdge("1", "2");
//         model.addEdge("2", "3");
//         model.addEdge("3", "4");
//         model.addEdge("4", "5");
//         model.addEdge("5", "6");
        graph.endUpdate();

    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }

}
