/********************************************************************
 * To compile the code with a Makefile, you'll need to 
 * navigate to the directory where the Makefile is located
 * and run the following command: 'Make all', this will compile
 * the program. To run the file, making sure you have the Makefile
 * in the same directory as the cmd will be written. The cmd to
 * run is: 'make run'.
 ********************************************************************/
package testfx; // package

import java.net.URL; // System import
import java.net.URLClassLoader;
import java.util.spi.ResourceBundleControlProvider;
import java.lang.NullPointerException; // System import
import javafx.application.Application; //javafx import
import javafx.event.ActionEvent; //javafx import
import javafx.event.EventHandler; //javafx import
import javafx.fxml.FXMLLoader; //javafx import
import javafx.scene.Parent;//javafx import
import javafx.scene.Scene; //javafx import
import javafx.stage.Stage;//javafx import


/********************************************************************
 * This is the 'main class' of the program. It is the entry point
 * for the program.
 ********************************************************************/
public class Main extends Application  {

    /********************************************************************
     * This is the 'main method' of the program. It is the entry point
     * for the program.
     ********************************************************************/
    public static void main(String[] args) {
        launch(args);
    }

    
    @Override
    /**************************************
     * Function to initialize the program.
     * @param primaryStage
     * @throws Exception
     **************************************/
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("TestFX");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("file:///testfx.fxml"))));
        primaryStage.show();

        System.out.println(ResourceBundleControlProvider.class);
        System.out.println(ResourceBundleControlProvider.class.getClassLoader());
        System.out.println(ResourceBundleControlProvider.class.getClassLoader().getParent());
        System.out.println(ResourceBundleControlProvider.class.getClassLoader().getParent().getParent());
    }

}

