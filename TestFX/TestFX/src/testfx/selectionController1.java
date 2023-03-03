package testfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class selectionController1 extends PlayerSelections {

    @Override
    public void initialize() {

        TwoPlayers twoPlayers = TwoPlayers.getInstance();
        selectingPlayer = twoPlayers.getPlayer1();
        otherPlayer = twoPlayers.getPlayer2();

        super.initialize();
    }

    @Override
    public void submit(ActionEvent event) throws IOException {
        super.submit(event);

        if (continueSubmit) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("player2Selection.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }
}
