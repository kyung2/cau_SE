package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by hyunkyung on 2016-05-20.
 */
public class ProgramInfoWindowController {
    @FXML
    private Button program_info_ok_button;
    @FXML
    private void onAction_ok_btn(){
        program_info_ok_button.setText("Click");
    }
}
