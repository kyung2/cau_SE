package Controller;

import View.AlarmWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;

/**
 * Created by woojin on 2016-05-19.
 */
public class AlarmController {
    @FXML
    private Button yes_button;
    @FXML
    private void clickYesButton(){
        Window window = yes_button.getScene().getWindow();

        boolean clicked_button = true;
        window.setUserData(clicked_button);

        window.hide();
    }
    @FXML
    private void clickNoButton(){
        Window window = yes_button.getScene().getWindow();

        boolean clicked_button = false;
        window.setUserData(clicked_button);

        window.hide();
    }
}
