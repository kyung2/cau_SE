package Controller;

import Model.Model;
import Model.ModelRealize;
import View.AlarmWindow;
import View.MyListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
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

    private int tab_num;
    /*
    * 기본적으로
    * file pane 의 버튼은 로드 활성화. 수정 비활성화, 저장 비활성화
    * toolbar 의 버튼은 모두 비활성화
    * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tab_num = -1;

        setClickableButtons("right","true","false","false");
        setClickableButtons("left","true","false","false");

        left_text_list.setDisable(true);
        left_text_list.setVisible(false);
        right_text_list.setDisable(true);
        right_text_list.setVisible(false);

        left_text_area.setEditable(false);
        right_text_area.setEditable(false);

        compare_button = null;
    }
    /*
    * compare 버튼을 할당되있지 않을 경우 할당
    * 파일을 읽어서 내용이 있을 경우 edit 버튼 활성화
    * load 버튼을 누를 경우 파일을 읽어서 text area 에 파일 내용을 적는다.
    * text area 를 visible 하게 바꾼다.
    * 이미 파일이 있을 경우는 덮어쓰기
    * */
    @FXML
    private void leftLoadButtonOnAction() {
        checkTabNum();
        String path = loadFileChooser(left_load_button);
        Model model = ModelRealize.getInstance();
        if(path != null){
            try {
                model.readTextOuter(tab_num, path, 0);
                left_text_area.setVisible(true);
                left_text_area.setText(arrayListToString(model.getText(tab_num,0)));
                setClickableButtons("left","true","true","false");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        invisibleListViewVisibleTextArea();
        checkCompareButton();
    }
    @FXML
    private void rightLoadButtonOnAction(){
        checkTabNum();
        String path = loadFileChooser(right_load_button);
        if(path != null){
            Model model = ModelRealize.getInstance();
            try {
                model.readTextOuter(tab_num, path, 1);
                right_text_area.setVisible(true);
                right_text_area.setText(arrayListToString(model.getText(tab_num,1)));
                setClickableButtons("right","true","true","false");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        invisibleListViewVisibleTextArea();
        checkCompareButton();
    }
    /*
    * 기본은 text area 수정 불가
    * edit 버튼을 클릭하면 수정 가능하게 바꾸고 text area 를 visible 하게 바꾼다.
    * edit 이 가능할 때는 edit 와 save 버튼 말고는 모두 비활성화
    * 수정 가능한 상황에서 한 번 더 버튼을 누르면 다시 수정 불가
    * 그 후 비활성화 된 load 버튼을 활성화로
    * */
    @FXML
    private void leftEditButtonOnAction() {
        boolean edit_flag = left_text_area.isEditable();
        checkTabNum();
        if(edit_flag){
            /*
            * 수정이 가능할 때 - 누르면 수정이 불가능해짐. 로드는 가능해짐. 모델에 있는 정보를 바꿈,
            * 두 file pane 이 모두 수정 불가능 == save 가 비활성화 되있으면 compare 가능
            */
            left_text_area.setEditable(false);
            try {
                Model model = ModelRealize.getInstance();
                model.setText(tab_num, stringToArrayList(left_text_area.getText()), 0);
            }catch (Exception e){
                e.printStackTrace();
            }
            checkCompareButton();
            setClickableButtons("left","true",null,null);
            // hk - edit 비활성화 되시 원상태로 복귀
            left_edit_button.setStyle("-fx-background-color:transparent");
        }
        else{
            //수정이 불가능 할 때 = 누르면 수정이 가능. 로드 불가능, 저장 가능, compare 불가능
            left_text_area.setEditable(true);
            invisibleListViewVisibleTextArea();
            disableAllButtonInToolBar();
            setClickableButtons("left","false",null,"true");
            // hk - edit이 활성화 될 시 하이라이팅 (이미지바꿀려면 저 setStyle메소드 이용하면됨)
            left_edit_button.setStyle("-fx-background-color: #a6a6a6");
        }
    }
    @FXML
    private void rightEditButtonOnAction() {
        boolean edit_flag = right_text_area.isEditable();
        checkTabNum();
        if(edit_flag){
            /*
            * 수정이 가능할 때 - 누르면 수정이 불가능해짐. 로드는 가능해짐. 모델에 있는 정보를 바꿈,
            * 두 file pane 이 모두 수정 불가능 == save 가 비활성화 되있으면 compare 가능
            */
            try {
                Model model = ModelRealize.getInstance();
                model.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
            }catch (Exception e){
                e.printStackTrace();
            }
            right_text_area.setEditable(false);
            checkCompareButton();
            setClickableButtons("right","true",null,null);
            // hk - edit 비활성화 되시 원상태로 복귀
            right_edit_button.setStyle("-fx-background-color:transparent");
        }
        else{
            //수정이 불가능 할 때 - 누르면 수정 가능, 로드 불가능, 저장 가능
            right_text_area.setEditable(true);
            invisibleListViewVisibleTextArea();
            disableAllButtonInToolBar();
            setClickableButtons("right","false",null,"true");
            // hk - edit이 활성화 될 시 하이라이팅 (이미지바꿀려면 저 setStyle메소드 이용하면됨)
            right_edit_button.setStyle("-fx-background-color: #a6a6a6");
        }
    }
    /*
    * edit 버튼을 눌러서 수정이 가해진 상황 일 경우 save 버튼이 활성화 된다.
    * 수정 사항을 저장한 후에는 로드 가능, 수정 가늗, 저장 불가능으로 된다.
    * */
    @FXML
    private void leftSaveButtonOnAction(){
        checkTabNum();
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            try {
                Model model = ModelRealize.getInstance();
                model.setText(tab_num, stringToArrayList(left_text_area.getText()), 0);
                model.writeTextOuter(tab_num, 0);
                left_text_area.setEditable(false);
                checkCompareButton();
                setClickableButtons("left", "true", null, "false");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void rightSaveButtonOnAction(){
        checkTabNum();
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            try {
                Model model = ModelRealize.getInstance();
                model.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
                model.writeTextOuter(tab_num, 1);
                right_text_area.setEditable(false);
                checkCompareButton();
                setClickableButtons("right", "true", null, "false");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
    *  file chooser 를 열어서 파일의 path 를 가져온다.
    *  tab num 이 -1 즉 초기값일 경우 tab num 을 할당해준다.
    *  모델 객체를 받아온 뒤 해당하는 model 에 absoulte path를 넘겨주어 파일 입출력을 한다.
    * */
    private String loadFileChooser(Button position){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        File selectedFile = fileChooser.showOpenDialog(null);
        checkTabNum();
        String path = null;
        try{
            path = selectedFile.getAbsolutePath();
            Model model = ModelRealize.getInstance();

            return path;
        }catch (Exception NullPointException){
            System.out.println("No Select FIle");
        }
        return path;
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

        if(edit == "true") {
            f_edit = true;

        }
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
        if(compare_button == null) {
            compare_button = (Button)((ToolBar)((BorderPane)((BorderPane)split_pane.getScene().getRoot()).getCenter()).getTop()).getItems().get(9);
        }
        Model model = ModelRealize.getInstance();
        try{
            if(model.isOpen(tab_num,0) && model.isOpen(tab_num,1)) {
                if (!right_text_area.isEditable() && !left_text_area.isEditable()) {
                    compare_button.setDisable(false);
                }
            }
        }catch (IllegalAccessException e){
                e.printStackTrace();
        }
    }
    /*
    * Tab num 값을 확인한다.
    * -1 일 경우 초기값이므로 현재 tab 의 번호를 넣어준다.
    * */
    private void checkTabNum(){
        if(tab_num == -1){
            tab_num = (int)getSelectedTab().getUserData();
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
    /*
    * TabPane 에서 현재 선택 된 tab 을 가져온다.
    * */
    private Tab getSelectedTab(){
        Tab tab = null;
        TabPane tabPane = (TabPane)split_pane.getParent().getParent();
        for(int i=0,n=tabPane.getTabs().size(); i<n;i++){
            if(tabPane.getTabs().get(i).isSelected()){
                tab = tabPane.getTabs().get(i);
            }
        }
        return tab;
    }
    /*
    * ArrayList 로 들어온 문자를 \n 을 붙여 String 하나로 만든다.
    * */
    private String arrayListToString(ArrayList<String> arrayList){
        String s = new String();
        for (String s1 : arrayList) {
            s += s1 + "\n";
        }
        return s;
    }
    /*
    * String 을 받아와서 \n 로 split 한 후 ArrayList 에 저장한다.
    * */
    private ArrayList<String> stringToArrayList(String s){
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] strings = s.split("\n");
        for(int i = 0, n = strings.length; i < n; i++){
            arrayList.add(strings[i]);
        }
        return arrayList;
    }
    /*
    * 모든 List view 를 invisible 하게
    * 모든 Text view 를 visible 하게 바꾼다.
    * */
    private void invisibleListViewVisibleTextArea(){
        right_text_area.setVisible(true);
        right_text_list.setVisible(false);
        left_text_area.setVisible(true);
        left_text_list.setVisible(false);
    }
}
