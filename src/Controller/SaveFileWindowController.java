package Controller;

import Model.ModelInterface;
import Model.ModelRealize;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class SaveFileWindowController extends FileWindowAbstractClass{
    private boolean item_flag = false;
    private File left_file, right_file;
    private Tab tab;
    private Label left_file_label, right_file_label;
    private Button left_load_button, left_edit_button, left_save_button, right_load_button, right_edit_button, right_save_button, compare_button;
    private MenuItem open_menu_item, save_menu_item, save_left_file_menu_item, save_right_file_menu_item, compare_menu_item;
    private TextArea left_text_area, right_text_area;
    private TextArea left_status_area, right_status_area;

    @FXML
    private AnchorPane file_anchor_pane;
    @FXML
    private TextField right_file_text_area, left_file_text_area, warning_info_text_area;

    private StatusController left_status, right_status;

    @FXML
    public void leftFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        FileChooser fileChooser = super.loadFileChooser();
        try {
            left_file = fileChooser.showSaveDialog(null);
            left_file_text_area.setText(left_file.getAbsolutePath());
            warning_info_text_area.setText("Select " + left_file.getName());
        }catch (NullPointerException e){
            warning_info_text_area.setText("Select no Left File");
        }
    }

    @FXML
    public void rightFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        FileChooser fileChooser = super.loadFileChooser();
        try {
            right_file = fileChooser.showSaveDialog(null);
            right_file_text_area.setText(right_file.getAbsolutePath());
            warning_info_text_area.setText("Select " + right_file.getName());
        }catch (NullPointerException e){
            warning_info_text_area.setText("Select no Right File");
        }
    }

    @FXML
    public void okButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        ModelInterface modelInterface = ModelRealize.getInstance();
        try{
            int tab_num = (int)tab.getUserData();
            if(left_file != null) {
                modelInterface.setText(tab_num, stringToArrayList(left_text_area.getText()), 0);
                modelInterface.writeTextOuter(tab_num,left_file.getAbsolutePath(), 0);
                super.changeTabName(tab, left_file.getName(),"left");
                left_file_label.setText(left_file.getName());

                left_status.setFileName(left_file.getName());
                left_status.addStatusWithName("File open");

                doActionBySave("left");
                System.out.println("left");
            }
            if(right_file != null) {
                modelInterface.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
                modelInterface.writeTextOuter(tab_num,right_file.getAbsolutePath(), 1);
                super.changeTabName(tab, right_file.getName(),"right");
                right_file_label.setText(right_file.getName());

                right_status.setFileName(right_file.getName());
                right_status.addStatusWithName("File open");

                doActionBySave("right");
                System.out.println("right");
            }
            checkCompareButtonAndMenuItem();
        }catch (Exception e){
            e.printStackTrace();
        }
        ((Stage)file_anchor_pane.getScene().getWindow()).close();
    }

    @FXML
    public void cancelButtonOnAction(){
        Stage stage = (Stage)file_anchor_pane.getScene().getWindow();
        if(left_file != null || right_file != null) {
            super.initCancelButtonAction("Save File Alarm", "Wouldn't you Save this file?", stage);
        }
        else{
            stage.close();
        }
    }

    /*
    *  tab 에 포함된 내용물들을 가져온다
    * */
    private void getTabContent(){
        tab = (Tab)file_anchor_pane.getScene().getUserData();
        AnchorPane left_pane = (AnchorPane)((SplitPane)tab.getContent()).getItems().get(0);
        AnchorPane right_pane = (AnchorPane)((SplitPane)tab.getContent()).getItems().get(1);
        AnchorPane left_file_button_tab = (AnchorPane)left_pane.getChildren().get(0);
        AnchorPane right_file_button_tab = (AnchorPane) right_pane.getChildren().get(0);

        left_file_label = (Label)left_file_button_tab.getChildren().get(0);
        left_load_button = (Button)left_file_button_tab.getChildren().get(1);
        left_edit_button = (Button)left_file_button_tab.getChildren().get(2);
        left_save_button = (Button)left_file_button_tab.getChildren().get(3);

        right_file_label = (Label)right_file_button_tab.getChildren().get(0);
        right_load_button = (Button)right_file_button_tab.getChildren().get(1);
        right_edit_button = (Button)right_file_button_tab.getChildren().get(2);
        right_save_button = (Button)right_file_button_tab.getChildren().get(3);

        BorderPane main_center_pane = (BorderPane)((BorderPane)tab.getTabPane().getScene().getRoot()).getCenter();
        compare_button = (Button)((ToolBar)(main_center_pane.getTop())).getItems().get(11);

        left_status_area = (TextArea)((SplitPane)left_pane.getChildren().get(1)).getItems().get(1);
        right_status_area = (TextArea)((SplitPane)right_pane.getChildren().get(1)).getItems().get(1);

        AnchorPane left_file_pane = (AnchorPane)((SplitPane)left_pane.getChildren().get(1)).getItems().get(0);
        AnchorPane right_file_pane = (AnchorPane)((SplitPane)right_pane.getChildren().get(1)).getItems().get(0);

        left_text_area = (TextArea)left_file_pane.getChildren().get(0);
        right_text_area = (TextArea)right_file_pane.getChildren().get(0);

        MenuBar menu_bar = (MenuBar)((BorderPane)compare_button.getScene().getRoot()).getTop();

        Menu menu = menu_bar.getMenus().get(1);
        compare_menu_item = menu.getItems().get(9);

        menu = menu_bar.getMenus().get(0);
        open_menu_item = menu.getItems().get(1);
        save_menu_item = menu.getItems().get(2);
        save_left_file_menu_item = menu.getItems().get(3);
        save_right_file_menu_item = menu.getItems().get(4);

        initStatus();
    }

    /*
    * compare 버튼의 조건을 검사하고 활성화 or 비활성화
    * */
    private void checkCompareButtonAndMenuItem(){
        if(!left_edit_button.isDisable() && !right_edit_button.isDisable()) {
            compare_button.setDisable(false);
            compare_menu_item.setDisable(false);
        }
    }

    /*
    * position 을 받아서 save 할 때의 action 을 한다.
    * */
    private void doActionBySave(String position){
        save_menu_item.setDisable(true);
        if(position == "left") {
            left_load_button.setDisable(false);
            left_save_button.setDisable(true);
            save_left_file_menu_item.setDisable(true);
            left_text_area.setEditable(false);
            left_edit_button.setStyle("-fx-background-color:transparent");
        }
        else {
            right_load_button.setDisable(false);
            right_save_button.setDisable(true);
            save_right_file_menu_item.setDisable(true);
            right_text_area.setEditable(false);
            right_edit_button.setStyle("-fx-background-color:transparent");
        }
        if(!left_load_button.isDisable() && !right_load_button.isDisable()) open_menu_item.setDisable(false);
    }
    /*
    * String 을 받아와서 \n 로 split 한 후 ArrayList 에 저장한다.
    * */
    private ArrayList<String> stringToArrayList(String s){
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] strings = s.split("\n");
        for (String string : strings) {
            arrayList.add(string);
        }
        return arrayList;
    }

    private void initStatus(){
        left_status = new StatusController(left_status_area);
        right_status = new StatusController(right_status_area);
    }
}
