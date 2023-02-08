package testfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Label gameTitle;

    @FXML
    void TwoBtn(ActionEvent event)throws IOException  {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("player1Selection.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void networkBtn(ActionEvent event)throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newScreen3.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void singleBtn(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newScreen1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
