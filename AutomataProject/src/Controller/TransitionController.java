/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
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

/**
 * FXML Controller class
 *
 * @author void
 */
public class TransitionController implements Initializable {

    private int NumberOfState;
    private String Alphabet;

    public TransitionController(int NumberOfState, String Alphabet) {
        this.NumberOfState = NumberOfState;
        this.Alphabet = Alphabet;
    }

    JFXTextField vertex[];
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
        AnchorPane rootPane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        vertexPane.getChildren().setAll(rootPane);

    }

    @FXML
    void nextButtonAction(ActionEvent event) throws IOException {

        String[] vertexValue = new String[NumberOfState];
        Double[][] edgesValue = new Double[NumberOfState][NumberOfState];

        boolean valueTaker = true;

        for (int i = 0; i < NumberOfState; i++) {
            vertexValue[i] = vertex[i].getText();

            if ("".equals(vertexValue[i])) {
                JFXSnackbar snackbar = new JFXSnackbar(vertexPane);
                vertex[i].setUnFocusColor(Color.web("#ff0000"));
                snackbar.show("Vertex Field can not be Empty!", 5000);
                valueTaker = false;
            } else {
                vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
            }
        }

        for (int i = 0; i < NumberOfState; i++) {
            for (int j = 0; j < NumberOfState; j++) {

                String num = edges[i][j].getText();

                if (num == "") {
                    edges[i][j].setUnFocusColor(Color.web("#ff0000"));
                    JFXSnackbar snackbar = new JFXSnackbar(vertexPane);
                    valueTaker = false;
                    snackbar.show("Edge field can not be Empty!", 5000);
                } else if ("inf".equals(num) || "i".equals(num)) {
                    edgesValue[i][j] = Double.POSITIVE_INFINITY;
                } else {
                    try {
                        edgesValue[i][j] = Double.parseDouble(num);
                        edges[i][j].setUnFocusColor(Color.web("#4d4d4d"));
                    } catch (Exception e) {
                        JFXSnackbar snackbar = new JFXSnackbar(vertexPane);
                        edges[i][j].setUnFocusColor(Color.web("#ff0000"));
                        snackbar.show("One or more fields are not valid!", 5000);
                        valueTaker = false;
                    }
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vertex = new JFXTextField[NumberOfState];
        edges = new JFXTextField[NumberOfState][NumberOfState];

        for (int i = 0; i < NumberOfState; i++) {
            vertex[i] = new JFXTextField();
            vertex[i].setFocusColor(Color.web("1A237E"));
            vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
            vertex[i].setText(Integer.toString(i + 1));
            State.add(vertex[i], 0, i);
        }

        for (int i = 0; i < NumberOfState; i++) {
            for (int j = 0; j < NumberOfState; j++) {
                edges[i][j] = new JFXTextField();
                edges[i][j].setFocusColor(Color.web("#00838F"));
                edges[i][j].setUnFocusColor(Color.web("#4d4d4d"));
                edges[i][j].setPromptText("v" + (i + 1) + "e" + (j + 1));

                if (i == j) {
                    edges[i][j].setText("0");
                    edges[i][j].setEditable(false);
                }

                edgesGrid.add(edges[i][j], j, i);
            }
        }
    }
}
