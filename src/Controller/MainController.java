package Controller;

import View.HelpWindow;
import View.OpenFileWindow;
import View.ProgramInformationWindow;
import View.SaveFileWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.Tab.SELECTION_CHANGED_EVENT;

public class MainController implements Initializable {
    @FXML
    private Button compare_button, copy_to_left_button,copy_to_right_button, copy_to_left_all_button, copy_to_right_all_button,
            next_difference_button, post_difference_button, first_difference_button, now_difference_button, last_difference_button;
    @FXML
    private TabPane tab_pane;
    @FXML
    private Tab tab;
    @FXML
    BorderPane main_pane;

    private ArrayList<String[]> toolbar_stage = new ArrayList<String []>();
    private int tab_num, now_tab_num;
    @Override
    /*
    * tab은 user data 를 통해 구별한다.
    * 현재 tab에 user data 를 저장한다.
    * tool bar의 버튼들을 모두 비활성화로 한다.
    * */
    public void initialize(URL location, ResourceBundle resources) {
        tab_num = 0;
        tab.setUserData(tab_num);
        setClickabeButtons("false","false","false","false","false","false","false","false","false","false");
        toolbar_stage.add(new String[]{"false","false","false","false","false","false","false","false","false","false"});
        
        tab_pane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        String[] stage = makeToolbarStage();

                        toolbar_stage.set(now_tab_num,stage);
                        now_tab_num = (int)t1.getUserData();
                        setClickabeButtons(toolbar_stage.get(now_tab_num)[0],toolbar_stage.get(now_tab_num)[1],toolbar_stage.get(now_tab_num)[2],
                                toolbar_stage.get(now_tab_num)[3], toolbar_stage.get(now_tab_num)[4],toolbar_stage.get(now_tab_num)[5],
                                toolbar_stage.get(now_tab_num)[6],toolbar_stage.get(now_tab_num)[7],toolbar_stage.get(now_tab_num)[8],toolbar_stage.get(now_tab_num)[9]);
                    }
                }
        );
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
    /*
    * new tab 을 누르면 fxml 로 부터 정보를 읽어온 후
    * tab을 하나 만들고 그 정보를 입력한다.
    * user data는 현재 tab num 에서 1을 더한 값을 저장한다.
    * */
    private void newTabMenuItemOnAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Fxml/SplitFilePane.fxml"));
            Tab new_tab = new Tab("File");
            new_tab.setContent(root);
            new_tab.setUserData(++tab_num);
            toolbar_stage.add(new String[]{"false","false","false","false","false","false","false","false","false","false"});
            tab_pane.getTabs().add(new_tab);
        }catch (Exception e){
            System.out.println(e);// 적절한 예외처리로 바꿔야함
        }

    }

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
    /*
    * Toolbar 에 있는 버튼들의 활성화와 비활성화를 조절한다.
    * 아무 행동도 하지 않으려면 null 을 입력하면 된다.
    * */
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

    private String[] makeToolbarStage(){
        String[] stage = new String[10];
        if(!next_difference_button.isDisable()) { stage[0] = "true"; }
        else { stage[0] = "false"; }
        if(!post_difference_button.isDisable()) { stage[1] = "true"; }
        else { stage[1] = "false"; }
        if(!first_difference_button.isDisable()) { stage[2] = "true"; }
        else { stage[2] = "false"; }
        if(!now_difference_button.isDisable()) { stage[3] = "true"; }
        else { stage[3] = "false"; }
        if(!last_difference_button.isDisable()) { stage[4] = "true"; }
        else { stage[4] = "false"; }
        if(!copy_to_right_button.isDisable()) { stage[5] = "true"; }
        else { stage[5] = "false"; }
        if(!copy_to_left_button.isDisable()) { stage[6] = "true"; }
        else { stage[6] = "false"; }
        if(!copy_to_right_all_button.isDisable()) { stage[7] = "true"; }
        else { stage[7] = "false"; }
        if(!copy_to_left_all_button.isDisable()){ stage[8] = "true"; }
        else { stage[8] = "false"; }
        if(!compare_button.isDisable()) { stage[9] = "true"; }
        else { stage[9] = "false"; }
        return stage;
    }
}
