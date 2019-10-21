/*
 * Course: CS2852
 * Spring 2018-2019
 * File header contains class Controller
 * Name: crossj
 * Created 3/4/2019
 */

package crossj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * CS2852 2019
 * Class purpose: LabOne, runs and draws .dot files with dots and lines
 *
 * @author crossj
 * @version created on 3/4/2019 at 9:33 PM
 */
public class LabNine extends Application {

    /**
     * Starts the application
     * @param primaryStage the stage that is displays application
     * @throws Exception if not able to run
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));
        primaryStage.setTitle("Auto Complete");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Launches the Application
     * @param args used for launch
     */
    public static void main(String[] args) {
        launch(args);
    }
}
