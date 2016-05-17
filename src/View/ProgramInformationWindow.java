package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by woojin on 2016-05-17.
 */
public class ProgramInformationWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/ProgramInfoWindow.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("ProgramInformationWindow");
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
