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
public class SpliteFilePaneController {
    @FXML
    private void clickLeftLoadButton(){
        fileChooser();
        System.out.println("Click");
    }
    @FXML
    private void clickLeftEditButton() { System.out.println("Click"); }
    @FXML
    private void clickLeftSaveButton(){
        System.out.println("Click");
    }
    @FXML
    private void clickRightLoadButton(){
        fileChooser();
        System.out.println("Click");
    }
    @FXML
    private void clickRightEditButton() { System.out.println("Click"); }
    @FXML
    private void clickRightSaveButton(){
        System.out.println("Click");
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
