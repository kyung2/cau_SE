package Controller;

import Model.Model;
import Model.ModelException.MergeLineIllegalException;
import Model.ModelRealize;

import View.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button compare_button, copy_to_left_button, copy_to_right_button, copy_to_left_all_button, copy_to_right_all_button,
            next_difference_button, previous_difference_button, first_difference_button, now_difference_button, last_difference_button;
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

    private ArrayList<String[]> toolbar_stage = new ArrayList<String[]>();
    private int tab_num, now_tab_num, tab_menu_item_num, close_tab_num;

    private int text_block_index;
    ImageIcon img = new ImageIcon("/View/Image/sampleIcon.jpg");


    @Override
    /*
    * tab은 user data 를 통해 구별한다.
    * 현재 tab에 user data 를 저장한다.
    * tool bar의 버튼들을 모두 비활성화로 한다.
    * */
    public void initialize(URL location, ResourceBundle resources) {
        tab_num = 0;
        now_tab = tab;
        now_tab.setUserData(tab_num);
        tab_menu_item_num = 1;
        tab_menu_item.setOnAction(e -> tabMenuItemOnAction(0));
        setClickabeButtons("false", "false", "false", "false", "false", "false", "false", "false", "false", "false");
        toolbar_stage.add(new String[]{"false", "false", "false", "false", "false", "false", "false", "false", "false", "false"});

        left_text_list = null;
        right_text_list = null;
        left_text_area = null;
        right_text_area = null;
        try {
            Model model = ModelRealize.getInstance();
            model.newModel(tab_num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tab_pane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if (t1 != null) {
                            String[] stage = makeToolbarStage();
                            now_tab = t1;
                            toolbar_stage.set(now_tab_num, stage);
                            close_tab_num = now_tab_num;
                            now_tab_num = (int) now_tab.getUserData();
                            System.out.println("Change now tab num "+ close_tab_num +" to " + now_tab_num);
                            setClickabeButtons(toolbar_stage.get(now_tab_num)[0], toolbar_stage.get(now_tab_num)[1], toolbar_stage.get(now_tab_num)[2],
                                    toolbar_stage.get(now_tab_num)[3], toolbar_stage.get(now_tab_num)[4], toolbar_stage.get(now_tab_num)[5],
                                    toolbar_stage.get(now_tab_num)[6], toolbar_stage.get(now_tab_num)[7], toolbar_stage.get(now_tab_num)[8], toolbar_stage.get(now_tab_num)[9]);

                            initTextAreaAndListOnTab();
                        } else {
                            setClickabeButtons("false", "false", "false", "false", "false", "false", "false", "false", "false", "false");
                        }
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
    private void compareOnAction() {
        if (left_text_area == null || right_text_area == null || left_text_list == null || right_text_list == null) {
            initTextAreaAndListOnTab();
        }
        left_text_area.setVisible(false);
        right_text_area.setVisible(false);
        Model model = ModelRealize.getInstance();
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

            ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStinrgsForList(left_text, text_index));
            ObservableList<String> right_list_item = FXCollections.observableArrayList(makeStinrgsForList(right_text, text_index));
            left_text_list.setItems(left_list_item);
            right_text_list.setItems(right_list_item);



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
            if(left_list_item.size()==1) setClickabeButtons("false", "false", "false", "false", "false", "false", "false", "false", "false", "true");
            else setClickabeButtons("true", "false", "true", "true", "true", "true", "true", "true", "true", "true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void copyToLeftOnAction() {
        System.out.println("Click");
        Model model = ModelRealize.getInstance();
        try {
            text_block_index=left_text_list.getSelectionModel().getSelectedIndex();
            model.mergeByGroup(now_tab_num, text_block_index+(model.getArrangedGroupSpace(now_tab_num).get(0)==0?1:0), false);
        }
         catch (MergeLineIllegalException e) {
            e.printStackTrace();
        }
        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 0)));
        compareOnAction();
    }

    @FXML
    private void copyToRightOnAction() {
        System.out.println("Click");
        Model model = ModelRealize.getInstance();
        try {
            text_block_index=right_text_list.getSelectionModel().getSelectedIndex();
            model.mergeByGroup(now_tab_num, text_block_index+(model.getArrangedGroupSpace(now_tab_num).get(0)==0?1:0), true);
        }
        catch (MergeLineIllegalException e) {
            e.printStackTrace();
        }
        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 1)));
        compareOnAction();
    }

    @FXML
    private void copyToRightAllOnAction() {
        Model model = ModelRealize.getInstance();
        model.setText(now_tab_num,model.getText(now_tab_num,0),1);

        right_text_area.setText(arrayListToString(model.getText(now_tab_num, 0)));
        compareOnAction();
    }

    @FXML
    private void copyToLeftAllOnAction() {
        Model model = ModelRealize.getInstance();
        model.setText(now_tab_num,model.getText(now_tab_num,1),0);

        left_text_area.setText(arrayListToString(model.getText(now_tab_num, 1)));
        compareOnAction();
    }

    @FXML
    private void nextDifferenceOnAction() {
        Model model = ModelRealize.getInstance();
        ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStinrgsForList(model.getArrangedText(now_tab_num,0),model.getArrangedGroupSpace(now_tab_num)));

        //맨 마지막이면 비활성화 되어야함
        if(text_block_index+2<=left_list_item.size()-1) {
            text_block_index = left_text_list.getSelectionModel().getSelectedIndex() + 2;
            if(previous_difference_button.isDisable()) previous_difference_button.setDisable(false);
            if(text_block_index>=left_list_item.size()-2)
               next_difference_button.setDisable(true);
        }

        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);
    }

    @FXML
    private void previousDifferenceOnAction() {
        //left_text_list.getSelectionModel().;      `
        //맨 처음이면 비활성화 되어야함
        if(text_block_index-2>=0) {
            text_block_index = left_text_list.getSelectionModel().getSelectedIndex() - 2;
            if(next_difference_button.isDisable()) next_difference_button.setDisable(false);
            if(text_block_index<=1)
                previous_difference_button.setDisable(true);
        }
        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);
    }

    @FXML
    private void firstDifferenceOnAction() {
        //처음 차이점으로;
        Model model = ModelRealize.getInstance();
        ArrayList<Integer> text_index = model.getArrangedGroupSpace(now_tab_num);
        ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStinrgsForList(model.getArrangedText(now_tab_num,0),model.getArrangedGroupSpace(now_tab_num)));

        if(text_index.get(0) == 0) text_block_index = 0;
        else text_block_index = 1;

        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);

        // 이전 차이점 버튼 비활성화
        // + 맨 처음으로 돌아갔는데, 맨 마지막 차이점일 경우(차이나는 부분이 하나밖에 없을 경우)
        // 다음 차이점 버튼 비활성화
        previous_difference_button.setDisable(true);
        if(!(text_block_index==left_list_item.size()-2 || text_block_index==left_list_item.size()-1)) next_difference_button.setDisable(false);
        else next_difference_button.setDisable(true);
    }

    @FXML
    private void nowDifferenceOnAction() {
        now_difference_button.setText("");
        Model model = ModelRealize.getInstance();
        ArrayList<Integer> text_index = model.getArrangedGroupSpace(now_tab_num);
        ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStinrgsForList(model.getArrangedText(now_tab_num,0),model.getArrangedGroupSpace(now_tab_num)));

        //시점만을 현재 위치로 이동
        text_block_index=left_text_list.getSelectionModel().getSelectedIndex();
        System.out.println(text_index.get(0));
        if(text_index.get(0) == 1){ // 처음 cell 이 서로 동일할 때
            if(text_block_index % 2 == 0) text_block_index += 1;    // 짝수번째(같은 부분)를 focus 중 이면 다음 cell 을 현재 차이점으로
        }
        else{   // 처음 cell 이 서로 다를 대
            if(text_block_index %2 == 1) text_block_index += 1;     // 홀수번째(같은 부분)를 focus 중 이면 그 다음 cell 을 현재 차이점으로
        }
        if(left_text_list.getSelectionModel().getSelectedIndex()==0 || left_text_list.getSelectionModel().getSelectedIndex()==1)
            previous_difference_button.setDisable(true);
        else previous_difference_button.setDisable(false);

        if(left_text_list.getSelectionModel().getSelectedIndex()==left_list_item.size()-2 || left_text_list.getSelectionModel().getSelectedIndex()==left_list_item.size()-1)
            next_difference_button.setDisable(true);
        else next_difference_button.setDisable(false);
        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);
    }

    @FXML
    private void lastDifferenceOnAction() {
        //마지막 차이점으로
        Model model = ModelRealize.getInstance();
        ArrayList<Integer> text_index = model.getArrangedGroupSpace(now_tab_num);
        int text_index_size = text_index.size();
        System.out.println(text_index);
        System.out.println(text_index_size);
        if(text_index.get(0) == 0) {
            if(text_index_size % 2 == 0) {
                text_block_index = text_index_size - 2;
            }
            else{
                text_block_index = text_index_size - 3;
            }
        }
        else {
            if(text_index_size % 2 == 0) {
                text_block_index = text_index_size - 1 ;
            }
            else{
                text_block_index = text_index_size - 2;
            }
        }

        left_text_list.getSelectionModel ().select (text_block_index);
        right_text_list.getSelectionModel ().select (text_block_index);

        // 다음 차이점 버튼 비활성화
        // + 맨 끝으로 갔는데, 맨 처음 차이점일 경우(차이나는 부분이 하나밖에 없을 경우)
        // 이전 차이점 버튼 비활성화
        next_difference_button.setDisable(true);
        if(!(text_block_index==0 || text_block_index==1)) previous_difference_button.setDisable(false);
        else previous_difference_button.setDisable(true);
    }

    @FXML
    /*
    * new tab 을 누르면 fxml 로 부터 정보를 읽어온 후
    * tab을 하나 만들고 그 정보를 입력한다.
    * user data는 현재 tab num 에서 1을 더한 값을 저장한다.
    * tab 에 해당하는 새로운 모델을 하나 만든다
    * */
    private void newTabMenuItemOnAction() {
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
            Model model = ModelRealize.getInstance();
            model.newModel(tab_num);
        } catch (Exception e) {
            System.out.println(e);// 적절한 예외처리로 바꿔야함
        }

    }

    @FXML
    /*
    * open file window 를 연다.
    * */
    private void openMenuItemOnAction() {
        OpenFileWindow openFileWindow = new OpenFileWindow(now_tab);
        System.out.println("Open");
    }

    @FXML
    /*
    * save file window 를 연다.
    * */
    private void saveMenuItemOnAction() {
        SaveFileWindow saveFileWindow = new SaveFileWindow(now_tab);
        System.out.println("Save");
    }

    @FXML
    /*
    * 오늘쪽 파일을 저장한다.
    * */
    private void saveRightFileMenuItemOnAction() {
        if (left_text_area == null || right_text_area == null || left_text_list == null || right_text_list == null) {
            initTextAreaAndListOnTab();
        }

        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();
        if((boolean)saveAlarmWindow.getUserData()) {
            Model model = ModelRealize.getInstance();
            try {
                model.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
                model.writeTextOuter(tab_num, 1);
                right_text_area.setEditable(false);
                AnchorPane anchorPane = (AnchorPane) ((SplitPane) right_text_area.getParent().getParent().getParent()).getParent();
                AnchorPane button_area = (AnchorPane) anchorPane.getChildren().get(0);
                Button right_load = (Button) button_area.getChildren().get(1);
                Button right_edit = (Button) button_area.getChildren().get(2);
                Button right_save = (Button) button_area.getChildren().get(3);
                right_load.setDisable(false);
                right_save.setDisable(true);
                right_edit.setStyle("-fx-background-color:transparent");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                if(model.isOpen(tab_num,0) && model.isOpen(tab_num,1)) {
                    if (!right_text_area.isEditable() && !left_text_area.isEditable()) {
                        compare_button.setDisable(false);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    /*
    * 왼쪽 파일을 저장한다.
    * */
    private void saveLeftFileMenuItemOnAction() {
        if (left_text_area == null || right_text_area == null || left_text_list == null || right_text_list == null) {
            initTextAreaAndListOnTab();
        }

        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();
        if((boolean)saveAlarmWindow.getUserData()) {
            Model model = ModelRealize.getInstance();
            try {
                model.setText(tab_num, stringToArrayList(left_text_area.getText()), 0);
                model.writeTextOuter(tab_num, 0);
                left_text_area.setEditable(false);
                AnchorPane anchorPane = (AnchorPane) ((SplitPane) left_text_area.getParent().getParent().getParent()).getParent();
                AnchorPane button_area = (AnchorPane) anchorPane.getChildren().get(0);
                Button left_load = (Button) button_area.getChildren().get(1);
                Button left_edit = (Button) button_area.getChildren().get(2);
                Button left_save = (Button) button_area.getChildren().get(3);
                left_load.setDisable(false);
                left_save.setDisable(true);
                left_edit.setStyle("-fx-background-color:transparent");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                if(model.isOpen(tab_num,0) && model.isOpen(tab_num,1)) {
                    if (!right_text_area.isEditable() && !left_text_area.isEditable()) {
                        compare_button.setDisable(false);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    /*
    * main window 를 닫는다.
    * */
    private void closeMenuItemOnAction() {
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
    /*
    * program info window 를 연다.
    * */
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
    /*
    * tab 이 꺼지면 그에 해당하는 toolbar stage 의 값을 null 로 바꾼다.
    * 그 후 tab menu item 을 하나 없에고
    * 해당하는 모델을 닫는다.
    * */
    private void tabCloseAction() {
        System.out.println("Close tab num " + (close_tab_num));
        toolbar_stage.set(close_tab_num, null);
        tab_menu.getItems().remove(tab_menu_item_num + 1);
        tab_menu_item_num--;
        Model model = ModelRealize.getInstance();
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
    private void closeTabMenuItemOnAction() {
        close_tab_num = now_tab_num;
        tabCloseAction();
        ((TabPane) now_tab.getTabPane()).getTabs().remove(now_tab);
    }

    @FXML
    /*
    * 모든 tab 을 닫는다.
    * 모든 tab menu item 을 없에고 모든 toolbar stage 를 null 로 한다.
    * */
    private void closeTabAllMenuItemOnAction() {
        ((TabPane) now_tab.getTabPane()).getTabs().remove(0, tab_pane.getTabs().size());
        tab_menu.getItems().remove(2, tab_menu_item_num + 2);
        tab_menu_item_num = 0;
        Model model = ModelRealize.getInstance();
        model.closeModelAll();
        //removeAllToolbarStage 해야 함
    }

    @FXML
    /*
    * tab menu item 의 숫자에 맞게 tab 을 선택해 준다.
    * */
    private void tabMenuItemOnAction(int index) {
        tab_pane.getSelectionModel().select(index);
    }

    /*
    * Toolbar 에 있는 버튼들의 활성화와 비활성화를 조절한다.
    * 아무 행동도 하지 않으려면 null 을 입력하면 된다.
    * */
    private void setClickabeButtons(String next, String previous, String first, String now, String last, String ctor, String ctol, String ctora, String ctola, String compare) {
        if (next != null) {
            if (next == "true") next_difference_button.setDisable(false);
            else next_difference_button.setDisable(true);
        }
        if (previous != null) {
            if (previous == "true") previous_difference_button.setDisable(false);
            else previous_difference_button.setDisable(true);
        }
        if (first != null) {
            if (first == "true") first_difference_button.setDisable(false);
            else first_difference_button.setDisable(true);
        }
        if (now != null) {
            if (now == "true") now_difference_button.setDisable(false);
            else now_difference_button.setDisable(true);
        }
        if (last != null) {
            if (last == "true") last_difference_button.setDisable(false);
            else last_difference_button.setDisable(true);
        }
        if (ctor != null) {
            if (ctor == "true") copy_to_right_button.setDisable(false);
            else copy_to_right_button.setDisable(true);
        }
        if (ctol != null) {
            if (ctol == "true") copy_to_left_button.setDisable(false);
            else copy_to_left_button.setDisable(true);
        }
        if (ctora != null) {
            if (ctora == "true") copy_to_right_all_button.setDisable(false);
            else copy_to_right_all_button.setDisable(true);
        }
        if (ctola != null) {
            if (ctola == "true") copy_to_left_all_button.setDisable(false);
            else copy_to_left_all_button.setDisable(true);
        }
        if (compare != null) {
            if (compare == "true") compare_button.setDisable(false);
            else compare_button.setDisable(true);
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
    }

    /*
    * String 을 저장한 array list 와 index를 저장한 array list 를 통해 list view 에 넣을 string 배열을 만든다.
    * */
    private String[] makeStinrgsForList(ArrayList<String> arrayList, ArrayList<Integer> index) {
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
}

/*
 ImageIcon img = new ImageIcon("/View/Image/sampleIcon.jpg");

 public class JavaFX_ApplicationIcon extends Application {

@Override
public void start(final Stage primaryStage) {

Button btn = new Button();
btn.setText("Say 'Hello World'");
btn.setOnAction(new EventHandler<ActionEvent>() {

@Override
public void handle(ActionEvent event) {
System.out.println("Hello World!");

//load another image from internet
//and dynamically add it as new apllication icon
Image anotherIcon = new Image("http://goo.gl/kYEQl");
primaryStage.getIcons().add(anotherIcon);
}
});

StackPane root = new StackPane();
root.getChildren().add(btn);

Scene scene = new Scene(root, 300, 250);

//set icon of the application
Image applicationIcon = new Image(getClass().getResourceAsStream("dukes_36x36.png"));
primaryStage.getIcons().add(applicationIcon);

primaryStage.setTitle("Hello World!");
primaryStage.setScene(scene);
primaryStage.show();
}

public static void main(String[] args) {
launch(args);
}

}

 http://java-buddy.blogspot.kr/2013/11/javafx-example-how-to-set-icon-of.html

 아이콘 이미지 바꾸어주는것 : 버튼 누르면 개귀엽
 도전 한ㅂㄴ merger 버튼 누르면 색깔 바꾸는 것으로 .. ?ㅎㅎ
 merge 가근데 오랫동안 안걸릴것같으니 포기

 */