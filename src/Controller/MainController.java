package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private MenuItem new_tab_menu_item;
    @FXML
    private void clickNewTabMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem open_menu_item;
    @FXML
    private void clickOpenMenuItem() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/OpenFileWindow.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("OpenFileWindow");
            stage.show();
        } catch (IOException ex) {
        }
        System.out.println("Click");
    }
    @FXML
    private MenuItem save_menu_item;
    @FXML
    private void clickSaveMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem save_right_file_menu_item;
    @FXML
    private void clickSaveRightFileMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem save_left_file_menu_item;
    @FXML
    private void clickSaveLeftFileMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem close_menu_item;
    @FXML
    private void clickCloseMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem next_difference_menu_item;
    @FXML
    private void clickNextDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem post_difference_menu_item;
    @FXML
    private void clickPostDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem first_difference_menu_item;
    @FXML
    private void clickFirstDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem now_difference_menu_item;
    @FXML
    private void clickNowDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem last_difference_menu_item;
    @FXML
    private void clickLastDifferenceMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem copy_to_right_menu_item;
    @FXML
    private void clickCopyToRightMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem copy_to_left_menu_item;
    @FXML
    private void clickCopyToLeftMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem copy_to_right_all_menu_item;
    @FXML
    private void clickCopyToRightAllMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem copy_to_left_all_menu_item;
    @FXML
    private void clickCopyToLeftAllMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem compare_menu_item;
    @FXML
    private void clickCompareMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem help_menu_item;
    @FXML
    private void clickHelpMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem program_information_menu_item;
    @FXML
    private void clickProgramInformationMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem close_tab_menu_item;
    @FXML
    private void clickCloseTabMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem close_tab_all_menu_item;
    @FXML
    private void clickCloseTabAllMenuItem() { System.out.println("Click"); }
    @FXML
    private MenuItem tab_1_menu_item;
    @FXML
    private void clickTab1MenuItem(){ System.out.println("Click"); }
}
