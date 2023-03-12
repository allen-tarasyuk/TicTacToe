package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class lobbyController implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;
   
    ArrayList<Button> buttons;

  
    public void setButton(String str){
        button1.setText(str);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void lobbyBackBtn(ActionEvent event)throws IOException  {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newScreen3.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    void newGameBtn(ActionEvent event) throws IOException  {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newScreen1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Waiting for another Player...");
        alert.showAndWait();

    }


}
