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

    String name2;

    @FXML
    void submit2(ActionEvent event) throws IOException {

        name2 = myTextField2.getText();
        System.out.println(name2);

        if(name2 == ""){
            myLabel2.setText("Please Enter Name"); 
        }else{ 
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("newScreen2.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

    }

}
