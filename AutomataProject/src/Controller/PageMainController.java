package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PhpAutomat;

public class PageMainController implements Initializable {

    @FXML
    private JFXButton Button;
    @FXML
    private JFXButton rabeeeeeeee;

    @FXML
    void btnComplex(ActionEvent event) {
        try {
            System.out.println("Hello World");
            PhpAutomat DFA = new PhpAutomat();
            Button.getScene().getWindow().hide();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/FXML/Php.fxml"));
            Parent root = fxml.load();
            PhpController phpController = fxml.getController();
            phpController.setDFA(DFA);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.show();
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(PageMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    @FXML
    private void Rabee(ActionEvent event) {
     Platform.exit();
    }

}
