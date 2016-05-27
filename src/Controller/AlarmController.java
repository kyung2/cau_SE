package Controller;

import View.AlarmWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by woojin on 2016-05-19.
 */
public class AlarmController {
    @FXML
    private Button yes_button;
    @FXML
    private void yesButtonOnAction(){
        Stage stage = (Stage)yes_button.getScene().getWindow();

        boolean clicked_button = true;
        stage.setUserData(clicked_button);

        stage.close();
    }
    @FXML
    private void noButtonOnAction(){
        Stage stage = (Stage)yes_button.getScene().getWindow();

        boolean clicked_button = false;
        stage.setUserData(clicked_button);

        stage.close();
    }
}
