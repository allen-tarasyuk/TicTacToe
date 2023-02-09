package testfx;

import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

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
        
        Parent root = FXMLLoader.load(new URL("file:src/testfx/newScreen1.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("newScreen1.css").toExternalForm());
        
        stage.setTitle("New Screen");
        stage.setScene(scene);
        stage.show();
    }


}
