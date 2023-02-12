package testfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class PlayerSelections {

    @FXML
    private Button myButton;

    @FXML
    private Label myLabel;

    @FXML
    private TextField myTextField;

    @FXML
    private ImageView imgView1, imgView2, imgView3, imgView4;

    protected Player selectingPlayer, otherPlayer;

    private Image playerAvatarImg = null;

    protected boolean continueSubmit = false;

    private final double SELECTED_AVATAR_OPACITY = 0.4;

    @FXML
    public void initialize() {

        if (selectingPlayer.getUsername() != "") {
            myTextField.setText(selectingPlayer.getUsername());
        }

        if (selectingPlayer.getAvatarImg() != null) {
            toggleImageViews(selectingPlayer.getAvatarImg());
            playerAvatarImg = selectingPlayer.getAvatarImg();
        }

        if (otherPlayer.getAvatarImg() != null) {
            toggleImageViews(otherPlayer.getAvatarImg());
        }
    }

    @FXML
    public void selectAvatar(MouseEvent event) {
        Image avatarImg = ((ImageView) event.getSource()).getImage();
        String avatarImgUrl = avatarImg.getUrl();

        if (otherPlayer.getAvatarImg() != null && Objects.equals(otherPlayer.getAvatarImg().getUrl(), avatarImgUrl)) {
            return;
        }

        if (playerAvatarImg != null) {
            toggleImageViews(playerAvatarImg);
        }

        playerAvatarImg = avatarImg;
        toggleImageViews(playerAvatarImg);
    }

    @FXML
    public void submit(ActionEvent event) throws IOException {

        if(myTextField.getText() == ""){
            myLabel.setText("Please Enter Name");
            return;
        }

        if (playerAvatarImg == null) {
            return;
        }

        continueSubmit = true;

        selectingPlayer.setUsername(myTextField.getText());
        selectingPlayer.setAvatarImg(playerAvatarImg);
    }

    private void toggleImageViews(Image selectedAvatar) {
        String selectedAvatarUrl = selectedAvatar.getUrl();

        if (Objects.equals(selectedAvatarUrl, imgView1.getImage().getUrl())) {
            if (imgView1.getOpacity() == SELECTED_AVATAR_OPACITY) {
                imgView1.setOpacity(1);
            } else {
                imgView1.setOpacity(SELECTED_AVATAR_OPACITY);
            }
        } else if (Objects.equals(selectedAvatarUrl, imgView2.getImage().getUrl())) {
            if (imgView2.getOpacity() == SELECTED_AVATAR_OPACITY) {
                imgView2.setOpacity(1);
            } else {
                imgView2.setOpacity(SELECTED_AVATAR_OPACITY);
            }
        } else if (Objects.equals(selectedAvatarUrl, imgView3.getImage().getUrl())) {
            if (imgView3.getOpacity() == SELECTED_AVATAR_OPACITY) {
                imgView3.setOpacity(1);
            } else {
                imgView3.setOpacity(SELECTED_AVATAR_OPACITY);
            }
        } else if (Objects.equals(selectedAvatarUrl, imgView4.getImage().getUrl())) {
            if (imgView4.getOpacity() == SELECTED_AVATAR_OPACITY) {
                imgView4.setOpacity(1);
            } else {
                imgView4.setOpacity(SELECTED_AVATAR_OPACITY);
            }
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
