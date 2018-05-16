/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import graph.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.SimpleAutomata;

/**
 * FXML Controller class
 *
 * @author void
 */
public class TransitionController implements Initializable {

    SimpleAutomata DFA;
    private int NumberOfState;
    private String Alphabet;

    public TransitionController(int NumberOfState, String Alphabet, SimpleAutomata DFA) {
        this.NumberOfState = NumberOfState;
        this.Alphabet = Alphabet;
        this.DFA = DFA;
    }

    JFXTextField vertex[], AlphaText[];
    JFXTextField edges[][];

    @FXML
    private JFXButton previousButton;

    @FXML
    private AnchorPane vertexPane;

    @FXML
    private JFXButton nextButton;

    @FXML
    private GridPane State;

    @FXML
    private GridPane edgesGrid;

    @FXML
    private GridPane AlphbetGrid;

    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
        AnchorPane rootPane = FXMLLoader.load(getClass().getResource("/FXML/CreateAutomat.fxml"));
        vertexPane.getChildren().setAll(rootPane);

    }

    @FXML
    void nextButtonAction(ActionEvent event) throws IOException {
        for (int i = 0; i < this.NumberOfState; i++) {
            for (int j = 0; j < this.Alphabet.length(); j++) {
                String from = vertex[i].getText();
                char ch = AlphaText[j].getText().charAt(0);
                String to = edges[i][j].getText();

                DFA.AddTransition(from, to, ch);
            }
        }
        nextButton.getScene().getWindow().hide();

        Main main = new Main(DFA);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vertex = new JFXTextField[NumberOfState];
        AlphaText = new JFXTextField[this.Alphabet.length()];
        edges = new JFXTextField[NumberOfState][this.Alphabet.length()];

        for (int i = 0; i < NumberOfState; i++) {
            vertex[i] = new JFXTextField();
            vertex[i].setFocusColor(Color.web("1A237E"));
            vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
            vertex[i].setText("q" + Integer.toString(i));
            vertex[i].setEditable(false);
            State.add(vertex[i], 0, i);
        }
        for (int i = 0; i < NumberOfState; i++) {
            for (int j = 0; j < (int) Alphabet.length(); j++) {
                edges[i][j] = new JFXTextField();
                edges[i][j].setFocusColor(Color.web("#00838F"));
                edges[i][j].setUnFocusColor(Color.web("#4d4d4d"));
                edges[i][j].setPromptText("null");

                edgesGrid.add(edges[i][j], j, i);
            }
        }
        for (int i = 0; i < Alphabet.length(); i++) {
            AlphaText[i] = new JFXTextField(String.valueOf(Alphabet.charAt(i)));
            AlphaText[i].setFocusColor(Color.web("1A237E"));
            AlphaText[i].setUnFocusColor(Color.web("#4d4d4d"));
            AlphaText[i].setEditable(false);
            AlphbetGrid.add(AlphaText[i], i, 0);
        }
    }
}
