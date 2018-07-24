package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import graph.Main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.SimpleAutomata;
import org.controlsfx.control.ToggleSwitch;

public class CreateAutomatController implements Initializable {

    @FXML
    private GridPane GridPane;
    @FXML
    private JFXComboBox<Integer> ComNumberState;

    @FXML
    private JFXComboBox<Integer> ComStartState;

    @FXML
    private JFXTextField txtalphabet;

    @FXML
    private JFXTextField txtpath;
    @FXML
    private ToggleSwitch toggle;

    @FXML
    private JFXButton open;

    @FXML
    private JFXButton create;

    @FXML
    private JFXButton Next;

    @FXML
    private JFXCheckBox q0;

    @FXML
    private JFXCheckBox q1;

    @FXML
    private JFXCheckBox q2;

    @FXML
    private JFXCheckBox q3;

    @FXML
    private JFXCheckBox q4;

    @FXML
    private JFXCheckBox q5;

    @FXML
    private JFXCheckBox q6;

    @FXML
    private JFXCheckBox q7;

    @FXML
    private JFXCheckBox q8;

    @FXML
    private JFXCheckBox q9;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Next.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PageMain.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Next.getScene().getWindow().hide();
        SimpleAutomata DFA = new SimpleAutomata(txtpath.getText());
        Main main = new Main(DFA);

    }

    @FXML
    void btnNext(ActionEvent event) throws IOException {
        List<String> Final = new ArrayList<>();

        if (q0.isSelected()) {
            Final.add("q0");
        }
        if (q1.isSelected()) {
            Final.add("q1");
        }
        if (q2.isSelected()) {
            Final.add("q2");
        }
        if (q3.isSelected()) {
            Final.add("q3");
        }
        if (q4.isSelected()) {
            Final.add("q4");
        }
        if (q5.isSelected()) {
            Final.add("q5");
        }
        if (q6.isSelected()) {
            Final.add("q6");
        }
        if (q7.isSelected()) {
            Final.add("q7");
        }
        if (q8.isSelected()) {
            Final.add("q8");
        }
        if (q9.isSelected()) {
            Final.add("q9");
        }

        SimpleAutomata DFA = new SimpleAutomata(this.ComNumberState.getValue(), this.ComStartState.getValue(), Final, this.txtalphabet.getText());
        Next.getScene().getWindow().hide();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/FXML/Transition.fxml"));
        TransitionController transitionController = new TransitionController((int) ComNumberState.getValue(), txtalphabet.getText(), DFA);
        fxml.setController(transitionController);
        Parent root = fxml.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        stage.setResizable(false);

    }

    @FXML
    void btnOpen(ActionEvent event) {
        FileChooser dc = new FileChooser();
        File selectedFile = dc.showOpenDialog(null);

        if (selectedFile != null) {
            txtpath.setText(selectedFile.getPath());
        }

    }

    @FXML
    void onMouse(MouseEvent event) {
        if (toggle.isSelected()) {
            ComNumberState.setDisable(true);
            ComStartState.setDisable(true);
            txtalphabet.setDisable(true);
            open.setDisable(false);
            txtpath.setDisable(false);

            Next.setVisible(false);
            create.setVisible(true);

        } else {
            ComNumberState.setDisable(false);
            ComStartState.setDisable(false);
            txtalphabet.setDisable(false);
            open.setDisable(true);
            txtpath.setDisable(true);

            Next.setVisible(true);
            create.setVisible(false);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtalphabet.setFont(Font.font("Verdana", 20));
        txtpath.setFont(Font.font("Verdana", 20));

        open.setDisable(true);
        txtpath.setDisable(true);
        for (int i = 1; i <= 10; i++) {
            ComNumberState.getItems().add(i);
        }
        ComNumberState.valueProperty().addListener((observable) -> {
            ComStartState.getItems().clear();

            for (int i = 0; i < 10; i++) {
                GridPane.getChildren().get(i).setVisible(false);
            }

            for (int i = 0; i < (int) ComNumberState.getValue(); i++) {
                ComStartState.getItems().add(i);
                GridPane.getChildren().get(i).setVisible(true);
            }

        });

    }

    @FXML
    private void getAlpha(ActionEvent event) {
        
    }

}
