package testfx;

import javafx.scene.image.Image;

public class Player {
    private String username;

    private Image avatarImg;

    public Player() {
        this.username = "";
        this.avatarImg = null;
    }

    public Player(String username, Image avatarImg) {
        this.username = username;
        this.avatarImg = avatarImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Image getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(Image avatarImg) {
        this.avatarImg = avatarImg;
    }
}
