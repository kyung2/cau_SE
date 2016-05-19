package Controller;

import View.AlarmWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojin on 2016-05-15.
 * FilePanel Controller.
 * has Load, Save, Edit button action.
 * in Load button action, make file chooser.
 */
public class SplitFilePaneController implements Initializable {
    @FXML
    private TextArea left_text_area;
    @FXML
    private TextArea right_text_area;
    @FXML
    private Button left_load_button;
    @FXML
    private Button left_edit_button;
    @FXML
    private Button left_save_button;
    @FXML
    private Button right_load_button;
    @FXML
    private Button right_edit_button;
    @FXML
    private Button right_save_button;

    /*
    * 기본적으로 로드 가능. 수정 불가, 저장 불가
    * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        left_load_button.setDisable(false);
        right_load_button.setDisable(false);
        left_edit_button.setDisable(true);
        left_save_button.setDisable(false);// 원래 비 활성화여야 하지만 테스트를 위해 임시로 활성화로 바꿔놈
        right_edit_button.setDisable(true);
        right_save_button.setDisable(true);
    }
    /*
    * 파일을 읽어서 내용이 있을 경우 edit 버튼 활성화
    * load 버튼을 누를 경우 파일을 읽어서 text area 에 파일 내용을 적는다.
    * 이미 파일이 있을 경우는 덮어쓰기
    * */
    @FXML
    private void clickLeftLoadButton(){
        fileChooser();

        left_edit_button.setDisable(false);
        System.out.println("Click");
    }
    @FXML
    private void clickRightLoadButton(){
        fileChooser();

        right_edit_button.setDisable(false);
        System.out.println("Click");
    }
    /*
    * 기본은 text area 수정 불가
    * edit 버튼을 클릭하면 수정 가능하게 바꿈
    * edit 이 가능할 때는 edit 와 save 버튼 말고는 모두 비활성화
    * 수정 가능한 상황에서 한 번 더 버튼을 누르면 다시 수정 불가
    * 그 후 비활성화 된 load 버튼을 활성화로
    * */
    @FXML
    private void clickLeftEditButton() {
        boolean edit_flag = left_text_area.isEditable();
        if(edit_flag){
            //수정이 가능할 때 - 누르면 수정이 불가능해짐. 로드는 가능해짐. 파일은 저장?
            left_text_area.setEditable(false);
            left_load_button.setDisable(false);
        }
        else{
            //수정이 불가능 할 때 = 누르면 수정이 가능. 로드 불가능, 저장 가능
            left_text_area.setEditable(true);
            left_load_button.setDisable(true);
            left_save_button.setDisable(false);
        }
    }
    @FXML
    private void clickRightEditButton() {
        boolean edit_flag = right_text_area.isEditable();
        if(edit_flag){
            //수정이 가능할 때 - 누르면 수정이 불가능해짐, 로드 불가능, 파일은 저장?
            right_text_area.setEditable(false);
            right_load_button.setDisable(false);
        }
        else{
            //수정이 불가능 할 때 - 누르면 수정 가능, 로드 불가능, 저장 가능
            right_text_area.setEditable(true);
            right_load_button.setDisable(true);
            right_save_button.setDisable(false);
        }
    }
    /*
    * edit 버튼을 눌러서 수정이 가해진 상황 일 경우 save 버튼이 활성화 된다.
    * 수정 사항을 저장한 후에는 로드 가능, 수정 가늗, 저장 불가능으로 된다.
    * */
    @FXML
    private void clickLeftSaveButton(){
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            left_load_button.setDisable(false);
            left_save_button.setDisable(true);
        }
    }
    @FXML
    private void clickRightSaveButton(){
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            right_load_button.setDisable(false);
            right_save_button.setDisable(true);
        }
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
