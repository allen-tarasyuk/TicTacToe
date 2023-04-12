package router;

public class Room {
    private int roomID;
    private String roomName;

    private static int id = 1;

    String player1 = "";
    String player2 = "";

    public Room(String roomName) {
        this.roomName = roomName;
        this.roomID = id;
        id += 1;
    }

    public void setPlayer1(String player1Name) {
        this.player1 = player1Name;
    }

    public void setPlayer2(String player2Name) {
        this.player2 = player2Name;
    }
}
