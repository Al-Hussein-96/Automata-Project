package graph;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.SimpleAutomata;
import model.State;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Al-Hussein
 */
public class fxmlController implements Initializable {

    SimpleAutomata DFA;
    @FXML
    private JFXButton Back;

    @FXML
    private JFXTextField txtWord;

    @FXML
    void btnCheck(ActionEvent event) {
        String Word = txtWord.getText();

        for (int i = 0; i < Word.length(); i++) {
            boolean ok = false;
            for (int j = 0; j < DFA.Alphabet.length(); j++) {
                if (Word.charAt(i) == DFA.Alphabet.charAt(j)) {
                    ok = true;
                }
            }
            if (ok == false) {
                Notifications notification = Notifications.create()
                        .title("")
                        .text("Pattern is contain char not exist in Alphabet")
                        .graphic(null)
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER);
                notification.showConfirm();
                return;
            }

        }
        List<State> Result = DFA.Solve(DFA.getStartState(), Word);
        System.out.println("Result : " + Result.size());
        State last = Result.get(Result.size() - 1);
        if (last.isIsFinal()) {
            Notifications notification = Notifications.create()
                    .title("")
                    .text("Correct")
                    .graphic(null)
                    .darkStyle()
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.CENTER);
            notification.showConfirm();
        } else {
            Notifications notification = Notifications.create()
                    .title("Create Project")
                    .text("Not Correct")
                    .graphic(null)
                    .darkStyle()
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.CENTER);
            notification.showConfirm();
        }

    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        
        Back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/CreateAutomat.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        stage.setResizable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtWord.setFont(Font.font("Verdana", 20));

    }

    public void setDFA(SimpleAutomata DFA) {
        this.DFA = DFA;
    }

}
