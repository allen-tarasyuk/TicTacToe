package testfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class selectionController2 {

    @FXML
    private Button myButton2;

    @FXML
    private Label myLabel2;

    @FXML
    private TextField myTextField2;

    Player player2;

    @FXML
    public void initialize() {
        TwoPlayers twoPlayers = TwoPlayers.getInstance();
        player2 = twoPlayers.getPlayer2();

        if (player2.getUsername() != "") {
            System.out.println("Setting saved player 2 name");
            myTextField2.setText(player2.getUsername());
        }
    }

    @FXML
    void submit2(ActionEvent event) throws IOException {

        if(myTextField2.getText() == ""){
            myLabel2.setText("Please Enter Name"); 
        }else{
            player2.setUsername(myTextField2.getText());
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("confirmPlayerSelections.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

    }

}
