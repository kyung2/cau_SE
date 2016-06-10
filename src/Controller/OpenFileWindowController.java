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


/**
 * Implement openfilewindows
 * Created by woojin on 2016-05-20.
 * @author woonjin
 */
public class OpenFileWindowController extends FileWindowAbstractClass {
	@FXML
	private AnchorPane file_anchor_pane;
	@FXML
	private TextField right_file_text_area, left_file_text_area, warning_info_text_area;

    private Button compare_button;
    
	private Tab tab;
	private Label left_file_label, right_file_label;
	private Button left_load, left_edit, left_save, right_load, right_edit, right_save;
    private MenuItem open_menu_item, save_menu_item, save_right_file_menu_item, save_left_file_menu_item, compare_menu_item;
    private TextArea left_text_area, right_text_area;
	private TextArea left_status_area, right_status_area;
	private ListView left_list_view, right_list_view;

    private boolean item_flag = false, left_file_chooser_flag = true, right_file_chooser_flag = true;

    private File right_file, left_file;
    private boolean right_file_flag = false, left_file_flag = false;
    private StatusController left_status, right_status;

    @FXML
    /**
     * leftFileLoad button Actions
     *update state after file load.
     */
    public void leftFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        if(left_file_chooser_flag) {
            int tab_num = (int) tab.getUserData();
            FileChooser fileChooser = super.customFileChooser("Left File FileChooser");

            try {
                left_file_chooser_flag = false;
                left_file = fileChooser.showOpenDialog(null);
                left_file_chooser_flag = true;
                left_file_flag = true;
                left_file_text_area.setText(left_file.getAbsolutePath());
                warning_info_text_area.setText("Select " + left_file.getName());
            } catch (NullPointerException e) {
                warning_info_text_area.setText("Select No Left File");
            }
            tab_num = (int) tab.getUserData();
        }
        else{
            System.out.println("Left File FileChooser is already open!");
        }
    }

    @FXML
    /**
     * RightFileLoad button Actions
     *update state after file load.
     */
    public void rightFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        if(right_file_chooser_flag) {
            int tab_num = (int) tab.getUserData();
            FileChooser fileChooser = super.customFileChooser("Right File FileChooser");
            try {
                right_file_chooser_flag = false;
                right_file = fileChooser.showOpenDialog(null);
                right_file_chooser_flag = true;
                right_file_flag = true;
                right_file_text_area.setText(right_file.getAbsolutePath());
                warning_info_text_area.setText("Select " + right_file.getName());
            } catch (NullPointerException e) {
                warning_info_text_area.setText("Select No Right File");
            }
        }
        else{
            System.out.println("Right File FileChooser is already open!");
        }
    }

    @FXML
    /**
     * OKbutton
     *update state about other components
     */
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
        if(left_file_flag) {
            try {
                Model.ModelInterface model = ModelRealize.getInstance();
                model.readTextOuter(tab_num, left_file.getAbsolutePath(), 0);

                //파일을 찾았으면 파일을 열어두고 표시창에 파일의 이름을 표시한다
                left_file_text_area.setText(left_file.getAbsolutePath());
                String fileLeftname = left_file.getName();

                left_text_area.setText(arrayListToString(modelInterface.getText(tab_num, 0)));
                left_file_label.setText(fileLeftname);
                super.changeTabName(tab, fileLeftname, "left");

                left_status.setFileName(fileLeftname);
                left_status.addStatusWithName("File open");
                setClickableButtonsInFilePane("left", "true", "true", "false");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(right_file_flag) {
            try{
                Model.ModelInterface model = ModelRealize.getInstance();
                model.readTextOuter(tab_num, right_file.getAbsolutePath(), 1);

                //파일을 찾았으면 파일을 열어두고 표시창에 파일의 이름을 표시한다
                right_file_text_area.setText(right_file.getAbsolutePath());
                String fileRightname = right_file.getName();

                right_text_area.setText(arrayListToString(modelInterface.getText(tab_num, 1)));
                right_file_label.setText(fileRightname);
                super.changeTabName(tab, fileRightname,"right");

                right_status.setFileName(fileRightname);
                right_status.addStatusWithName("File open");
                setClickableButtonsInFilePane("right","true","true","false");

            } catch (IOException e){
                e.printStackTrace();
            }

        }
        checkCompareButtonAndMenuItem();

        //창 종료
        Stage stage = (Stage) file_anchor_pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    /**
     * load action cancel*/
    public void cancelButtonOnAction(){
        Stage stage = (Stage)file_anchor_pane.getScene().getWindow();

        if(left_file_flag || right_file_flag) {
           super.initCancelButtonAction("Open File Alarm", "Wouldn't you open this file?", stage);
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

        BorderPane main_center_pane = (BorderPane)((BorderPane)tab.getTabPane().getScene().getRoot()).getCenter();
        compare_button = (Button)((ToolBar)(main_center_pane.getTop())).getItems().get(11);

        left_status_area = (TextArea)((SplitPane)left_pane.getChildren().get(1)).getItems().get(1);
        right_status_area = (TextArea)((SplitPane)right_pane.getChildren().get(1)).getItems().get(1);

        AnchorPane left_file_pane = (AnchorPane)((SplitPane)left_pane.getChildren().get(1)).getItems().get(0);
        AnchorPane right_file_pane = (AnchorPane)((SplitPane)right_pane.getChildren().get(1)).getItems().get(0);

        left_text_area = (TextArea)left_file_pane.getChildren().get(0);
        left_list_view = (ListView)left_file_pane.getChildren().get(1);
        right_text_area = (TextArea)right_file_pane.getChildren().get(0);
        right_list_view = (ListView)right_file_pane.getChildren().get(1);

        MenuBar menu_bar = (MenuBar)((BorderPane)compare_button.getScene().getRoot()).getTop();

        Menu menu = menu_bar.getMenus().get(1);
        compare_menu_item = menu.getItems().get(9);

        menu = menu_bar.getMenus().get(0);
        open_menu_item = menu.getItems().get(1);
        save_menu_item = menu.getItems().get(2);
        save_left_file_menu_item = menu.getItems().get(3);
        save_right_file_menu_item = menu.getItems().get(4);

        initStatusControl();
    }

    /**
    * file pane 버튼의 able 과 disable 을 해준다.
    * position 에 left 와 right 를 통해서 위치를 선택
    * 각각 load, edit, save에 true || false 를 통해서 able 과 disable 을 한다.
    * null 일 경우 그 버튼은 현상 유지
     @param edit edit state
     @param save save
     @param load load
     @param position left right
    * */
    private void setClickableButtonsInFilePane(String position, String load, String edit, String save){
        boolean f_load, f_edit, f_save;
        f_load = load == "true" ? true : false;
        f_edit = edit == "true" ? true : false;
        f_save = save == "true" ? true : false;

        if(position == "left"){
            if(load != null) {
                left_load.setDisable(!f_load);
                if(!f_load) open_menu_item.setDisable(true);
            }
            if(edit != null) {
                left_edit.setDisable(!f_edit);
                if(!f_edit) left_edit.setStyle("-fx-background-color:transparent");
            }
            if(save != null) {
                left_save.setDisable(!f_save);
                save_left_file_menu_item.setDisable(!f_save);
                if(!f_save) save_menu_item.setDisable(true);
            }
        }
        else{
            if(load != null) {
                right_load.setDisable(!f_load);
                if(!f_load) open_menu_item.setDisable(true);
            }
            if(edit != null) {
                right_edit.setDisable(!f_edit);
                if(!f_edit) right_edit.setStyle("-fx-background-color:transparent");

            }
            if(save != null) {
                right_save.setDisable(!f_save);
                save_right_file_menu_item.setDisable(!f_save);
                if(!f_save) save_menu_item.setDisable(true);
            }
        }
        if(!left_load.isDisable() && !right_load.isDisable()) open_menu_item.setDisable(false);
        if(!left_save.isDisable() && !right_save.isDisable()) save_menu_item.setDisable(false);
    }

    /**
    * compare 버튼의 조건을 검사하고 활성화 or 비활성화
    * */
    private void checkCompareButtonAndMenuItem(){
        if(!left_edit.isDisable() && !right_edit.isDisable()) {
            compare_button.setDisable(false);
            compare_menu_item.setDisable(false);
        }
    }

    /**
    * array list 를 받아서 string 으로 만들어 준다.
     * @param arrayList want to change string
     * @return arraylist contenT
    * */
    private String arrayListToString(ArrayList<String> arrayList){
        String s = new String();
        for (String s1 : arrayList) {
            s += s1 + "\n";
        }
        return s;
    }

    private void initStatusControl(){
        left_status = new StatusController(left_status_area);
        right_status = new StatusController(right_status_area);
    }
}
