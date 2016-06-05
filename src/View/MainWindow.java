package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * MainWindow
 * Created by hyunkyung on 2016-06-01.
 * @author Hyunkyung Choi
 */

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        //fxmlLoader.setController("Controller/Controller.java");

        Parent root = FXMLLoader.load(getClass().getResource("/View/Fxml/MainWindow.fxml"));
        primaryStage.setTitle("Simple Merge");
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add("/View/Css/mainWindow.css");
        primaryStage.getIcons().add(new Image("/View/Image/mainIcon.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}