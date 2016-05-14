package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by woojin on 2016-05-15.
 * FilePanel Controller.
 * has Load, Save, Edit button action.
 * in Load button action, make file chooser.
 */
public class FilePanelController {
    @FXML
    private Button load_button;
    @FXML
    private void clickLoadButton(){
        fileChooser();
        load_button.setText("Click");
    }
    @FXML
    private Button edit_button;
    @FXML
    private void clickEditButton() { edit_button.setText("Click"); }
    @FXML
    private Button save_button;
    @FXML
    private void clickSaveButton(){
        save_button.setText("Click");
    }

    private void fileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        File selectedFile = fileChooser.showOpenDialog(null);
        try{
            System.out.println( selectedFile.getAbsolutePath() );
        }catch (Exception NullPointException){
            System.out.println("No Select FIle");
        }
    }
}
