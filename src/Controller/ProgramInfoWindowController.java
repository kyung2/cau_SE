package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Created by hyunkyung on 2016-05-20.
 */
public class ProgramInfoWindowController {
    @FXML
    private AnchorPane info_window;
    @FXML
    private void onAction_ok_btn(){
        info_window.getScene().getWindow().hide();
    }
}
