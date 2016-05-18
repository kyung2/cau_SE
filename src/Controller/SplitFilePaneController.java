package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import sun.plugin.javascript.navig.Anchor;

import java.io.File;

/**
 * Created by woojin on 2016-05-15.
 * FilePanel Controller.
 * has Load, Save, Edit button action.
 * in Load button action, make file chooser.
 */
public class SplitFilePaneController {
    @FXML
    private TextArea left_text_area;
    @FXML
    private TextArea right_text_area;
    /*
    * 기본은 save 버튼 비활성화
    * edit 버튼을 눌러서 수정이 가해진 상황 일 경우 save 버튼이 활성화 된다.
    * 수정 사항을 저장한 후에는 다시 비활성화 된다.
    * */
    @FXML
    private void clickLeftLoadButton(){
        fileChooser();
        System.out.println("Click");
    }
    @FXML
    private void clickRightLoadButton(){
        fileChooser();
        System.out.println("Click");
    }
    /*
    * 기본은 text area 수정 불가
    * edit 버튼을 클릭하면 수정 가능하게 바꿈
    * 수정 가능한 상황에서 한 번 더 버튼을 누르면 다시 수정 불가.
    * */
    @FXML
    private void clickLeftEditButton() {
        boolean edit_flag = left_text_area.isEditable();
        if(edit_flag){
            //수정이 가능할 때 - 누르면 수정이 불가능해짐. 파일은 저장?
            left_text_area.setEditable(false);
        }
        else{
            left_text_area.setEditable(true);
        }
    }
    @FXML
    private void clickRightEditButton() {
        boolean edit_flag = right_text_area.isEditable();
        if(edit_flag){
            //수정이 가능할 때 - 누르면 수정이 불가능해짐. 파일은 저장?
            right_text_area.setEditable(false);
        }
        else{
            right_text_area.setEditable(true);
        }
    }
    @FXML
    private void clickLeftSaveButton(){
        System.out.println("Click");
    }
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
