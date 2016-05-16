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
public class TabPaneController {
    @FXML
    private Button left_load_button;
    @FXML
    private void clickLeftLoadButton(){
        fileChooser();
        System.out.println("Click");
    }
    @FXML
    private Button left_edit_button;
    @FXML
    private void clickLeftEditButton() { left_edit_button.setText("Click"); }
    @FXML
    private Button left_save_button;
    @FXML
    private void clickLeftSaveButton(){
        left_save_button.setText("Click");
    }
    @FXML
    private Button right_load_button;
    @FXML
    private void clickRightLoadButton(){
        fileChooser();
        System.out.println("Click");
    }
    @FXML
    private Button right_edit_button;
    @FXML
    private void clickRightEditButton() { right_edit_button.setText("Click"); }
    @FXML
    private Button right_save_button;
    @FXML
    private void clickRightSaveButton(){
        right_save_button.setText("Click");
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
