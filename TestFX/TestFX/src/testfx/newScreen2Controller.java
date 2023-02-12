package testfx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class newScreen2Controller implements Initializable {

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

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;

    @FXML
    private Text winnerText;

    private int playerTurn = 0;

    int counter = 0;

    ArrayList<Button> buttons;

    private ArrayList<ImageView> imgViews;

    private final Player player1;
    private final Player player2;

    public newScreen2Controller() {
        TwoPlayers twoPlayers = TwoPlayers.getInstance();
        player1 = twoPlayers.getPlayer1();
        player2 = twoPlayers.getPlayer2();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        imgViews = new ArrayList<>(Arrays.asList(img1, img2, img3, img4, img5, img6, img7, img8, img9));

        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        resetImageViews();
        winnerText.setText("Tic-Tac-Toe");
        counter = 0;
    }

    private void resetImageViews() {
        for (ImageView imgView : imgViews) {
            imgView.setImage(null);
        }
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();

            counter++;
            if(counter == 9){
                winnerText.setText("Draw!");
                return;
            }

        });
    }

    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            ((ImageView) button.getChildrenUnmodifiable().get(0)).setImage(player1.getAvatarImg());
            playerTurn = 1;
            winnerText.setText(player2.getUsername() + "'s Turn");
        } else{
//            button.setText("O");
            ((ImageView) button.getChildrenUnmodifiable().get(0)).setImage(player2.getAvatarImg());
            playerTurn = 0;
            winnerText.setText(player1.getUsername() + "'s Turn");
        }
    }

    private String getGameSymbolString(Button button) {
        ObservableList<Node> children = button.getChildrenUnmodifiable();

        Image gameSymbolImg = ((ImageView) children.get(0)).getImage();

        if (gameSymbolImg == null) {
            return "";
        }

        String gameSymbolImgUrl = gameSymbolImg.getUrl();

        if (player1.compareImageUrl(gameSymbolImgUrl)) {
            return player1.getGameSymbolText();
        } else {
            return player2.getGameSymbolText();
        }

    }

    public void checkIfGameIsOver(){
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> getGameSymbolString(button1) + getGameSymbolString(button2) + getGameSymbolString(button3);
                case 1 -> getGameSymbolString(button4) + getGameSymbolString(button5) + getGameSymbolString(button6);
                case 2 -> getGameSymbolString(button7) + getGameSymbolString(button8) + getGameSymbolString(button9);
                case 3 -> getGameSymbolString(button1) + getGameSymbolString(button5) + getGameSymbolString(button9);
                case 4 -> getGameSymbolString(button3) + getGameSymbolString(button5) + getGameSymbolString(button7);
                case 5 -> getGameSymbolString(button1) + getGameSymbolString(button4) + getGameSymbolString(button7);
                case 6 -> getGameSymbolString(button2) + getGameSymbolString(button5) + getGameSymbolString(button8);
                case 7 -> getGameSymbolString(button3) + getGameSymbolString(button6) + getGameSymbolString(button9);
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                winnerText.setText(player1.getUsername() + " won!");
            }

            //O winner
            else if (line.equals("OOO")) {
                winnerText.setText(player2.getUsername() + " won!");
            }
        }
    }

    @FXML
    void backBtn2(ActionEvent event) throws IOException {
        TwoPlayers.resetPlayers();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("testfx.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }



}


















