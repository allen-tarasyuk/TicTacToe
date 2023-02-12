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

public class selectionController1 {

    @FXML
    private Button myButton1;

    @FXML
    private Label myLabel1;

    @FXML
    private TextField myTextField1;

    Player player1;

    @FXML
    public void initialize() {
        TwoPlayers twoPlayers = TwoPlayers.getInstance();
        player1 = twoPlayers.getPlayer1();

        if (player1.getUsername() != "") {
            System.out.println("Setting saved player 1 name");
            myTextField1.setText(player1.getUsername());
        }
    }

    @FXML
    void submit(ActionEvent event) throws IOException {

            if(myTextField1.getText() == ""){
                myLabel1.setText("Please Enter Name"); 
            }else{
                player1.setUsername(myTextField1.getText());
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("player2Selection.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
       
    }















    // @FXML
    // void submit(ActionEvent event) {

    //     try{
    //         age = Integer.parseInt(myTextField1.getText());
    //         System.out.println(age);

    //         if(age >= 18){
    //             myLabel1.setText("You are signed up");
    //         }else{
    //             myLabel1.setText("You must be years or older");
    //         }

    //     }catch(NumberFormatException e){
    //         myLabel1.setText("Enter only numbers");
    //     }catch(Exception e){
    //         myLabel1.setText("Enter only numbers");
    //     }
    // }






}
