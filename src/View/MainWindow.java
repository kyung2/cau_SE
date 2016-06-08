package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * MainWindow
 * this class is the main class for our application
 * It is designed by MainWindow.fxml file in /View/Fxml package
 * and MainWindow.css file in /View/Css package
 * It is related on MainController in /Controller package
 * Created by woojin on 2016-05-16.
 *
 * @author Woonjin Jang
 */
public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/Fxml/MainWindow.fxml"));
        primaryStage.setTitle("Simple Merge");
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add("/View/Css/mainWindow.css");
        primaryStage.getIcons().add(new Image("/View/Image/mainIcon.png"));
        primaryStage.show();

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}