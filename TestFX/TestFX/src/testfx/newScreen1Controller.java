package testfx;

import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class newScreen1Controller {

    @FXML
    void backBtn1(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("testfx.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

}
