package Controller;

import com.jfoenix.controls.JFXButton;
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

public class PageMainController implements Initializable {

    @FXML
    private JFXButton Button;
    @FXML
    void btnComplex(ActionEvent event) {

    }

    @FXML
    void btnSimple(ActionEvent event) throws IOException {
        Button.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/CreateAutomat.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        stage.setResizable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
