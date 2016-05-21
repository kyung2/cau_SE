package Controller;

import View.HelpWindow;
import View.OpenFileWindow;
import View.ProgramInformationWindow;
import View.SaveFileWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button compare_button, copy_to_left_button,copy_to_right_button, copy_to_left_all_button, copy_to_right_all_button,
            next_difference_button, post_difference_button, first_difference_button, now_difference_button, last_difference_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClickabeButtons("false","false","false","false","false","false","false","false","false","false");
    }
    @FXML
    /*
    * 조건에 따라 달라져야 함.
    * 다른 부분이 하나도 없다면 모든 버튼 비활성화
    * 현재 차이점이 선택되 있지 않다면 다음 차이점과 이전 차이점, 모든 copy 버튼은 비활성화
    * 차이점이 선택 되었을 때 그 차이점이 처음 차이점과 같다면 처음 차이점 비활성화
    * 차이점이 선택 되었을 때 그 차이점이 마지막 차이점과 같다면 마지막 차이점 비활성화
    * */
    private void compareButtonOnAction() {
        setClickabeButtons("true","true","true","true","true","true","true","true","true","true");
    }
    @FXML
    private void copyToLeftButtonOnAction() {
        System.out.println("Click");
    }
    @FXML
    private void copyToRightButtonOnAction() { copy_to_right_button.setText(""); }
    @FXML
    private void copyToRightAllButtonOnAction() {
        copy_to_right_all_button.setText("");
    }
    @FXML
    private void copyToLeftAllButtonOnAction(){
        copy_to_left_all_button.setText("");
    }
    @FXML
    private void nextDifferenceButtonOnAction() { next_difference_button.setText(""); }
    @FXML
    private void postDifferenceButtonOnAction() { post_difference_button.setText(""); }
    @FXML
    private void firstDifferenceButtonOnAction() { first_difference_button.setText(""); }
    @FXML
    private void nowDifferenceButtonOnAction() { now_difference_button.setText(""); }
    @FXML
    private void LastDifferenceButtonOnAction() { last_difference_button.setText(""); }
    @FXML
    private void newTabMenuItemOnAction() { System.out.println(""); }
    
    @FXML
    private void openMenuItemOnAction() {
        OpenFileWindow openFileWindow = new OpenFileWindow();
        System.out.println("Open");
    }
    @FXML
    private void saveMenuItemOnAction() {
        SaveFileWindow saveFileWindow = new SaveFileWindow();
        System.out.println("Save");
    }
    @FXML
    private void saveRightFileMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void saveLeftFileMenuItemOnAction() { System.out.println(""); }
    @FXML
    BorderPane main_pane;// 어떤 윈도우를 없에는지 지정하기위해 만듬
    @FXML
    private void closeMenuItemOnAction() {
        /*
        여러가지 조건 필요

        EX> 저장되지 않은 상태라면 저장하고 종료를 묻는다.
            Compare 중 이라면 merge 하지않고 종료할 것인지 묻는다.
        * */
        ((Window)(main_pane.getScene().getWindow())).hide();// 종료 method
        System.out.println("");
    }
    @FXML
    private void nextDifferenceMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void postDifferenceMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void firstDifferenceMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void nowDifferenceMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void lastDifferenceMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void copyToRightMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void copyToLeftMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void copyToRightAllMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void copyToLeftAllMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void compareMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void helpMenuItemOnAction() {
        HelpWindow HelpWindow = new HelpWindow();
        try {
            HelpWindow.HelpWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Help");
    }
    @FXML
    private void programInformationMenuItemOnAction() {
        ProgramInformationWindow programInformationWindow = new ProgramInformationWindow();
        try {
            programInformationWindow.PrograminformationWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProgramInfo");
    }
    @FXML
    private void closeTabMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void closeTabAllMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void tab1MenuItemOnAction(){ System.out.println(""); }

    private void setClickabeButtons(String next, String post, String first, String now, String last, String ctor, String ctol, String ctora, String ctola, String compare){
        if(next != null){
            if(next == "true") next_difference_button.setDisable(false);
            else next_difference_button.setDisable(true);
        }
        if(post != null){
            if(post == "true") post_difference_button.setDisable(false);
            else post_difference_button.setDisable(true);
        }
        if(first != null){
            if(first == "true") first_difference_button.setDisable(false);
            else first_difference_button.setDisable(true);
        }
        if(now != null){
            if(now == "true") now_difference_button.setDisable(false);
            else now_difference_button.setDisable(true);
        }
        if(last != null){
            if(last == "true") last_difference_button.setDisable(false);
            else last_difference_button.setDisable(true);
        }
        if(ctor != null){
            if(ctor == "true") copy_to_right_button.setDisable(false);
            else copy_to_right_button.setDisable(true);
        }
        if(ctol != null){
            if(ctol == "true") copy_to_left_button.setDisable(false);
            else copy_to_left_button.setDisable(true);
        }
        if(ctora != null){
            if(ctora == "true") copy_to_right_all_button.setDisable(false);
            else copy_to_right_all_button.setDisable(true);
        }
        if(ctola != null){
            if(ctola == "true") copy_to_left_all_button.setDisable(false);
            else copy_to_left_all_button.setDisable(true);
        }
        if(compare != null){
            if(compare == "true") compare_button.setDisable(false);
            else compare_button.setDisable(true);
        }
    }
}
