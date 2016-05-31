package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by hyunkyung on 2016-05-20.
 */
public class ProgramInfoWindowController implements programInfoInterface{
    @FXML
    private AnchorPane info_window;
    @FXML
    public void okButtonOnAction(){
        ((Stage)info_window.getScene().getWindow()).close();
    }
}
