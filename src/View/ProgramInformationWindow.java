package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

/**
 * About Program info simple!
 * This class has information about program name, author, licence and date that finished to make
 * It is designed by ProgramInfoWindow.fxml file in /View/Fxml package
 * It is related on ProgramInfoWindowController in /Controller package
 * Created by woojin on 2016-05-16.
 * @author Woojin Jang and Hyunkyung Choi
 */
public class ProgramInformationWindow {

    public void PrograminformationWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/ProgramInfoWindow.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.setTitle("About Program");
            stage.getIcons().add(new Image("/View/Image/mainIcon.png"));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
