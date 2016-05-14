package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button compare_button;
    @FXML
    private void clickCompareButton() { compare_button.setText("Click"); }
    @FXML
    private Button copy_to_left_button;
    @FXML
    private void clickCopyToLeftButton() { copy_to_left_button.setText("Click"); }
    @FXML
    private Button copy_to_right_button;
    @FXML
    private void clickCopyToRightButton() { copy_to_right_button.setText("Click"); }
    @FXML
    private Button copy_to_right_all_button;
    @FXML
    private void clickCopyToRightAllButton() {
        copy_to_right_all_button.setText("Click");
    }
    @FXML
    private Button copy_to_left_all_button;
    @FXML
    private void clickCopyToLeftAllButton(){
        copy_to_left_all_button.setText("Click");
    }
}
