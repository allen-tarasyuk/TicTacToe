package testfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application  {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Parent root = FXMLLoader.load(getClass().getResource("testfx.fxml"));

        Scene scene = new Scene(root);

        // scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());




        primaryStage.setScene(scene);
        primaryStage.show();

    }

}