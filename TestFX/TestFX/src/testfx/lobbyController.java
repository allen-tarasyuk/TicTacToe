package testfx;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class lobbyController {

    @FXML
    void lobbyBackBtn(ActionEvent event)throws IOException  {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newScreen3.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

       

    }

}
