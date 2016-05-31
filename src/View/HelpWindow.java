package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * Created by woojin on 2016-05-18.
 */
public class HelpWindow {

    public void HelpWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/HelpWindow.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Help");
            stage.getIcons().add(new Image("/View/Image/mainIcon.png"));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
