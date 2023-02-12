package testfx;

public class Player {
    private String username;

    private int avatarNum;

    public Player() {
        this.username = "";
        this.avatarNum = -1;
    }

    public Player(String username, int avatarNum) {
        this.username = username;
        this.avatarNum = avatarNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAvatarNum() {
        return avatarNum;
    }

    public void setAvatarNum(int avatarNum) {
        this.avatarNum = avatarNum;
    }
}
