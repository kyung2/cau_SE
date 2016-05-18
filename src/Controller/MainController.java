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
    private void clickCompareButton() { compare_button.setText("Click"); }
    @FXML
    private Button copy_to_left_button;
    @FXML
    private void clickCopyToLeftButton() { copy_to_left_button.setText("Click"); }
    @FXML
    private Button copy_to_right_button;
    @FXML
    private void clickCopyToRightButton() { copy_to_right_button.setText("Click"); }
    @FXML
    private Button copy_to_right_all_button;
    @FXML
    private void clickCopyToRightAllButton() {
        copy_to_right_all_button.setText("Click");
    }
    @FXML
    private Button copy_to_left_all_button;
    @FXML
    private void clickCopyToLeftAllButton(){
        copy_to_left_all_button.setText("Click");
    }
    @FXML
    private Button next_difference_button;
    @FXML
    private void clickNextDifferenceButton() { next_difference_button.setText("Click"); }
    @FXML
    private Button post_difference_button;
    @FXML
    private void clickPostDifferenceButton() { post_difference_button.setText("Click"); }
    @FXML
    private Button first_difference_button;
    @FXML
    private void clickFirstDifferenceButton() { first_difference_button.setText("Click"); }
    @FXML
    private Button now_difference_button;
    @FXML
    private void clickNowDifferenceButton() { now_difference_button.setText("Click"); }
    @FXML
    private Button last_difference_button;
    @FXML
    private void clickLastDifferenceButton() { last_difference_button.setText("Click"); }
    @FXML
    private void clickNewTabMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickOpenMenuItem() {
        OpenFileWindow openFileWindow = new OpenFileWindow();
        System.out.println("ClickOpen");
    }
    @FXML
    private void clickSaveMenuItem() {
        SaveFileWindow saveFileWindow = new SaveFileWindow();
        System.out.println("ClickSave");
    }
    @FXML
    private void clickSaveRightFileMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickSaveLeftFileMenuItem() { System.out.println("Click"); }
    @FXML
    BorderPane main_pane;// 어떤 윈도우를 없에는지 지정하기위해 만듬
    @FXML
    private void clickCloseMenuItem() {
        /*
        여러가지 조건 필요

        EX> 저장되지 않은 상태라면 저장하고 종료를 묻는다.
            Compare 중 이라면 merge 하지않고 종료할 것인지 묻는다.
        * */
        ((Window)(main_pane.getScene().getWindow())).hide();// 종료 method
        System.out.println("Click");
    }
    @FXML
    private void clickNextDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickPostDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickFirstDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickNowDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickLastDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickCopyToRightMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickCopyToLeftMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickCopyToRightAllMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickCopyToLeftAllMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickCompareMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickHelpMenuItem() {
        HelpWindow HelpWindow = new HelpWindow();
        try {
            HelpWindow.HelpWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ClickHelp");
    }
    @FXML
    private void clickProgramInformationMenuItem() {
        ProgramInformationWindow programInformationWindow = new ProgramInformationWindow();
        try {
            programInformationWindow.PrograminformationWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ClickProgramInfo");
    }
    @FXML
    private void clickCloseTabMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickCloseTabAllMenuItem() { System.out.println("Click"); }
    @FXML
    private void clickTab1MenuItem(){ System.out.println("Click"); }
}
