package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by woojin on 2016-05-19.
 */
abstract public class AbstractAlarmWindow extends Stage {
    public AbstractAlarmWindow(String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/AlarmWindow.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        this.setTitle(title);
        System.out.println(this);
        System.out.println(this.getScene()
        );
        this.setScene(scene);
        this.show();
    }

    abstract public void initLabel();
}