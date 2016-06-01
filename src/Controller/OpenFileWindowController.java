package Controller;

import java.io.File;
import java.io.IOException;

import Model.ModelInterface;
import Model.ModelRealize;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;

public class OpenFileWindowController extends FileWindowAbstractClass {
	@FXML
	private AnchorPane file_anchor_pane;
	@FXML
	private TextField right_file_text_area, left_file_text_area, warning_info_text_area;

    private Button compare_button_file;
    
	private Tab tab;
	private Label left_file_label, right_file_label;
	private Button left_load, left_edit, left_save, right_load, right_edit, right_save;
	private TextArea left_text_area, right_text_area;
	private TextArea left_file_bottom_text_area, right_file_bottom_text_area;
	private ListView left_list_view, right_list_view;

    private boolean item_flag = false;

    private String fileRightname="";
    private String fileLeftname="";


    @FXML
    public void leftFileFindButtonOnAction(){

        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        int tab_num = (int)tab.getUserData();
        FileChooser fileChooser = super.loadFileChooser();
        try {
            File file = fileChooser.showOpenDialog(null);

            Model.ModelInterface model = ModelRealize.getInstance();
            model.readTextOuter(tab_num, file.getAbsolutePath(), 0);

            //파일을 찾았으면 파일을 열어두고 표시창에 파일의 이름을 표시한다
            left_file_text_area.setText(file.getAbsolutePath());
            fileLeftname = file.getName();
            warning_info_text_area.setText("Select " + fileLeftname);
        }catch (NullPointerException e){
            warning_info_text_area.setText("Select No Left File");
        }catch (IOException e){
            e.printStackTrace();
        }
        tab_num = (int)tab.getUserData();
    }

    @FXML
    public void rightFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        int tab_num = (int)tab.getUserData();
        FileChooser fileChooser = super.loadFileChooser();
        try {
            File file = fileChooser.showOpenDialog(null);

            Model.ModelInterface model = ModelRealize.getInstance();
            model.readTextOuter(tab_num, file.getAbsolutePath(), 1);

            //파일을 찾았으면 파일을 열어두고 표시창에 파일의 이름을 표시한다
            right_file_text_area.setText(file.getAbsolutePath());
            fileRightname = file.getName();
            warning_info_text_area.setText("Select " + fileRightname);
        }catch (NullPointerException e){
            warning_info_text_area.setText("Select No Right File");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void okButtonOnAction() {

        /* 현재 탭의 구성요소들을 사용 가능하게 해 두고
        * 버튼들을 활성화시킨다
        */
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        int tab_num = (int)tab.getUserData();
        //textarea
        left_text_area.setVisible(true);
        right_text_area.setVisible(true);
        left_list_view.setVisible(false);
        left_list_view.setDisable(true);
        right_list_view.setVisible(false);
        right_list_view.setDisable(true);
        ModelInterface modelInterface = ModelRealize.getInstance();
        //탭의 패널 이름 변경
        if(!fileLeftname.equals("")) {
            left_text_area.setText(arrayListToString(modelInterface.getText(tab_num, 0)));
            left_file_label.setText(fileLeftname);
            super.changeTabName(tab, fileLeftname,"left");

        }
        if(!fileRightname.equals("")) {
            right_text_area.setText(arrayListToString(modelInterface.getText(tab_num, 1)));
            right_file_label.setText(fileRightname);
            super.changeTabName(tab, fileRightname,"right");
        }
        //스플릿 패널 활성화 설정
        left_load.setDisable(false);
        left_edit.setDisable(false);
        left_save.setDisable(true);
        right_load.setDisable(false);
        right_edit.setDisable(false);
        right_save.setDisable(true);


        if(!left_edit.isDisable() && !right_edit.isDisable()) {
            BorderPane main_center_pane = (BorderPane)((BorderPane)tab.getTabPane().getScene().getRoot()).getCenter();
            Button compare = (Button)((ToolBar)(main_center_pane.getTop())).getItems().get(11);
            compare.setDisable(false);
        }
        //창 종료
        Stage stage = (Stage) file_anchor_pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelButtonOnAction(){
        Stage stage = (Stage)file_anchor_pane.getScene().getWindow();

        if(!fileLeftname.equals("") || !fileRightname.equals("")) {
           super.initCancelButtonAction("Open File Alarm", "Wouldn't you open this file", stage);
        }
        else{
            stage.close();
        }
    }
    /*
    * tab 의 구성요소들을 가져온다.
    * */
    private void getTabContent(){
    	//현재 탭의 버튼 요소들
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
    * array list 를 받아서 string 으로 만들어 준다.
    * */
    private String arrayListToString(ArrayList<String> arrayList){
        String s = new String();
        for (String s1 : arrayList) {
            s += s1 + "\n";
        }
        return s;
    }
}
