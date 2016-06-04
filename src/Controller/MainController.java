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

public class MainController implements Initializable, MainInterface {
    @FXML
    private Button compare_button, copy_to_left_button, copy_to_right_button, copy_to_left_all_button, copy_to_right_all_button,
            next_difference_button, previous_difference_button, first_difference_button, now_difference_button, last_difference_button;
    @FXML
    private MenuItem open_menu_item, save_menu_item, save_right_file_menu_item, save_left_file_menu_item;
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
    private MenuItem tab_menu_item;
    @FXML
    BorderPane main_pane;

    private Tab now_tab;
    private ListView left_text_list, right_text_list;
    private TextArea left_text_area, right_text_area;

    private StatusControll left_status, right_status;

    private ArrayList<String[]> toolbar_stage = new ArrayList<String[]>();
    private int tab_num, now_tab_num, tab_menu_item_num, close_tab_num;

    private int text_block_index;
    ImageIcon img = new ImageIcon("/View/Image/sampleIcon.jpg");


    @Override
    /*
    * tab은 user data 를 통해 구별한다.
    * 현재 tab에 user data 를 저장한다.
    * tool bar의 버튼들을 모두 비활성화로 한다.
    * 버튼과 관련된 menu item 들을 비활성화한다.
    * */
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

        left_text_list = null;
        right_text_list = null;
        left_text_area = null;
        right_text_area = null;
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
                        close_tab_num = now_tab_num;
                        now_tab_num = (int) now_tab.getUserData();
                        System.out.println("Change now tab num "+ close_tab_num +" to " + now_tab_num);
                        setClickableButtonsAndMenuItems(toolbar_stage.get(now_tab_num)[0], toolbar_stage.get(now_tab_num)[1], toolbar_stage.get(now_tab_num)[2],
                                toolbar_stage.get(now_tab_num)[3], toolbar_stage.get(now_tab_num)[4], toolbar_stage.get(now_tab_num)[5],
                                toolbar_stage.get(now_tab_num)[6], toolbar_stage.get(now_tab_num)[7], toolbar_stage.get(now_tab_num)[8], toolbar_stage.get(now_tab_num)[9]);

                        initTextAreaAndListOnTab();
                    } else {
                        setClickableButtonsAndMenuItems("false", "false", "false", "false", "false", "false", "false", "false", "false", "false");
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
    public void compareOnAction() {
        if (left_text_area == null || right_text_area == null || left_text_list == null || right_text_list == null) {
            initTextAreaAndListOnTab();
        }
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
    public void copyToLeftOnAction() {
        System.out.println("Click");
        Model.ModelInterface model = ModelRealize.getInstance();
        try {
            text_block_index=left_text_list.getSelectionModel().getSelectedIndex();
            model.mergeByGroup(now_tab_num, text_block_index+(model.getArrangedGroupSpace(now_tab_num).get(0)==0?1:0), false);
            left_status.addStatus("Copy to left");
            right_status.addStatus("Copy to left");
            left_status.setMeueFlag();
            right_status.setMeueFlag();
        }
         catch (MergeLineIllegalException e) {
             e.printStackTrace();
             left_status.addStatusWithName("ERR - "+e.getMessage());
        }
        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 0)));

        compareOnAction();
    }

    @FXML
    public void copyToRightOnAction() {
        System.out.println("Click");
        Model.ModelInterface model = ModelRealize.getInstance();
        try {
            text_block_index=right_text_list.getSelectionModel().getSelectedIndex();
            model.mergeByGroup(now_tab_num, text_block_index+(model.getArrangedGroupSpace(now_tab_num).get(0)==0?1:0), true);
            left_status.addStatus("Copy to right");
            right_status.addStatus("Copy to right");
            left_status.setMeueFlag();
            right_status.setMeueFlag();

        }
        catch (MergeLineIllegalException e) {
            e.printStackTrace();
            right_status.addStatusWithName("ERR - "+e.getMessage());
        }
        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 1)));
        compareOnAction();
    }

    @FXML
    public void copyToRightAllOnAction() {
        Model.ModelInterface model = ModelRealize.getInstance();
        model.setText(now_tab_num, model.getText(now_tab_num,0),1);

        left_status.addStatus("Copy to right All");
        right_status.addStatus("Copy to right All");
        left_status.setMeueFlag();
        right_status.setMeueFlag();

        right_text_area.setText(arrayListToString(model.getText(now_tab_num, 0)));
        compareOnAction();
    }

    @FXML
    public void copyToLeftAllOnAction() {
        Model.ModelInterface model = ModelRealize.getInstance();
        model.setText(now_tab_num, model.getText(now_tab_num,1),0);

        left_status.addStatus("Copy to left All");
        right_status.addStatus("Copy to left All");
        left_status.setMeueFlag();
        right_status.setMeueFlag();

        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 1)));
        compareOnAction();
    }

    @FXML
    public void nextDifferenceOnAction() {
        Model.ModelInterface model = ModelRealize.getInstance();
        ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStringsForList(model.getArrangedText(now_tab_num,0), model.getArrangedGroupSpace(now_tab_num)));
        //맨 마지막이면 비활성화 되어야함
        if(text_block_index+2<=left_list_item.size()-1) {
            text_block_index = left_text_list.getSelectionModel().getSelectedIndex() + 2;
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
    public void previousDifferenceOnAction() {
        //left_text_list.getSelectionModel().;      `
        //맨 처음이면 비활성화 되어야함
        if(text_block_index-2>=0) {
            text_block_index = left_text_list.getSelectionModel().getSelectedIndex() - 2;
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
    }

    @FXML
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
        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);
    }

    @FXML
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
    }

    @FXML
    /*
    * new tab 을 누르면 fxml 로 부터 정보를 읽어온 후
    * tab을 하나 만들고 그 정보를 입력한다.
    * user data는 현재 tab num 에서 1을 더한 값을 저장한다.
    * tab 에 해당하는 새로운 모델을 하나 만든다
    * */
    public void newTabMenuItemOnAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Fxml/SplitFilePane.fxml"));
            Tab new_tab = new Tab("File");
            new_tab.setContent(root);
            new_tab.setUserData(++tab_num);
            new_tab.setOnClosed(e -> tabCloseAction());
            toolbar_stage.add(new String[]{"false", "false", "false", "false", "false", "false", "false", "false", "false", "false"});
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
    /*
    * open file window 를 연다.
    * */
    public void openMenuItemOnAction() {
        OpenFileWindow openFileWindow = new OpenFileWindow(now_tab);
        System.out.println("Open");
    }

    @FXML
    /*
    * save file window 를 연다.
    * */
    public void saveMenuItemOnAction() {
        SaveFileWindow saveFileWindow = new SaveFileWindow(now_tab);
        System.out.println("Save");
    }

    @FXML
    /*
    * 오늘쪽 파일을 저장한다.
    * */
    public void saveRightFileMenuItemOnAction() {
        if (left_text_area == null || right_text_area == null || left_text_list == null || right_text_list == null) {
            initTextAreaAndListOnTab();
        }

        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();
        if((boolean)saveAlarmWindow.getUserData()) {
            Model.ModelInterface model = ModelRealize.getInstance();
            try {
                model.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
                model.writeTextOuter(tab_num, 1);
                right_text_area.setEditable(false);
                setClickableButtonsInFilePane("right","true","false","false");

            } catch (Exception e) {
                e.printStackTrace();
                right_status.addStatusWithName("ERR - "+e.getMessage());
            }
            try{
                if(model.isOpen(tab_num,0) && model.isOpen(tab_num,1)) {
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
    /*
    * 왼쪽 파일을 저장한다.
    * */
    public void saveLeftFileMenuItemOnAction() {
        if (left_text_area == null || right_text_area == null || left_text_list == null || right_text_list == null) {
            initTextAreaAndListOnTab();
        }

        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();
        if((boolean)saveAlarmWindow.getUserData()) {
            Model.ModelInterface model = ModelRealize.getInstance();
            try {
                model.setText(tab_num, stringToArrayList(left_text_area.getText()), 0);
                model.writeTextOuter(tab_num, 0);
                left_text_area.setEditable(false);

                setClickableButtonsInFilePane("left", "true", "false", "false");
            } catch (Exception e) {
                e.printStackTrace();
                left_status.addStatusWithName("ERR - "+e.getMessage());
            }
            try{
                if(model.isOpen(tab_num,0) && model.isOpen(tab_num,1)) {
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
    /*
    * main window 를 닫는다.
    * */
    public void closeMenuItemOnAction() {
        /*
        여러가지 조건 필요
        EX> 저장되지 않은 상태라면 저장하고 종료를 묻는다.
            Compare 중 이라면 merge 하지않고 종료할 것인지 묻는다.
        * */
        ((Stage) (main_pane.getScene().getWindow())).close();// 종료 method
        System.out.println("");
    }

    @FXML
    /*
    * help window 를 연다.
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
    /*
    * program info window 를 연다.
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
    /*
    * tab 이 꺼지면 그에 해당하는 toolbar stage 의 값을 null 로 바꾼다.
    * 그 후 tab menu item 을 하나 없에고
    * 해당하는 모델을 닫는다.
    * */
    public void tabCloseAction() {
        System.out.println("Close tab num " + (close_tab_num));
        toolbar_stage.set(close_tab_num, null);
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
    /*
    * close tab 의 menu item 을 누르면 일어나는 일
    * 현재 선택된 tab 을 지우고
    * tab 이 닫힐 때 일어나는 일을 한다.
    * */
    public void closeTabMenuItemOnAction() {
        close_tab_num = now_tab_num;
        tabCloseAction();
        ((TabPane) now_tab.getTabPane()).getTabs().remove(now_tab);
    }

    @FXML
    /*
    * 모든 tab 을 닫는다.
    * 모든 tab menu item 을 없에고 모든 toolbar stage 를 null 로 한다.
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

    /*
    * tab menu item 의 숫자에 맞게 tab 을 선택해 준다.
    * */
    private void tabMenuItemOnAction(int index) {
        tab_pane.getSelectionModel().select(index);
    }

    /*
    *  compare 의 able / disable
    * */
    private void compareButtonAndMenuItem(boolean compare){
        compare_menu_item.setDisable(!compare);
        compare_button.setDisable(!compare);
    }
    /*
    *  previous difference 의 able / disable
    * */
    private void preDifferenceButtonAndMenuItem(boolean pre){
        previous_difference_menu_item.setDisable(!pre);
        previous_difference_button.setDisable(!pre);
    }
    /*
    *  next difference 의 able / disable
    * */
    private void nextDifferenceButtonAndMenuItem(boolean next){
        next_difference_menu_item.setDisable(!next);
        next_difference_button.setDisable(!next);
    }

    /*
    * Toolbar 에 있는 버튼들의 활성화와 비활성화를 조절한다.
    * 버튼과 관련된 menu item 들의 활성화와 비활성화를 조절한다.
    * 아무 행동도 하지 않으려면 null 을 입력하면 된다.
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

    /*
    * file pane 버튼의 able 과 disable 을 해준다.
    * position 에 left 와 right 를 통해서 위치를 선택
    * 각각 load, edit, save에 true || false 를 통해서 able 과 disable 을 한다.
    * null 일 경우 그 버튼은 현상 유지
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

    /*
    * 원하는 index 로 scrollbar 를 움직인다.
    * */
    private void changeScrollbar(int index){
        ModelInterface model = ModelRealize.getInstance();
        ArrayList<Integer> arrangedGroupSpace = model.getArrangedGroupSpace(tab_num);
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

    /*
    * 버튼들의 상태를 봐서 tool bar stage 를 만든다.
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

    /*
    * 현재 tab 을 통해서 현재 선택된 tab 의 text area 와 list view 를 가져온다.
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

        Model.ModelInterface model = ModelRealize.getInstance();

        File left_file = new File(model.getUnit(tab_num).filepath(0));
        File right_file = new File(model.getUnit(tab_num).filepath(1));

        left_status = new StatusControll((TextArea)(left_split_pane.getItems().get(1)));
        left_status.setFileName(left_file.getName());
        right_status = new StatusControll((TextArea)(right_split_pane.getItems().get(1)));
        right_status.setFileName(right_file.getName());
    }

    /*
    * String 을 저장한 array list 와 index를 저장한 array list 를 통해 list view 에 넣을 string 배열을 만든다.
    * */
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

    /*
    * ArrayList 로 들어온 문자를 \n 을 붙여 String 하나로 만든다.
    * */
    private String arrayListToString(ArrayList<String> arrayList) {
        String s = new String();
        for (String s1 : arrayList) {
            s += s1 + "\n";
        }
        return s;
    }

    /*
    * String 을 받아와서 \n 로 split 한 후 ArrayList 에 저장한다.
    * */
    private ArrayList<String> stringToArrayList(String s) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] strings = s.split("\n");
        for (int i = 0, n = strings.length; i < n; i++) {
            arrayList.add(strings[i]);
        }
        return arrayList;
    }

    public int getTextBlockIndex(){
        return text_block_index;
    }
}