package testfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class selectionController2 extends PlayerSelections {

    @Override
    public void initialize() {

        TwoPlayers twoPlayers = TwoPlayers.getInstance();
        selectingPlayer = twoPlayers.getPlayer2();
        otherPlayer = twoPlayers.getPlayer1();

        super.initialize();
    }

    @Override
    public void submit(ActionEvent event) throws IOException {
        super.submit(event);

        if (continueSubmit) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("confirmPlayerSelections.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }
}
