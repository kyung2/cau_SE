package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for programInformationWindow
 *@author woojin
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
