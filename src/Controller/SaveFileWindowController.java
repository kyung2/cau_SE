package Controller;

import Model.ModelInterface;
import Model.ModelRealize;
import View.AlarmWindow;
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
    private Button left_load, left_edit, left_save, right_load, right_edit, right_save;
    private TextArea left_text_area, right_text_area;
    private TextArea left_file_bottom_text_area, right_file_bottom_text_area;
    private ListView left_list_view, right_list_view;

    @FXML
    private AnchorPane file_anchor_pane;
    @FXML
    private TextField right_file_text_area, left_file_text_area, warning_info_text_area;

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
                doActionBySave("left");
                System.out.println("left");
            }
            if(right_file != null) {
                modelInterface.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
                modelInterface.writeTextOuter(tab_num,right_file.getAbsolutePath(), 1);
                super.changeTabName(tab, right_file.getName(),"right");
                right_file_label.setText(right_file.getName());
                doActionBySave("right");
                System.out.println("right");
            }
            if(!left_edit.isDisable() && !right_edit.isDisable()) {
                BorderPane main_center_pane = (BorderPane)((BorderPane)tab.getTabPane().getScene().getRoot()).getCenter();
                Button compare = (Button)((ToolBar)(main_center_pane.getTop())).getItems().get(11);
                compare.setDisable(false);
            }
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
        left_load = (Button)left_file_button_tab.getChildren().get(1);
        left_edit = (Button)left_file_button_tab.getChildren().get(2);
        left_save = (Button)left_file_button_tab.getChildren().get(3);

        right_file_label = (Label)right_file_button_tab.getChildren().get(0);
        right_load = (Button)right_file_button_tab.getChildren().get(1);
        right_edit = (Button)right_file_button_tab.getChildren().get(2);
        right_save = (Button)right_file_button_tab.getChildren().get(3);

        left_file_bottom_text_area = (TextArea)((SplitPane)left_pane.getChildren().get(1)).getItems().get(1);
        right_file_bottom_text_area = (TextArea)((SplitPane)right_pane.getChildren().get(1)).getItems().get(1);

        AnchorPane left_file_pane = (AnchorPane)((SplitPane)left_pane.getChildren().get(1)).getItems().get(0);
        AnchorPane right_file_pane = (AnchorPane)((SplitPane)right_pane.getChildren().get(1)).getItems().get(0);

        left_text_area = (TextArea)left_file_pane.getChildren().get(0);
        left_list_view = (ListView)left_file_pane.getChildren().get(1);
        right_text_area = (TextArea)right_file_pane.getChildren().get(0);
        right_list_view = (ListView)right_file_pane.getChildren().get(1);
    }

    /*
    * position 을 받아서 save 할 때의 action 을 한다.
    * */
    private void doActionBySave(String position){
        if(position == "left") {
            left_load.setDisable(false);
            left_save.setDisable(true);
            left_text_area.setEditable(false);
            left_edit.setStyle("-fx-background-color:transparent");
        }
        else {
            right_load.setDisable(false);
            right_save.setDisable(true);
            right_text_area.setEditable(false);
            right_edit.setStyle("-fx-background-color:transparent");
        }
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


}
