package Controller;

import View.HelpWindow;
import View.OpenFileWindow;
import View.ProgramInformationWindow;
import View.SaveFileWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class MainController {
    @FXML
    private Button compare_button;
    @FXML
    private void compareButtonOnAction() { compare_button.setText(""); }
    @FXML
    private Button copy_to_left_button;
    @FXML
    private void copyToLeftButtonOnAction() { copy_to_left_button.setText(""); }
    @FXML
    private Button copy_to_right_button;
    @FXML
    private void copyToRightButtonOnAction() { copy_to_right_button.setText(""); }
    @FXML
    private Button copy_to_right_all_button;
    @FXML
    private void copyToRightAllButtonOnAction() {
        copy_to_right_all_button.setText("");
    }
    @FXML
    private Button copy_to_left_all_button;
    @FXML
    private void copyToLeftAllButtonOnAction(){
        copy_to_left_all_button.setText("");
    }
    @FXML
    private Button next_difference_button;
    @FXML
    private void nextDifferenceButtonOnAction() { next_difference_button.setText(""); }
    @FXML
    private Button post_difference_button;
    @FXML
    private void postDifferenceButtonOnAction() { post_difference_button.setText(""); }
    @FXML
    private Button first_difference_button;
    @FXML
    private void firstDifferenceButtonOnAction() { first_difference_button.setText(""); }
    @FXML
    private Button now_difference_button;
    @FXML
    private void nowDifferenceButtonOnAction() { now_difference_button.setText(""); }
    @FXML
    private Button last_difference_button;
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
}
