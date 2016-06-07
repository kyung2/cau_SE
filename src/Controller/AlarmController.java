package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Alarm window's controller
 * If click yes button it has true value in user data
 * If click no button it has false value in user data
 * Created by woojin on 2016-05-19.
 * @author woojin Jang
 */
public class AlarmController implements AlarmInterface {
    @FXML
    private AnchorPane alarm_anchor_pane;
    @FXML
    /*
    * do click yes button
    * insert true value to stage's user data
    * */
    public void yesButtonOnAction(){
        Stage stage = (Stage)alarm_anchor_pane.getScene().getWindow();

        boolean clicked_button = true;
        stage.setUserData(clicked_button);

        stage.close();
    }
    @FXML
    /*
    * do click no button
    * insert false value to stage's user data
    * */
    public void noButtonOnAction(){
        Stage stage = (Stage)alarm_anchor_pane.getScene().getWindow();

        boolean clicked_button = false;
        stage.setUserData(clicked_button);

        stage.close();
    }
}
