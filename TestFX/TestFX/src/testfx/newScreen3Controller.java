package testfx;

import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class newScreen3Controller {

    @FXML
    void backBtn3(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("testfx.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    void lobbyBtn(ActionEvent event) throws IOException {
        System.out.println("Testing Lobby Button");


        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("lobby.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);


    }
    

}
