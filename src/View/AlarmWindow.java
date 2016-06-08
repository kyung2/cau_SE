package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Create Alarm window and show this to user.
 * this have buttons that action "Yes" and "NO"
 * this class is designed with AlarmWindow.fxml file in /View/Fxml package
 * this class is related on AlarmController class in /Controller package
 * Created by woojin on 2016-05-19.
 *
 * @author Woonjin Jang
 */
public class AlarmWindow extends Stage {
    /**
     * Constructure
     *
     * @param title Title for alarm
     * @param label Content for alarm,
     */
    public AlarmWindow(String title, String label) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/AlarmWindow.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);

        this.setTitle(title);
        this.setScene(scene);
        getLabel().setText(label);
    }

    /* get Label node for write content */
    private Label getLabel(){
        return (Label)((AnchorPane)this.getScene().getRoot()).getChildren().get(0);
    }

}