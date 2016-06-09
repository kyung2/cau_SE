package Controller;

import Model.ModelException.MergeLineIllegalException;
import Model.ModelInterface;
import Model.ModelRealize;
import View.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * MainController
 * Created by woojin on 2016-05-16
 * @author woojin Jang and soyoung Kim.
 */
public class MainController implements Initializable, MainInterface {
    @FXML
    private Button compare_button, copy_to_left_button, copy_to_right_button, copy_to_left_all_button, copy_to_right_all_button,
            next_difference_button, previous_difference_button, first_difference_button, now_difference_button, last_difference_button;
    @FXML
    private MenuItem open_menu_item, save_menu_item, save_right_file_menu_item, save_left_file_menu_item, tab_menu_item;
    @FXML
    private MenuItem next_difference_menu_item, previous_difference_menu_item, first_difference_menu_item, now_difference_menu_item, last_difference_menu_item,
            copy_to_right_menu_item, copy_to_left_menu_item, copy_to_right_all_menu_item, copy_to_left_all_menu_item, compare_menu_item;
    @FXML
    private TabPane tab_pane;
    @FXML
    private Tab tab;
    @FXML
    private Menu tab_menu;
    @FXML
    BorderPane main_pane;

    private Tab now_tab;
    private ListView left_text_list, right_text_list;
    private TextArea left_text_area, right_text_area;

    private StatusController left_status, right_status;

    private ArrayList<String[]> toolbar_stage = new ArrayList<String[]>();
    private ArrayList<boolean[]> file_menu_stage = new ArrayList<boolean[]>();
    private int tab_num, now_tab_num, tab_menu_item_num, close_tab_num;
    private boolean tab_num_change_flag = false;
    private int text_block_index;
    ImageIcon img = new ImageIcon("/View/Image/sampleIcon.jpg");


    @Override
    /*
    * tab은 user data 를 통해 구별한다.
    * 현재 tab에 user data 를 저장한다.
    * tool bar의 버튼들을 모두 비활성화로 한다.
    * 버튼과 관련된 menu item 들을 비활성화한다.
    * */
    /**
     * init
     */
    public void initialize(URL location, ResourceBundle resources) {
        tab_num = 0;
        now_tab = tab;
        now_tab.setUserData(tab_num);
        tab_menu_item_num = 1;
        tab_menu_item.setOnAction(e -> tabMenuItemOnAction(0));

        open_menu_item.setDisable(false);
        save_menu_item.setDisable(true);
        save_left_file_menu_item.setDisable(true);
        save_right_file_menu_item.setDisable(true);

        setClickableButtonsAndMenuItems("false", "false", "false", "false", "false", "false", "false", "false", "false", "false");
        toolbar_stage.add(new String[]{"false", "false", "false", "false", "false", "false", "false", "false", "false", "false"});
        file_menu_stage.add(new boolean[]{true,false,false,false});
        left_text_list = null;
        right_text_list = null;
        left_text_area = null;
        right_text_area = null;
        left_status = null;
        right_status = null;
        
        try {
            Model.ModelInterface model = ModelRealize.getInstance();
            model.newModel(tab_num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tab_pane.getSelectionModel().selectedItemProperty().addListener(
                (ov, t, t1) -> {
                    if (t1 != null) {
                        String[] stage = makeToolbarStage();
                        now_tab = t1;
                        toolbar_stage.set(now_tab_num, stage);
                        file_menu_stage.set(now_tab_num, new boolean[]{!open_menu_item.isDisable(), !save_menu_item.isDisable(), !save_left_file_menu_item.isDisable(), !save_right_file_menu_item.isDisable()});
                        close_tab_num = now_tab_num;
                        tab_num_change_flag = true;
                        now_tab_num = (int) now_tab.getUserData();
                        System.out.println("Change now tab num "+ close_tab_num +" to " + now_tab_num);
                        setClickableButtonsAndMenuItems(toolbar_stage.get(now_tab_num)[0], toolbar_stage.get(now_tab_num)[1], toolbar_stage.get(now_tab_num)[2],
                                toolbar_stage.get(now_tab_num)[3], toolbar_stage.get(now_tab_num)[4], toolbar_stage.get(now_tab_num)[5],
                                toolbar_stage.get(now_tab_num)[6], toolbar_stage.get(now_tab_num)[7], toolbar_stage.get(now_tab_num)[8], toolbar_stage.get(now_tab_num)[9]);
                        open_menu_item.setDisable(!file_menu_stage.get(now_tab_num)[0]);
                        save_menu_item.setDisable(!file_menu_stage.get(now_tab_num)[1]);
                        save_left_file_menu_item.setDisable(!file_menu_stage.get(now_tab_num)[2]);
                        save_right_file_menu_item.setDisable(!file_menu_stage.get(now_tab_num)[3]);
                        initTextAreaAndListOnTab();
                    } else {
                        setClickableButtonsAndMenuItems("false", "false", "false", "false", "false", "false", "false", "false", "false", "false");
                    }
                }
        );
    }

    @FXML

    /**
     *조건에 따라 달라져야함.
     * 다른 부분이 하나도 없다면 모든 버튼 비활성화 처리가 된다.
     * 현재 차이점이 선택되 있지 않다면 ㄷ음차이점과 이전 차이점, 모든 copy 버튼은 비활성화 상태가 된다.
     * 차이점이 선택 되었을 때 그 차이점이 처음 차이점과 같다면 처음 차이점 비활성화
     * 차이점이 선택 되었을 때 그 차이점이 마지막 차이점과 같다면 마지막 차이점 비활성화
     * */
    public void compareOnAction() {
        if (right_text_area == null || left_text_area == null || right_text_list == null || left_text_list == null) {
            initTextAreaAndListOnTab();
        }
        initStatusTextArea();

        left_text_area.setVisible(false);
        right_text_area.setVisible(false);
        Model.ModelInterface model = ModelRealize.getInstance();
        try {
            ArrayList<String> left_text = model.getArrangedText(now_tab_num, 0);
            ArrayList<String> right_text = model.getArrangedText(now_tab_num, 1);
            ArrayList<Integer> text_index = model.getArrangedGroupSpace(now_tab_num);

            if(text_index.get(0) == 0){
                left_text_list.getStylesheets().remove(0,left_text_list.getStylesheets().size());
                left_text_list.getStylesheets().add("/View/Css/listCell2.css");
                right_text_list.getStylesheets().remove(0,right_text_list.getStylesheets().size());
                right_text_list.getStylesheets().add("/View/Css/listCell2.css");
            }
            else{
                left_text_list.getStylesheets().remove(0,left_text_list.getStylesheets().size());
                left_text_list.getStylesheets().add("/View/Css/listCell1.css");
                right_text_list.getStylesheets().remove(0,right_text_list.getStylesheets().size());
                right_text_list.getStylesheets().add("/View/Css/listCell1.css");
            }

            ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStringsForList(left_text, text_index));
            ObservableList<String> right_list_item = FXCollections.observableArrayList(makeStringsForList(right_text, text_index));
            left_text_list.setItems(left_list_item);
            right_text_list.setItems(right_list_item);

            ScrollbarBinding.bind(left_text_list,right_text_list);

            if(left_list_item.get(0).equals(right_list_item.get(0))) text_block_index = 1;
            else text_block_index = 0;
            left_text_list.setVisible(true);
            right_text_list.setVisible(true);
            left_text_list.setDisable(false);
            right_text_list.setDisable(false);

            //setHighlight(text_index);
            left_text_list.getSelectionModel ().select (text_block_index);
            right_text_list.getSelectionModel ().select (text_block_index);

            // 파일의 차이점이 없을 경우 차이점 관련 버튼을 비활성화,
            // 차이점이 있을 경우 차이점 관련 버튼을 활성화
            if(left_list_item.size()==1) setClickableButtonsAndMenuItems("false", "false", "false", "false", "false", "false", "false", "false", "false", "true");
            else setClickableButtonsAndMenuItems("true", "false", "true", "true", "true", "true", "true", "true", "true", "true");

            left_status.addStatusWithName("Compare");
            right_status.addStatusWithName("Compare");
        } catch (Exception e) {
            e.printStackTrace();

            left_status.addStatusWithName("ERR - "+e.getMessage());
            right_status.addStatusWithName("ERR - "+e.getMessage());

        }
    }

    @FXML

    /**
     * selected difference block copy to Left
     * */
    public void copyToLeftOnAction() {
        initStatusTextArea();
        Model.ModelInterface model = ModelRealize.getInstance();
        try {
            text_block_index=left_text_list.getSelectionModel().getSelectedIndex();
            model.mergeByGroup(now_tab_num, text_block_index+(model.getArrangedGroupSpace(now_tab_num).get(0)==0?1:0), false);
            left_status.addStatus("Copy to left");
            right_status.addStatus("Copy to left");
            left_status.setMenuFlag();
            right_status.setMenuFlag();
            saveButtonMenuItem("left",true);
        }
         catch (MergeLineIllegalException e) {
             e.printStackTrace();
             left_status.addStatusWithName("ERR - "+e.getMessage());
        }
        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 0)));

        compareOnAction();
    }

    @FXML
    /**
     * selected difference block copy to Right
     * */
    public void copyToRightOnAction() {
        initStatusTextArea();
        Model.ModelInterface model = ModelRealize.getInstance();
        try {
            text_block_index=right_text_list.getSelectionModel().getSelectedIndex();
            model.mergeByGroup(now_tab_num, text_block_index+(model.getArrangedGroupSpace(now_tab_num).get(0)==0?1:0), true);
            left_status.addStatus("Copy to right");
            right_status.addStatus("Copy to right");
            left_status.setMenuFlag();
            right_status.setMenuFlag();
            saveButtonMenuItem("right",true);
        }
        catch (MergeLineIllegalException e) {
            e.printStackTrace();
            right_status.addStatusWithName("ERR - "+e.getMessage());
        }
        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 1)));
        compareOnAction();
    }

    @FXML
    /**
     *오른쪽에 있는 내용을 copy to Left
     * */
    public void copyToLeftAllOnAction() {
        initStatusTextArea();
        Model.ModelInterface model = ModelRealize.getInstance();
        model.setText(now_tab_num, model.getText(now_tab_num,1),0);

        left_status.addStatus("Copy to left All");
        right_status.addStatus("Copy to left All");
        left_status.setMenuFlag();
        right_status.setMenuFlag();
        saveButtonMenuItem("left",true);

        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 1)));
        compareOnAction();
    }

    @FXML
    /**
     *왼쪽에 있는 내용을 copy to Right
     * */
    public void copyToRightAllOnAction() {
        initStatusTextArea();
        Model.ModelInterface model = ModelRealize.getInstance();
        model.setText(now_tab_num, model.getText(now_tab_num,0),1);

        left_status.addStatus("Copy to right All");
        right_status.addStatus("Copy to right All");
        left_status.setMenuFlag();
        right_status.setMenuFlag();
        saveButtonMenuItem("right",true);

        right_text_area.setText(arrayListToString(model.getText(now_tab_num, 0)));
        compareOnAction();
    }

    @FXML
    /**
     * 현재 선택된 차이점에서 다음 차이점으로 넘어감
     * if selected block is last , next Diff Button is disabled.
     * */
    public void nextDifferenceOnAction() {
        Model.ModelInterface model = ModelRealize.getInstance();
        ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStringsForList(model.getArrangedText(now_tab_num,0), model.getArrangedGroupSpace(now_tab_num)));
        //맨 마지막이면 비활성화 되어야함

        int clicked_index = left_text_list.getSelectionModel().getSelectedIndex();
        if(clicked_index+2<=left_list_item.size()-1) {
            text_block_index = clicked_index + 2;
            changeScrollbar(text_block_index);

            if(previous_difference_button.isDisable()) {
                preDifferenceButtonAndMenuItem(true);
            }
            if(text_block_index>=left_list_item.size()-2) {
                nextDifferenceButtonAndMenuItem(false);
            }
        }
        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);
    }

    @FXML
    /**
     * 현재 선택된 차이점에서 이전 차이점으로 넘어감
     * if selected block is first , previous Diff Button is disabled.
     * */
    public void previousDifferenceOnAction() {
        int clicked_index = left_text_list.getSelectionModel().getSelectedIndex();
        //left_text_list.getSelectionModel().;      `
        //맨 처음이면 비활성화 되어야함
        if(clicked_index-2>=0) {
            text_block_index = clicked_index - 2;
            changeScrollbar(text_block_index);

            if(next_difference_button.isDisable()) {
                nextDifferenceButtonAndMenuItem(true);
            }
            if(text_block_index<=1) {
                preDifferenceButtonAndMenuItem(false);
            }
        }
        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);
    }

    @FXML
    /**
     * Go to First diff blcok.
     * */
    public void firstDifferenceOnAction() {
        //처음 차이점으로;
        Model.ModelInterface model = ModelRealize.getInstance();
        ArrayList<Integer> text_index = model.getArrangedGroupSpace(now_tab_num);
        ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStringsForList(model.getArrangedText(now_tab_num,0), model.getArrangedGroupSpace(now_tab_num)));

        if(text_index.get(0) == 0) text_block_index = 0;
        else text_block_index = 1;
        changeScrollbar(text_block_index);

        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);

        // 이전 차이점 버튼 비활성화
        // + 맨 처음으로 돌아갔는데, 맨 마지막 차이점일 경우(차이나는 부분이 하나밖에 없을 경우)
        // 다음 차이점 버튼 비활성화
        preDifferenceButtonAndMenuItem(false);
        if(!(text_block_index==left_list_item.size()-2 || text_block_index==left_list_item.size()-1)) nextDifferenceButtonAndMenuItem(true);
        else nextDifferenceButtonAndMenuItem(false);
        setClickableButtonsAndMenuItems(null,null,"true","true","true","true","true","true","true",null);
    }

    @FXML
    /**
     * go to near diff block
     * 현재 차이점에 있으면 그대로 그 블록에 있는다.
     * 하지만 차이점이 없는 블록선택시 다음에 존재하는 차이점으로 이동된다.
     * 보통 바로 아래의 차이점으로 간다.
     * 다음에 차이점이 없는 경우는 그 전 차이점으로 간다.
     * */
    public void nowDifferenceOnAction() {
        ModelInterface modelInterface = ModelRealize.getInstance();
        ArrayList<Integer> text_index = modelInterface.getArrangedGroupSpace(now_tab_num);
        ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStringsForList(modelInterface.getArrangedText(now_tab_num,0), modelInterface.getArrangedGroupSpace(now_tab_num)));

        //시점만을 현재 위치로 이동
        text_block_index=left_text_list.getSelectionModel().getSelectedIndex();
        changeScrollbar(text_block_index);
        System.out.println(text_index);
        if(text_index.get(0) != 0){ // 처음 cell 이 서로 동일할 때
            if(text_block_index % 2 == 0) {
                if(text_block_index == text_index.size() - 1 ) text_block_index -= 1;
                else text_block_index += 1;                 // 짝수번째(같은 부분)를 focus 중 이면 다음 cell 을 현재 차이점으로
            }
        }
        else{   // 처음 cell 이 서로 다를 대
            if(text_block_index %2 == 1){
                if(text_block_index == text_index.size() - 2 ) text_block_index -= 1;
                else text_block_index += 1;                 // 홀수번째(같은 부분)를 focus 중 이면 다음 cell 을 현재 차이점으로
            }
        }
        if(text_block_index==0 || text_block_index==1) preDifferenceButtonAndMenuItem(false);
        else preDifferenceButtonAndMenuItem(true);

        if(text_block_index == left_list_item.size()-2 || text_block_index == left_list_item.size()-1) nextDifferenceButtonAndMenuItem(false);
        else nextDifferenceButtonAndMenuItem(true);
        setClickableButtonsAndMenuItems(null,null,"true","true","true","true","true","true","true",null);

        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);
    }

    @FXML
    /**
     * go to last diff
     * */
    public void lastDifferenceOnAction() {
        //마지막 차이점으로
        Model.ModelInterface model = ModelRealize.getInstance();
        ArrayList<Integer> text_index = model.getArrangedGroupSpace(now_tab_num);
        int text_index_size = text_index.size();
        if(text_index.get(0) == 0) {    // 처음이 서로 같을 때
            if(text_index_size % 2 == 0) {
                text_block_index = text_index_size - 2;
            }
            else{
                text_block_index = text_index_size - 3;
            }
        }
        else {  // 처음부터 서로 다를 때
            if(text_index_size % 2 == 0) {
                text_block_index = text_index_size - 1 ;
            }
            else{
                text_block_index = text_index_size - 2;
            }
        }
        changeScrollbar(text_block_index);

        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);

        // 다음 차이점 버튼 비활성화
        // + 맨 끝으로 갔는데, 맨 처음 차이점일 경우(차이나는 부분이 하나밖에 없을 경우)
        // 이전 차이점 버튼 비활성화
        nextDifferenceButtonAndMenuItem(false);
        if(!(text_block_index==0 || text_block_index==1)) preDifferenceButtonAndMenuItem(true);
        else preDifferenceButtonAndMenuItem(false);

        setClickableButtonsAndMenuItems(null,null,"true","true","true","true","true","true","true",null);
    }

    @FXML
    /**new tab or Ctrl + N -> fxml로 부터 정보를 읽어온다.
     * 새로운 tab이 생성되고 그 정보를 입력한다.
     * user data에는 현재 tab_num + 1을 더한 값을 저장, tab에 해당되는 새로운 모델을 만든다. */
    public void newTabMenuItemOnAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Fxml/SplitFilePane.fxml"));
            Tab new_tab = new Tab("File");
            new_tab.setContent(root);
            new_tab.setUserData(++tab_num);
            new_tab.setOnClosed(e -> tabCloseAction());
            toolbar_stage.add(new String[]{"false", "false", "false", "false", "false", "false", "false", "false", "false", "false"});
            file_menu_stage.add(new boolean[]{true,false,false,false});
            tab_pane.getTabs().add(new_tab);
            MenuItem tab_menuitem = new MenuItem("Tab " + ++tab_menu_item_num);
            tab_menu.getItems().add(tab_menuitem);
            tab_menuitem.setOnAction((e) -> {
                tabMenuItemOnAction(Integer.parseInt(tab_menuitem.getText().split(" ")[1]) - 1);
            });
            Model.ModelInterface model = ModelRealize.getInstance();
            model.newModel(tab_num);
        } catch (Exception e) {
            System.out.println(e);// 적절한 예외처리로 바꿔야함
        }

    }

    @FXML

    /**
     * 파일을 불러오는 window를 연다 .
     * */
    public void openMenuItemOnAction() {
        OpenFileWindow openFileWindow = new OpenFileWindow(now_tab);
        System.out.println("Open");
    }

    @FXML
    /**
     * 저장을 위한 window를 연다.
     * */
    public void saveMenuItemOnAction() {
        SaveFileWindow saveFileWindow = new SaveFileWindow(now_tab);
        System.out.println("Save");
    }

    @FXML
    /**
     * 왼쪽에 존재하는 파일을 저장한다.
     * */
    public void saveLeftFileMenuItemOnAction() {
        if (right_text_area == null || left_text_area == null || right_text_list == null || left_text_list == null) {
            initTextAreaAndListOnTab();
        }
        initStatusTextArea();

        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();
        if((boolean)saveAlarmWindow.getUserData()) {
            Model.ModelInterface model = ModelRealize.getInstance();
            try {
                model.setText(now_tab_num, stringToArrayList(left_text_area.getText()), 0);
                model.writeTextOuter(now_tab_num, 0);
                left_text_area.setEditable(false);

                setClickableButtonsInFilePane("left", "true", null, "false");
            } catch (Exception e) {
                e.printStackTrace();
                left_status.addStatusWithName("ERR - "+e.getMessage());
            }
            try{
                if(model.isOpen(now_tab_num,0) && model.isOpen(now_tab_num,1)) {
                    if (!right_text_area.isEditable() && !left_text_area.isEditable()) {
                        compareButtonAndMenuItem(true);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                left_status.addStatusWithName("ERR - "+e.getMessage());
            }
            left_status.addStatusWithName("File save");
        }
    }


    @FXML
    /**
     * 오른쪽에 있는 파일을 저장한다.
     * */
    public void saveRightFileMenuItemOnAction() {
        if (right_text_area == null || left_text_area == null || right_text_list == null || left_text_list == null) {
            initTextAreaAndListOnTab();
        }initStatusTextArea();

        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();
        if((boolean)saveAlarmWindow.getUserData()) {
            Model.ModelInterface model = ModelRealize.getInstance();
            try {
                model.setText(now_tab_num, stringToArrayList(right_text_area.getText()), 1);
                model.writeTextOuter(now_tab_num, 1);
                right_text_area.setEditable(false);
                setClickableButtonsInFilePane("right","true", null,"false");

            } catch (Exception e) {
                e.printStackTrace();
                right_status.addStatusWithName("ERR - "+e.getMessage());
            }
            try{
                if(model.isOpen(now_tab_num,0) && model.isOpen(now_tab_num,1)) {
                    if (!right_text_area.isEditable() && !left_text_area.isEditable()) {
                        compareButtonAndMenuItem(true);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                right_status.addStatusWithName("ERR - "+e.getMessage());
            }
            right_status.addStatusWithName("File save");
        }
    }

    @FXML
    /**
     * 창을 닫는다.
     * */
    public void closeMenuItemOnAction() {
        ((Stage) (main_pane.getScene().getWindow())).close();// 종료 method
    }

    @FXML
    /**
     * open helpwindow F1
     * */
    public void helpMenuItemOnAction() {
        HelpWindow HelpWindow = new HelpWindow();
        try {
            HelpWindow.HelpWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Help");
    }

    @FXML
    /**
     * open Program info
     * */
    public void programInformationMenuItemOnAction() {
        ProgramInformationWindow programInformationWindow = new ProgramInformationWindow();
        try {
            programInformationWindow.PrograminformationWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProgramInfo");
    }

    @FXML
    /**
    * tab 이 꺼지면 그에 해당하는 toolbar stage 의 값을 null 로 바꾼다.
    * 그 후 tab menu item 을 하나 없에고
    * 해당하는 모델을 닫는다.
    * */
    public void tabCloseAction() {
        if(!tab_num_change_flag){
            close_tab_num = now_tab_num;
        }
        tab_num_change_flag = false;
        System.out.println("Close tab num " + (close_tab_num));
        toolbar_stage.set(close_tab_num, null);
        file_menu_stage.set(close_tab_num, null);
        tab_menu.getItems().remove(tab_menu_item_num + 1);
        tab_menu_item_num--;
        Model.ModelInterface model = ModelRealize.getInstance();
        try {
            model.closeModel(close_tab_num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    /**
     * close tab now selected tab
     * */
    public void closeTabMenuItemOnAction() {
        close_tab_num = now_tab_num;
        tabCloseAction();
        ((TabPane) now_tab.getTabPane()).getTabs().remove(now_tab);
        System.out.println(tab_pane.getTabs().size());
        if(tab_pane.getTabs().size() == 0){
            setClickableButtonsAndMenuItems("false","false","false","false","false","false","false","false","false","false");
            open_menu_item.setDisable(true);
            save_menu_item.setDisable(true);
            save_left_file_menu_item.setDisable(true);
            save_right_file_menu_item.setDisable(true);
        }
    }

    @FXML
    /**
     * close all tab
     * */
    public void closeTabAllMenuItemOnAction() {
        ((TabPane) now_tab.getTabPane()).getTabs().remove(0, tab_pane.getTabs().size());
        tab_menu.getItems().remove(2, tab_menu_item_num + 2);
        tab_menu_item_num = 0;
        Model.ModelInterface model = ModelRealize.getInstance();
        model.closeModelAll();
        for (String[] strings : toolbar_stage) {
            strings = null;
        }
    }

    /**
     * focus to index's tab
     * @param index index fo focus tab
     * */
    private void tabMenuItemOnAction(int index) {
        tab_pane.getSelectionModel().select(index);
    }

    /**
     * control two save Buttons' able / disable condition
     * @param position "left" : left, "right" ; right
     * @param save save Button's able / disable condition
     * */
    private void saveButtonMenuItem(String position, boolean save){
        AnchorPane right_anchorPane = (AnchorPane) ((SplitPane) right_text_area.getParent().getParent().getParent()).getParent();
        AnchorPane right_button_area = (AnchorPane) right_anchorPane.getChildren().get(0);
        Button right_save = (Button) right_button_area.getChildren().get(3);

        AnchorPane left_anchorPane = (AnchorPane) ((SplitPane) left_text_area.getParent().getParent().getParent()).getParent();
        AnchorPane left_button_area = (AnchorPane) left_anchorPane.getChildren().get(0);
        Button left_save = (Button) left_button_area.getChildren().get(3);

        if(position.equals("left")){
            left_save.setDisable(!save);
            save_left_file_menu_item.setDisable(!save);
        }
        else if(position.equals("right")) {
            right_save.setDisable(!save);
            save_right_file_menu_item.setDisable(!save);
        }
        else{
            System.out.println("Wrong input");
        }
        if(!left_save.isDisable() && !right_save.isDisable()){
            save_menu_item.setDisable(false);
        }
    }
    /**
     * control compare Button's able / disable condition
     * @param compare compare Button's able / disable condition
     * */
    private void compareButtonAndMenuItem(boolean compare){
        compare_menu_item.setDisable(!compare);
        compare_button.setDisable(!compare);
    }
    /**
     * control previous difference Button's able / disable condition
     * @param pre previous difference Button's able / disable condition
     * */
    private void preDifferenceButtonAndMenuItem(boolean pre){
        previous_difference_menu_item.setDisable(!pre);
        previous_difference_button.setDisable(!pre);
    }

    /**
     * control next difference Button's able / disable condition
     * @param next next difference Button's able / disable condition
     * */
    private void nextDifferenceButtonAndMenuItem(boolean next){
        next_difference_menu_item.setDisable(!next);
        next_difference_button.setDisable(!next);
    }

    /**
     * Control buttons in toolbar and related menu item's able / disable condition
     * If you do not want to change some button, input null value
     * @param next next Button's disable/able condition
     * @param compare compare Button's disable/able condition
     * @param ctol copy to left Button's disable/able condition
     * @param ctola copy to left all Button's disable/able condition
     * @param ctora copy to Right all Button's disable/able condition
     * @param ctor copy to Right Button's disable/able condition
     * @param first first Button's disable/able condition
     * @param last last Button's disable/able condition
     * @param now now Button's disable/able condition
     * @param previous previous Button's disable/able condition
     * */
    private void setClickableButtonsAndMenuItems(String next, String previous, String first, String now, String last, String ctor, String ctol, String ctora, String ctola, String compare) {
        if (next != null) {
            if (next == "true") {
                next_difference_button.setDisable(false);
                next_difference_menu_item.setDisable(false);
            }
            else {
                next_difference_button.setDisable(true);
                next_difference_menu_item.setDisable(true);
            }
        }
        if (previous != null) {
            if (previous == "true") {
                previous_difference_button.setDisable(false);
                previous_difference_menu_item.setDisable(false);
            }
            else {
                previous_difference_button.setDisable(true);
                previous_difference_menu_item.setDisable(true);
            }
        }
        if (first != null) {
            if (first == "true") {
                first_difference_button.setDisable(false);
                first_difference_menu_item.setDisable(false);
            }
            else {
                first_difference_button.setDisable(true);
                first_difference_menu_item.setDisable(true);
            }
        }
        if (now != null) {
            if (now == "true") {
                now_difference_button.setDisable(false);
                now_difference_menu_item.setDisable(false);
            }
            else {
                now_difference_button.setDisable(true);
                now_difference_menu_item.setDisable(true);
            }
        }
        if (last != null) {
            if (last == "true") {
                last_difference_button.setDisable(false);
                last_difference_menu_item.setDisable(false);
            }
            else {
                last_difference_button.setDisable(true);
                last_difference_menu_item.setDisable(true);
            }
        }
        if (ctor != null) {
            if (ctor == "true") {
                copy_to_right_button.setDisable(false);
                copy_to_right_menu_item.setDisable(false);
            }
            else {
                copy_to_right_button.setDisable(true);
                copy_to_right_menu_item.setDisable(true);
            }
        }
        if (ctol != null) {
            if (ctol == "true") {
                copy_to_left_button.setDisable(false);
                copy_to_left_menu_item.setDisable(false);
            }
            else {
                copy_to_left_button.setDisable(true);
                copy_to_left_menu_item.setDisable(true);
            }
        }
        if (ctora != null) {
            if (ctora == "true") {
                copy_to_right_all_button.setDisable(false);
                copy_to_right_all_menu_item.setDisable(false);
            }
            else {
                copy_to_right_all_button.setDisable(true);
                copy_to_right_all_menu_item.setDisable(true);
            }
        }
        if (ctola != null) {
            if (ctola == "true") {
                copy_to_left_all_button.setDisable(false);
                copy_to_left_all_menu_item.setDisable(false);
            }
            else {
                copy_to_left_all_button.setDisable(true);
                copy_to_left_all_menu_item.setDisable(true);
            }
        }
        if (compare != null) {
            if (compare == "true") {
                compare_button.setDisable(false);
                compare_menu_item.setDisable(false);
            }
            else {
                compare_button.setDisable(true);
                compare_menu_item.setDisable(true);
            }
        }
    }

    /**
      * control file pane Buttons' able /disable condition
      * If you do not want to change some button, input null value
      * @param position "left" : left,  "right" :right
      * @param edit control edit button's disable/able condition
      * @param load control load button's disable/able condition
      * @param save control save button's disable/able condition
      * */
    private void setClickableButtonsInFilePane(String position, String load, String edit, String save){
        AnchorPane right_anchorPane = (AnchorPane) ((SplitPane) right_text_area.getParent().getParent().getParent()).getParent();
        AnchorPane right_button_area = (AnchorPane) right_anchorPane.getChildren().get(0);
        Button right_load = (Button) right_button_area.getChildren().get(1);
        Button right_edit = (Button) right_button_area.getChildren().get(2);
        Button right_save = (Button) right_button_area.getChildren().get(3);

        AnchorPane left_anchorPane = (AnchorPane) ((SplitPane) left_text_area.getParent().getParent().getParent()).getParent();
        AnchorPane left_button_area = (AnchorPane) left_anchorPane.getChildren().get(0);
        Button left_load = (Button) left_button_area.getChildren().get(1);
        Button left_edit = (Button) left_button_area.getChildren().get(2);
        Button left_save = (Button) left_button_area.getChildren().get(3);

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
     * change ListView's scrollbar to index
     * @param index block index that want to change scrollbar
     * */
    private void changeScrollbar(int index){
        ModelInterface model = ModelRealize.getInstance();
        ArrayList<Integer> arrangedGroupSpace = model.getArrangedGroupSpace(now_tab_num);
        int lineNum = 0;

        if( index  < 2) {
            if (index == 1) {
                if (arrangedGroupSpace.get(0) != 0 && arrangedGroupSpace.get(0) < 9) {
                    left_text_list.scrollTo(0);
                    right_text_list.scrollTo(0);
                }
                else if (arrangedGroupSpace.get(0) == 0 && arrangedGroupSpace.get(1) < 9) {
                    left_text_list.scrollTo(0);
                    right_text_list.scrollTo(0);
                }
                else {
                    left_text_list.scrollTo(index);
                    right_text_list.scrollTo(index);
                }
            } else {
                left_text_list.scrollTo(index);
                right_text_list.scrollTo(index);
            }
        }
        else {
            if (arrangedGroupSpace.get(0) == 0) {
                while(index > 0 && lineNum + arrangedGroupSpace.get(index) < 9){
                    lineNum += arrangedGroupSpace.get(index);
                    index--;
                }
            }
            else {
                while(index > 0 && lineNum + arrangedGroupSpace.get(index - 1) < 9){
                    lineNum += arrangedGroupSpace.get(index - 1);
                    index--;
                }
            }
            left_text_list.scrollTo(index);
            right_text_list.scrollTo(index);
        }
    }

    /**
     * make toolbar_stage with Buttons able / disable condition
     * @return String array of toolbar stage
     * */
    private String[] makeToolbarStage() {
        String[] stage = new String[10];
        if (!next_difference_button.isDisable()) {
            stage[0] = "true";
        } else {
            stage[0] = "false";
        }
        if (!previous_difference_button.isDisable()) {
            stage[1] = "true";
        } else {
            stage[1] = "false";
        }
        if (!first_difference_button.isDisable()) {
            stage[2] = "true";
        } else {
            stage[2] = "false";
        }
        if (!now_difference_button.isDisable()) {
            stage[3] = "true";
        } else {
            stage[3] = "false";
        }
        if (!last_difference_button.isDisable()) {
            stage[4] = "true";
        } else {
            stage[4] = "false";
        }
        if (!copy_to_right_button.isDisable()) {
            stage[5] = "true";
        } else {
            stage[5] = "false";
        }
        if (!copy_to_left_button.isDisable()) {
            stage[6] = "true";
        } else {
            stage[6] = "false";
        }
        if (!copy_to_right_all_button.isDisable()) {
            stage[7] = "true";
        } else {
            stage[7] = "false";
        }
        if (!copy_to_left_all_button.isDisable()) {
            stage[8] = "true";
        } else {
            stage[8] = "false";
        }
        if (!compare_button.isDisable()) {
            stage[9] = "true";
        } else {
            stage[9] = "false";
        }
        return stage;
    }

    /**
     * get TextArea and ListView related on now selected tab
     * */
    private void initTextAreaAndListOnTab() {
        AnchorPane left_anchor_pane = (AnchorPane) ((SplitPane) now_tab.getContent()).getItems().get(0);
        AnchorPane right_anchor_pane = (AnchorPane) ((SplitPane) now_tab.getContent()).getItems().get(1);
        SplitPane left_split_pane = (SplitPane) ((SplitPane) left_anchor_pane.getChildren().get(1));
        SplitPane right_split_pane = (SplitPane) ((SplitPane) right_anchor_pane.getChildren().get(1));
        
        left_text_area = (TextArea) ((AnchorPane) left_split_pane.getItems().get(0)).getChildren().get(0);
        right_text_area = (TextArea) ((AnchorPane) right_split_pane.getItems().get(0)).getChildren().get(0);
        left_text_list = (ListView) ((AnchorPane) left_split_pane.getItems().get(0)).getChildren().get(1);
        right_text_list = (ListView) ((AnchorPane) right_split_pane.getItems().get(0)).getChildren().get(1);
    }

    /**
     * initialize of left, right status area
     * */
    private void initStatusTextArea() {
        AnchorPane right_anchorPane = (AnchorPane) ((SplitPane) right_text_area.getParent().getParent().getParent()).getParent();
        AnchorPane right_button_area = (AnchorPane) right_anchorPane.getChildren().get(0);
        Button right_edit = (Button) right_button_area.getChildren().get(2);

        AnchorPane left_anchorPane = (AnchorPane) ((SplitPane) left_text_area.getParent().getParent().getParent()).getParent();
        AnchorPane left_button_area = (AnchorPane) left_anchorPane.getChildren().get(0);
        Button left_edit = (Button) left_button_area.getChildren().get(2);

        if (!left_edit.isDisable()) {
            Model.ModelInterface model = ModelRealize.getInstance();

            File left_file = new File(model.getUnit(now_tab_num).filepath(0));

            AnchorPane left_anchor_pane = (AnchorPane) ((SplitPane) now_tab.getContent()).getItems().get(0);
            SplitPane left_split_pane = (SplitPane) ((SplitPane) left_anchor_pane.getChildren().get(1));

            left_status = new StatusController((TextArea) (left_split_pane.getItems().get(1)));
            left_status.setFileName(left_file.getName());
        }
        if(!right_edit.isDisable()){
            Model.ModelInterface model = ModelRealize.getInstance();

            File right_file = new File(model.getUnit(now_tab_num).filepath(1));

            AnchorPane right_anchor_pane = (AnchorPane) ((SplitPane) now_tab.getContent()).getItems().get(1);
            SplitPane right_split_pane = (SplitPane) ((SplitPane) right_anchor_pane.getChildren().get(1));

            right_status = new StatusController((TextArea) (right_split_pane.getItems().get(1)));
            right_status.setFileName(right_file.getName());
        }
    }

    /**
     * make String array with ArrayList and Index of how many component in ArrayList make one String
     * @param index index of how many ArrayList need to make one String value
     * @param arrayList ArrayList which have list view's text
     * @return string ListView content*/
    private String[] makeStringsForList(ArrayList<String> arrayList, ArrayList<Integer> index) {
        String[] strings;
        int true_false_flag = 0;

        if (index.get(0) == 0) {
            true_false_flag = 1;
        }

        strings = new String[index.size() - true_false_flag];

        for (int i = 0, array = 0, n = strings.length; i < n; i++) {
            strings[i] = "";

            for (int j = 0, m = index.get(i + true_false_flag); j < m; j++) {
                strings[i] += arrayList.get(array) + "\n";
                array++;
            }
        }
        return strings;
    }

    /**
     * make String with ArrayList add '\n' in the end of each ArrayList component
     * @param arrayList content of ArrayList
     * @return String of TextArea content
     * */
    private String arrayListToString(ArrayList<String> arrayList) {
        String s = new String();
        for (String s1 : arrayList) {
            s += s1 + "\n";
        }
        return s;
    }

    /**
     * make ArrayList with split String in TextArea with '\n'
     * @param s string in TextArea
     * @return ArrayList that split String with '\n'
     * */
    private ArrayList<String> stringToArrayList(String s) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] strings = s.split("\n");
        for (int i = 0, n = strings.length; i < n; i++) {
            arrayList.add(strings[i]);
        }
        return arrayList;
    }

    /**
     * get text_block_index. text_block_index is representing which index's of block is selected
     * @return int text_block_index
     * */
    public int getTextBlockIndex(){
        return text_block_index;
    }

    /**
     * set text_block_index to parameter
     * @param t_index change text_block_index to t_index
     * */
    public void setTextBlockIndex(int t_index) { text_block_index = t_index; }

    /**
     * get Buttons' able / disable condition
     * @return boolean array of boolean values of Buttons' able / disable condition
     * */
    public boolean[] getButtonDisabled(){
        boolean[] conditionButton = new boolean[10];
        conditionButton[0] = next_difference_button.isDisable();
        conditionButton[1] = previous_difference_button.isDisable();
        conditionButton[2] = first_difference_button.isDisable();
        conditionButton[3] = now_difference_button.isDisable();
        conditionButton[4] = last_difference_button.isDisable();
        conditionButton[5] = copy_to_right_button.isDisable();
        conditionButton[6] = copy_to_left_button.isDisable();
        conditionButton[7] = copy_to_right_all_button.isDisable();
        conditionButton[8] = copy_to_left_all_button.isDisable();
        conditionButton[9] = compare_button.isDisable();

        return conditionButton;


    }

    /**
     * get now_tab_num value. now_tab_num notice that which tab is now selected
     * @return now_tab_num
     * */
    public int getNowTabNum(){
        return now_tab_num;

    }

    /**
     * get teb_num value. tab num value is related to making tab
     * @return tab_num
     * */
    public int getTabNum(){
        return tab_num;
    }
}