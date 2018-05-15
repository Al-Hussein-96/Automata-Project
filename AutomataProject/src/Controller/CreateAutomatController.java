package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import graph.Main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    void btnBack(ActionEvent event) {

    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Next.getScene().getWindow().hide();
        SimpleAutomata DFA = new SimpleAutomata(txtpath.getText());
        Main main = new Main(DFA);

    }

    @FXML
    void btnNext(ActionEvent event) throws IOException {
        Next.getScene().getWindow().hide();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/FXML/Transition.fxml"));
        TransitionController transitionController = new TransitionController((int)ComNumberState.getValue(), txtalphabet.getText());
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
            txtalphabet.setDisable(true);

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

}
