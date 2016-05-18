package Controller;

import View.OpenFileWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by woojin on 2016-05-16.
 */
public class OpenFileWindowController {
    @FXML
    private Button right_file_find_button;
    @FXML
    private void clickRightFileFindButton(){
        right_file_find_button.setText("Click");
    }
    @FXML
    private Button left_file_find_button;
    @FXML
    private void clickLeftFileFindButton(){
        left_file_find_button.setText("Click");
    }
    @FXML
    private Button ok_button;
    @FXML
    private void clickOkButton(){
        ok_button.setText("Click");
    }
    @FXML
    private Button cancel_button;
    @FXML
    private void clickCancelButton(){
        cancel_button.setText("Click");
    }
}
