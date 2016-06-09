package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for programInformationWindow
 * Created by woojin on 2016-05-16.
 * @author woojin
 */
public class ProgramInfoWindowController implements programInfoInterface{
    @FXML
    private AnchorPane info_window;
    @FXML
    /**
     * okaybutton close the info tab*/
    public void okButtonOnAction(){
        ((Stage)info_window.getScene().getWindow()).close();
    }
}
