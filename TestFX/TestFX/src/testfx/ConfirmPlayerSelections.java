package testfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmPlayerSelections {

    @FXML
    private Label player1Name, player2Name;

    @FXML
    private ImageView player1Avatar, player2Avatar;

    @FXML
    public void initialize() {
        TwoPlayers twoPlayers = TwoPlayers.getInstance();
        Player player1 = twoPlayers.getPlayer1();
        Player player2 = twoPlayers.getPlayer2();

        player1Name.setText(player1.getUsername());
        player2Name.setText(player2.getUsername());
        player1Avatar.setImage(player1.getAvatarImg());
        player2Avatar.setImage(player2.getAvatarImg());
    }

    @FXML
    void startGame(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newScreen2.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void restartSetup(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("player1Selection.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
