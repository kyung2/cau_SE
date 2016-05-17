package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by woojin on 2016-05-17.
 */
public class ProgramInfoWindowController {
    @FXML
    private Button program_info_ok_button;
    @FXML
    private void clickProgramInfoOkButton(){
        program_info_ok_button.setText("Click");
    }

}
