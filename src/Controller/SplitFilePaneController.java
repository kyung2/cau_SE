package Controller;

import View.AlarmWindow;
import View.MyListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by woojin on 2016-05-15.
 * FilePanel Controller.
 * has Load, Save, Edit button action.
 * in Load button action, make file chooser.
 */
public class SplitFilePaneController implements Initializable {
    @FXML
    private TextArea left_text_area, right_text_area;
    @FXML
    private Button left_load_button,left_edit_button,left_save_button,
                    right_load_button,right_save_button,right_edit_button;
    @FXML
    private MyListView left_text_list, right_text_list;
    @FXML
    private SplitPane split_pane;
    @FXML
    private Button compare_button;

    private boolean have_right_file, have_left_file;

    /*
    * 기본적으로
    * file pane 의 버튼은 로드 활성화. 수정 비활성화, 저장 비활성화
    * toolbar 의 버튼은 모두 비활성화
    * */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClickableButtons("right","true","false","false");
        setClickableButtons("left","true","false","false");
        //practice code
        ArrayList<ArrayList<String >> groups = new ArrayList<ArrayList<String>>();
        ArrayList<String> onelines = new ArrayList<String>();
        onelines.add("click\n");
        onelines.add("add\n");
        ArrayList<String> twolines = new ArrayList<String>();
        twolines.add("click2\n");
        twolines.add("add2\n");
        groups.add(onelines);
        groups.add(twolines);
        groups.add(onelines);
        //practice code
        left_text_list.setBlocks(groups);
        left_text_list.setDisable(false);
        left_text_list.setVisible(false);
        //right_text_list.setItems(lines);
        right_text_list.setDisable(true);

        left_text_area.setEditable(false);
        right_text_area.setEditable(false);
        compare_button = null;
        have_left_file = false;
        have_right_file = false;
    }
    /*
    * compare 버튼을 할당되있지 않을 경우 할당
    * 파일을 읽어서 내용이 있을 경우 edit 버튼 활성화
    * load 버튼을 누를 경우 파일을 읽어서 text area 에 파일 내용을 적는다.
    * 이미 파일이 있을 경우는 덮어쓰기
    * */
    @FXML
    private void leftLoadButtonOnAction(){
        if(compare_button == null) {
            compare_button = (Button)((ToolBar)((BorderPane)((BorderPane)split_pane.getScene().getRoot()).getCenter()).getTop()).getItems().get(9);
        }

        fileChooser(left_load_button);
        if(have_left_file) setClickableButtons("left","true","true","false");
        checkCompareButton();
    }
    @FXML
    private void rightLoadButtonOnAction(){
        if(compare_button == null) {
            compare_button = (Button)((ToolBar)((BorderPane)((BorderPane)split_pane.getScene().getRoot()).getCenter()).getTop()).getItems().get(9);
        }

        fileChooser(right_load_button);
        if(have_right_file) setClickableButtons("right","true","true","false");
        checkCompareButton();
    }
    /*
    * 기본은 text area 수정 불가
    * edit 버튼을 클릭하면 수정 가능하게 바꿈
    * edit 이 가능할 때는 edit 와 save 버튼 말고는 모두 비활성화
    * 수정 가능한 상황에서 한 번 더 버튼을 누르면 다시 수정 불가
    * 그 후 비활성화 된 load 버튼을 활성화로
    * */
    @FXML
    private void leftEditButtonOnAction() {
        boolean edit_flag = left_text_area.isEditable();
        if(edit_flag){
            /*
            * 수정이 가능할 때 - 누르면 수정이 불가능해짐. 로드는 가능해짐. 모델에 있는 정보를 바꿈,
            * 두 file pane 이 모두 수정 불가능 == save 가 비활성화 되있으면 compare 가능
            */
            left_text_area.setEditable(false);
            checkCompareButton();
            setClickableButtons("left","true",null,null);
        }
        else{
            //수정이 불가능 할 때 = 누르면 수정이 가능. 로드 불가능, 저장 가능, compare 불가능
            left_text_area.setEditable(true);
            disableAllButtonInToolBar();
            setClickableButtons("left","false",null,"true");
        }
    }
    @FXML
    private void rightEditButtonOnAction() {
        boolean edit_flag = right_text_area.isEditable();
        if(edit_flag){
            /*
            * 수정이 가능할 때 - 누르면 수정이 불가능해짐. 로드는 가능해짐. 모델에 있는 정보를 바꿈,
            * 두 file pane 이 모두 수정 불가능 == save 가 비활성화 되있으면 compare 가능
            */
            right_text_area.setEditable(false);
            checkCompareButton();
            setClickableButtons("right","true",null,null);
        }
        else{
            //수정이 불가능 할 때 - 누르면 수정 가능, 로드 불가능, 저장 가능
            right_text_area.setEditable(true);
            disableAllButtonInToolBar();
            setClickableButtons("right","false",null,"true");
        }
    }
    /*
    * edit 버튼을 눌러서 수정이 가해진 상황 일 경우 save 버튼이 활성화 된다.
    * 수정 사항을 저장한 후에는 로드 가능, 수정 가늗, 저장 불가능으로 된다.
    * */
    @FXML
    private void leftSaveButtonOnAction(){
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            left_text_area.setEditable(false);
            checkCompareButton();
            setClickableButtons("left","true",null,"false");
        }
    }
    @FXML
    private void rightSaveButtonOnAction(){
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            right_text_area.setEditable(false);
            checkCompareButton();
            setClickableButtons("right","true",null,"false");
        }
    }

    private void fileChooser(Button position){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        File selectedFile = fileChooser.showOpenDialog(null);
        try{
            String path = selectedFile.getAbsolutePath();

            if(position.getId().equals("left_load_button")) {
                have_left_file = true;

            }
            else {
                have_right_file = true;
            }
        }catch (Exception NullPointException){
            System.out.println("No Select FIle");
        }
    }

    /*
    * file pane 버튼의 able 과 disable 을 해준다.
    * position 에 left 와 right 를 통해서 위치를 선택
    * 각각 load, edit, save에 true || false 를 통해서 able 과 disable 을 한다.
    * null 일 경우 그 버튼은 현상 유지
    * */
    private void setClickableButtons(String position, String load, String edit, String save){
        boolean f_load, f_edit, f_save;
        if(load == "true") f_load = true;
        else f_load = false;

        if(edit == "true") f_edit = true;
        else f_edit = false;

        if(save == "true") f_save = true;
        else f_save = false;

        if(position == "left"){
            if(load != null) left_load_button.setDisable(!f_load);
            if(edit != null) left_edit_button.setDisable(!f_edit);
            if(save != null) left_save_button.setDisable(!f_save);
        }
        else{
            if(load != null) right_load_button.setDisable(!f_load);
            if(edit != null) right_edit_button.setDisable(!f_edit);
            if(save != null) right_save_button.setDisable(!f_save);
        }
    }
    /*
    * compare 버튼이 활성화 되도 되는지 체크
    * edit 버튼이 눌렸을 때 두 text area 가 수정 불가능 한 경우 활성화
    * save 버튼이 눌렸을 때 두 text area 가 수정 불가능 한 경우 활성화
    * */
    private void checkCompareButton(){
        if(have_right_file&&have_left_file) {
            if (!right_text_area.isEditable() && !left_text_area.isEditable()) {
                compare_button.setDisable(false);
            }
        }
    }
    /*
    * Toolbar 에 있는 모든 버튼을 비활성화 시킨다.
    * file 을 수정할 경우 compare 를 다시 해야하기에 모든 버튼을 비활성화
    * */
    private void disableAllButtonInToolBar(){
        ToolBar toolBar = (ToolBar)compare_button.getParent().getParent();

        for(int i=0, n=toolBar.getItems().size(); i<n; i++){
            ((Button)toolBar.getItems().get(i)).setDisable(true);
        }
    }
}
