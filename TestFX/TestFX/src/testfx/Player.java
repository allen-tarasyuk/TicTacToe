package testfx;

import javafx.scene.image.Image;

import java.util.Objects;

public class Player {
    private String username;

    private Image avatarImg;

    private String gameSymbolText;

    public Player() {
        this.username = "";
        this.avatarImg = null;
        this.gameSymbolText = "";
    }

    public Player(String username, Image avatarImg) {
        this.username = username;
        this.avatarImg = avatarImg;
    }

    public boolean compareImageUrl(String otherImgUrl) {
        return Objects.equals(otherImgUrl, this.avatarImg.getUrl());
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

    public String getGameSymbolText() {
        return gameSymbolText;
    }

    public void setGameSymbolText(String gameSymbolText) {
        this.gameSymbolText = gameSymbolText;
    }
}
