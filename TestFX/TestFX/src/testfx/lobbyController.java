package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
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
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6));

        // buttons.forEach(button ->{
        //     setupButton(button);
        //     button.setFocusTraversable(false);
        // });

    }


    @FXML
    void lobbyBackBtn(ActionEvent event)throws IOException  {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newScreen3.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

       

    }

}
