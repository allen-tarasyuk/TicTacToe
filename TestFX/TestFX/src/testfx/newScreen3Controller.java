package testfx;

import javafx.scene.control.Button;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class newScreen3Controller {



    




    @FXML
    void backBtn3(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("testfx.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void createBtn(ActionEvent event)throws IOException  {


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Game Created");
        alert.showAndWait();
    
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobby.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    
        lobbyController lobby = loader.getController();
        lobby.setButton("New Game");

        




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
