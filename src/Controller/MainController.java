package Controller;

import Model.Model;
import Model.ModelRealize;

import View.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.Tab.SELECTION_CHANGED_EVENT;

public class MainController implements Initializable {
    @FXML
    private Button compare_button, copy_to_left_button,copy_to_right_button, copy_to_left_all_button, copy_to_right_all_button,
            next_difference_button, post_difference_button, first_difference_button, now_difference_button, last_difference_button;
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
    private MyListView left_text_list, right_text_list;
    private TextArea left_text_area, right_text_area;

    private ArrayList<String[]> toolbar_stage = new ArrayList<String []>();
    private int tab_num, now_tab_num, tab_menu_item_num;
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
        tab_menu_item.setUserData(tab_num);
        tab_menu_item_num = 1;
        tab_menu_item.setOnAction( e -> tabMenuItemOnAction(0));
        setClickabeButtons("false","false","false","false","false","false","false","false","false","false");
        toolbar_stage.add(new String[]{"false","false","false","false","false","false","false","false","false","false"});

        left_text_list = null;
        right_text_list = null;
        left_text_area = null;
        right_text_area = null;
        try {
            Model model = ModelRealize.getInstance();
            model.newModel(tab_num);
        }catch(Exception e){
            e.printStackTrace();
        }
        tab_pane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if(t1 != null) {
                            String[] stage = makeToolbarStage();
                            now_tab = t1;
                            toolbar_stage.set(now_tab_num, stage);
                            now_tab_num = (int) now_tab.getUserData();
                            setClickabeButtons(toolbar_stage.get(now_tab_num)[0], toolbar_stage.get(now_tab_num)[1], toolbar_stage.get(now_tab_num)[2],
                                    toolbar_stage.get(now_tab_num)[3], toolbar_stage.get(now_tab_num)[4], toolbar_stage.get(now_tab_num)[5],
                                    toolbar_stage.get(now_tab_num)[6], toolbar_stage.get(now_tab_num)[7], toolbar_stage.get(now_tab_num)[8], toolbar_stage.get(now_tab_num)[9]);

                            initTextAreaAndListOnTab();
                        }
                        else {
                            setClickabeButtons("false","false","false","false","false","false","false","false","false","false");
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
        if(left_text_area == null || right_text_area == null || left_text_list == null || right_text_list == null){
           initTextAreaAndListOnTab();
        }
        left_text_area.setVisible(false);
        right_text_area.setVisible(false);
        Model model = ModelRealize.getInstance();
        try {
            ArrayList<String> left_text = model.getArrangedText(now_tab_num, 0);
            ArrayList<String> right_text = model.getArrangedText(now_tab_num, 1);
            ArrayList<Integer> text_index = model.getArrangedGroupSpace(now_tab_num);

            ObservableList<String> left_list_item = FXCollections.observableArrayList(makeStinrgsForList(left_text,text_index));
            ObservableList<String> right_list_item = FXCollections.observableArrayList(makeStinrgsForList(right_text,text_index));
            left_text_list.setItems(left_list_item);
            right_text_list.setItems(right_list_item);
            setHighlight(text_index);

            left_text_list.setVisible(true);
            left_text_list.setDisable(false);
            right_text_list.setVisible(true);
            right_text_list.setDisable(false);

        }catch (Exception e){
            e.printStackTrace();
        }
        setClickabeButtons("true","true","true","true","true","true","true","true","true","true");
    }
    @FXML
    private void copyToLeftOnAction() {
        System.out.println("Click");
    }
    @FXML
    private void copyToRightOnAction() { copy_to_right_button.setText(""); }
    @FXML
    private void copyToRightAllOnAction() {
        copy_to_right_all_button.setText("");
    }
    @FXML
    private void copyToLeftAllOnAction(){
        copy_to_left_all_button.setText("");
    }
    @FXML
    private void nextDifferenceOnAction() { next_difference_button.setText(""); }
    @FXML
    private void postDifferenceOnAction() { post_difference_button.setText(""); }
    @FXML
    private void firstDifferenceOnAction() { first_difference_button.setText(""); }
    @FXML
    private void nowDifferenceOnAction() { now_difference_button.setText(""); }
    @FXML
    private void lastDifferenceOnAction() { last_difference_button.setText(""); }
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
            toolbar_stage.add(new String[]{"false","false","false","false","false","false","false","false","false","false"});
            tab_pane.getTabs().add(new_tab);
            MenuItem tab_menuitem = new MenuItem("Tab " + ++tab_menu_item_num);
            tab_menuitem.setUserData(tab_num);
            tab_menu.getItems().add(tab_menuitem);
            tab_menuitem.setOnAction(( e )  -> {
                tabMenuItemOnAction(Integer.parseInt(tab_menuitem.getText().split(" ")[1]) - 1);
            });
            Model model = ModelRealize.getInstance();
            model.newModel(tab_num);
        }catch (Exception e){
            System.out.println(e);// 적절한 예외처리로 바꿔야함
        }

    }
    @FXML
    private void openMenuItemOnAction() {
        OpenFileWindow openFileWindow = new OpenFileWindow(now_tab);
        System.out.println("Open");
    }
    @FXML
    private void saveMenuItemOnAction() {
        SaveFileWindow saveFileWindow = new SaveFileWindow(now_tab);
        System.out.println("Save");
    }
    @FXML
    private void saveRightFileMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void saveLeftFileMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void closeMenuItemOnAction() {
        /*
        여러가지 조건 필요
        EX> 저장되지 않은 상태라면 저장하고 종료를 묻는다.
            Compare 중 이라면 merge 하지않고 종료할 것인지 묻는다.
        * */
        ((Stage)(main_pane.getScene().getWindow())).close();// 종료 method
        System.out.println("");
    }
    @FXML
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
    * */
    private void tabCloseAction(){
        toolbar_stage.set(now_tab_num,null);
        System.out.println(tab_menu.getItems());
        tab_menu.getItems().remove(tab_menu_item_num + 1);
        System.out.println(tab_menu_item_num--);
        Model model = ModelRealize.getInstance();
        try {
            model.closeModel(now_tab_num);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void closeTabMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void closeTabAllMenuItemOnAction() { System.out.println(""); }
    @FXML
    private void tabMenuItemOnAction(int index){
        tab_pane.getSelectionModel().select(index);
    }
    /*
    * Toolbar 에 있는 버튼들의 활성화와 비활성화를 조절한다.
    * 아무 행동도 하지 않으려면 null 을 입력하면 된다.
    * */
    private void setClickabeButtons(String next, String post, String first, String now, String last, String ctor, String ctol, String ctora, String ctola, String compare){
        if(next != null){
            if(next == "true") next_difference_button.setDisable(false);
            else next_difference_button.setDisable(true);
        }
        if(post != null){
            if(post == "true") post_difference_button.setDisable(false);
            else post_difference_button.setDisable(true);
        }
        if(first != null){
            if(first == "true") first_difference_button.setDisable(false);
            else first_difference_button.setDisable(true);
        }
        if(now != null){
            if(now == "true") now_difference_button.setDisable(false);
            else now_difference_button.setDisable(true);
        }
        if(last != null){
            if(last == "true") last_difference_button.setDisable(false);
            else last_difference_button.setDisable(true);
        }
        if(ctor != null){
            if(ctor == "true") copy_to_right_button.setDisable(false);
            else copy_to_right_button.setDisable(true);
        }
        if(ctol != null){
            if(ctol == "true") copy_to_left_button.setDisable(false);
            else copy_to_left_button.setDisable(true);
        }
        if(ctora != null){
            if(ctora == "true") copy_to_right_all_button.setDisable(false);
            else copy_to_right_all_button.setDisable(true);
        }
        if(ctola != null){
            if(ctola == "true") copy_to_left_all_button.setDisable(false);
            else copy_to_left_all_button.setDisable(true);
        }
        if(compare != null){
            if(compare == "true") compare_button.setDisable(false);
            else compare_button.setDisable(true);
        }
    }
    /*
    * 버튼들의 상태를 봐서 tool bar stage 를 만든다.
    * */
    private String[] makeToolbarStage(){
        String[] stage = new String[10];
        if(!next_difference_button.isDisable()) { stage[0] = "true"; }
        else { stage[0] = "false"; }
        if(!post_difference_button.isDisable()) { stage[1] = "true"; }
        else { stage[1] = "false"; }
        if(!first_difference_button.isDisable()) { stage[2] = "true"; }
        else { stage[2] = "false"; }
        if(!now_difference_button.isDisable()) { stage[3] = "true"; }
        else { stage[3] = "false"; }
        if(!last_difference_button.isDisable()) { stage[4] = "true"; }
        else { stage[4] = "false"; }
        if(!copy_to_right_button.isDisable()) { stage[5] = "true"; }
        else { stage[5] = "false"; }
        if(!copy_to_left_button.isDisable()) { stage[6] = "true"; }
        else { stage[6] = "false"; }
        if(!copy_to_right_all_button.isDisable()) { stage[7] = "true"; }
        else { stage[7] = "false"; }
        if(!copy_to_left_all_button.isDisable()){ stage[8] = "true"; }
        else { stage[8] = "false"; }
        if(!compare_button.isDisable()) { stage[9] = "true"; }
        else { stage[9] = "false"; }
        return stage;
    }
    /*
    * 현재 tab 을 통해서 현재 선택된 tab 의 text area 와 list view 를 가져온다.
    * */
    private void initTextAreaAndListOnTab(){
        AnchorPane left_anchor_pane = (AnchorPane)((SplitPane)now_tab.getContent()).getItems().get(0);
        AnchorPane right_anchor_pane = (AnchorPane)((SplitPane)now_tab.getContent()).getItems().get(1);
        SplitPane left_split_pane = (SplitPane)((SplitPane)left_anchor_pane.getChildren().get(1));
        SplitPane right_split_pane = (SplitPane)((SplitPane)right_anchor_pane.getChildren().get(1));

        left_text_area = (TextArea) ((AnchorPane)left_split_pane.getItems().get(0)).getChildren().get(0);
        right_text_area = (TextArea) ((AnchorPane)right_split_pane.getItems().get(0)).getChildren().get(0);
        left_text_list = (MyListView)((AnchorPane)left_split_pane.getItems().get(0)).getChildren().get(1);
        right_text_list = (MyListView)((AnchorPane)right_split_pane.getItems().get(0)).getChildren().get(1);
    }
    /*
    * String 을 저장한 array list 와 index를 저장한 array list 를 통해 list view 에 넣을 string 배열을 만든다.
    * */
    private String[] makeStinrgsForList(ArrayList<String> arrayList, ArrayList<Integer> index){
        String[] strings;
        int flag = 0;

        if(index.get(0) == 0){
            flag = 1;
        }

        strings = new String[index.size() - flag];

        for (int i = 0, array = 0, n = strings.length; i < n; i++) {
            strings[i] = "";

            for(int j=0, m = index.get(i + flag); j < m; j++){
                strings[i] += arrayList.get(array) + "\n";
                array++;
            }
        }
        return strings;
    }
    /**/
    private void setHighlight(ArrayList<Integer> index){
        int count = 0;
        for (int i = 0, n = index.size(); i < n; i++) {
            if(i %2 != 0) {
                left_text_list.setColorsOnBlock(i, MyListView.Red);
                right_text_list.setColorsOnBlock(i, MyListView.Red);
            }
            else {
                left_text_list.setColorsOnBlock(i, MyListView.Green);
                right_text_list.setColorsOnBlock(i, MyListView.Green);
            }
        }
    }
}