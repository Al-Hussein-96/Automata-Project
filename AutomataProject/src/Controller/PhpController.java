package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PhpAutomat;

public class PhpController implements Initializable {

    @FXML
    private JFXButton Back;
    @FXML
    private JFXTextField txtWord;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        Back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PageMain.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void btnCheck(ActionEvent event) {
    }

}
