package Controller;

import Model.ModelRealize;
import View.AlarmWindow;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import Model.ModelInterface;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class SaveFileWindowController {
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
    private void leftFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        left_file = loadFileChooser();
        if(left_file != null){
            left_file_text_area.setText(left_file.getAbsolutePath());
        }
        else{
            warning_info_text_area.setText("Select no Right File");
        }
    }
    @FXML
    private void rightFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        right_file = loadFileChooser();

        if(right_file != null){
            right_file_text_area.setText(right_file.getAbsolutePath());
        }
        else{
            warning_info_text_area.setText("Select no Left File");
        }
    }
    @FXML
    private void okButtonOnAction(){
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
                changeTabName(left_file.getName(),"left");
                left_file_label.setText(left_file.getName());
                doActionBySave("left");
                System.out.println("left");
            }
            if(right_file != null) {
                modelInterface.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
                modelInterface.writeTextOuter(tab_num,right_file.getAbsolutePath(), 1);
                changeTabName(right_file.getName(),"right");
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
    private void cancelButtonOnAction(){
        if(left_file != null || right_file != null) {
            AlarmWindow exitAlarmWindow = new AlarmWindow("Save File Alarm", "Wouldn't you Save this file?");
            exitAlarmWindow.showAndWait();
            if ((boolean) exitAlarmWindow.getUserData()) {
                ((Stage)file_anchor_pane.getScene().getWindow()).close();
            }
        }
        else{
            ((Stage)file_anchor_pane.getScene().getWindow()).close();
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
    * file chooser 를 연다.
    * */
    private File loadFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        fileChooser.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter("All Files", "*.txt","*.java", "*.c", "*.cpp"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Java Files", "*.java"),
                new FileChooser.ExtensionFilter("C Files", "*.c","*.cpp")
        );
        File selectedFile = fileChooser.showSaveDialog(null);

        if(selectedFile == null) {
            System.out.println("No Select FIle");
        }
        return selectedFile;
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
   * 파일 이름을 받아서 tab name 을 바꿔준다.
   * */
    private void changeTabName(String name, String position){
        String tab_name = tab.getText();
        if(position.equals("left")) {
            tab_name = name + " -" + tab_name.split("-")[1];
            tab.setText(tab_name);
        }
        else {
            tab_name = tab_name.split("-")[0] + "- " + name;
            tab.setText(tab_name);
        }
    }
}
