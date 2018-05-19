package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.PhpAutomat;
import model.PhpState;
import model.State;
import org.controlsfx.control.Notifications;

public class PhpController implements Initializable {

    PhpAutomat DFA;

    @FXML
    private JFXButton Back;
    @FXML
    private JFXTextField txtWord;

    public void setDFA(PhpAutomat DFA) {
        this.DFA = DFA;
    }

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
        String Word = txtWord.getText();

        List<State> Result = DFA.Solve(Word);
        System.out.println("Result : " + Result.size());
        State last = Result.get(Result.size() - 1);
        if (last.isIsFinal()) {
            Notifications notification = Notifications.create()
                    .title("Result")
                    .text("Correct : " + ((PhpState) last).getTypeAutomat())
                    .graphic(null)
                    .hideAfter(Duration.seconds(2))
                    .darkStyle()
                    .position(Pos.CENTER);
            notification.showConfirm();
        } else {
            Notifications notification = Notifications.create()
                    .title("Result")
                    .text("Not Correct")
                    .graphic(null)
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.CENTER);
            notification.showConfirm();
        }
    }

}
