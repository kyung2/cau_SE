package Controller;

import Model.ModelInterface;
import Model.ModelRealize;
import View.AlarmWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.easymock.internal.matchers.Null;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * SplitFilePaneController
 * Created by woojin on 2016-05-16.
 * @author Woojin
 */
public class SplitFilePaneController implements Initializable, SplitFilePaneInterface {
    @FXML
    TextArea left_text_area, right_text_area;
    @FXML
    private Button left_load_button,left_edit_button,left_save_button,
                    right_load_button,right_save_button,right_edit_button;
    @FXML
    private ListView left_text_list, right_text_list;
    @FXML
    private SplitPane split_pane;
    @FXML
    private Label left_file_label, right_file_label;
    @FXML
    private TextArea left_status_pane, right_status_pane;

    private int tab_num;
    private boolean left_file_chooser_flag = true, right_file_chooser_flag = true;
    private Button compare_button, previous_difference_button, next_difference_button;
    private MenuBar menu_bar;
    private MenuItem previous_menu_item, next_menu_item, compare_menu_item, open_menu_item, save_menu_item, save_right_file_menu_item, save_left_file_menu_item;
    private StatusController left_status, right_status;
    /**
    * 기본적으로
    * file pane 의 버튼은 로드 활성화. 수정 비활성화, 저장 비활성화
    * toolbar 의 버튼은 모두 비활성화
    * */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tab_num = -1;
        /*
        setClickableButtons("right","true","false","false");
        setClickableButtons("left","true","false","false");
        */

        left_text_list.setDisable(true);
        left_text_list.setVisible(false);
        right_text_list.setDisable(true);
        right_text_list.setVisible(false);

        left_text_area.setEditable(false);
        right_text_area.setEditable(false);

        compare_button = null;
        menu_bar = null;

        left_status = new StatusController(left_status_pane);
        right_status = new StatusController(right_status_pane);
    }

    /**
     * left
     * compare 버튼을 할당되있지 않을 경우 할당
     * 파일을 읽어서 내용이 있을 경우 edit 버튼 활성화
     * load 버튼을 누를 경우 파일을 읽어서 text area 에 파일 내용을 적는다.
     * text area 를 visible 하게 바꾼다.
     * 이미 파일이 있을 경우는 덮어쓰기
     **/
    @FXML
    public void leftLoadButtonOnAction() {
        if(left_file_chooser_flag) {
            checkTabNumAndCompareButtonAndMenuBar();

            FileChooser fileChooser = customFileChooser("Left File FileChooser");
            left_file_chooser_flag = false;
            File file = fileChooser.showOpenDialog(null);
            left_file_chooser_flag = true;

            Model.ModelInterface model = ModelRealize.getInstance();
            if (file != null) {
                try {
                    model.readTextOuter(tab_num, file.getAbsolutePath(), 0);
                    left_text_area.setVisible(true);
                    left_text_area.setText(arrayListToString(model.getText(tab_num, 0)));
                    setClickableButtons("left", "true", "true", "false");
                    changeTabName(file.getName(), "left");
                    disableAllButtonInToolBarAndMenuItem();
                    left_file_label.setText(file.getName());
                    left_status.setFileName(file.getName());

                    left_status.addStatusWithName("File open");
                    if (model.isOpen(tab_num, 1)) right_text_area.setText(arrayListToString(model.getText(tab_num, 1)));
                } catch (Exception e) {
                    e.printStackTrace();

                    left_status.addStatusWithName("ERR - " + e.getMessage());
                }
            }
            invisibleListViewVisibleTextArea();
            checkCompareButton();
        }
        else{
            System.out.println("Left File FileChooser is already open!");
        }
    }
    /**Right Load
     * compare 버튼을 할당되있지 않을 경우 할당
     * 파일을 읽어서 내용이 있을 경우 edit 버튼 활성화
     * load 버튼을 누를 경우 파일을 읽어서 text area 에 파일 내용을 적는다.
     * text area 를 visible 하게 바꾼다.
     * 이미 파일이 있을 경우는 덮어쓰기
     **/
    @FXML
    public void rightLoadButtonOnAction(){
        if(right_file_chooser_flag) {
            checkTabNumAndCompareButtonAndMenuBar();

            FileChooser fileChooser = customFileChooser("Light File FileChooser");
            right_file_chooser_flag = false;
            File file = fileChooser.showOpenDialog(null);
            right_file_chooser_flag = true;

            if (file != null) {
                Model.ModelInterface model = ModelRealize.getInstance();
                try {
                    model.readTextOuter(tab_num, file.getAbsolutePath(), 1);
                    right_text_area.setVisible(true);
                    right_text_area.setText(arrayListToString(model.getText(tab_num, 1)));
                    setClickableButtons("right", "true", "true", "false");
                    changeTabName(file.getName(), "right");
                    disableAllButtonInToolBarAndMenuItem();
                    right_file_label.setText(file.getName());
                    right_status.setFileName(file.getName());

                    right_status.addStatusWithName("File open");
                    if (model.isOpen(tab_num, 0)) left_text_area.setText(arrayListToString(model.getText(tab_num, 0)));
                } catch (Exception e) {
                    e.printStackTrace();

                    right_status.addStatusWithName("ERR - " + e.getMessage());
                }
            }
            invisibleListViewVisibleTextArea();
            checkCompareButton();
        }
        else{
            System.out.println("Right File FileChooser is already open");
        }
    }

    /**
     * left Edit
     * 기본은 text area 수정 불가
     * edit 버튼을 클릭하면 수정 가능하게 바꾸고 text area 를 visible 하게 바꾼다.
     * edit 이 가능할 때는 edit 와 save 버튼 말고는 모두 비활성화
     * 수정 가능한 상황에서 한 번 더 버튼을 누르면 다시 수정 불가
     * 그 후 비활성화 된 load 버튼을 활성화로
     * */
    @FXML
    public void leftEditButtonOnAction() {
        boolean edit_flag = left_text_area.isEditable();
        checkTabNumAndCompareButtonAndMenuBar();
        initStatusControl();
        if(edit_flag){
            /*
            * 수정이 가능할 때 - 누르면 수정이 불가능해짐. 로드는 가능해짐. 모델에 있는 정보를 바꿈,
            * 두 file pane 이 모두 수정 불가능 == save 가 비활성화 되있으면 compare 가능
            */
            left_text_area.setEditable(false);
            try {
                Model.ModelInterface model = ModelRealize.getInstance();
                model.setText(tab_num, stringToArrayList(left_text_area.getText()), 0);

                left_status.addStatusWithName("nonEditable");
            }catch (Exception e){
                e.printStackTrace();

                left_status.addStatusWithName("ERR - "+e.getMessage());
            }
            checkCompareButton();
            setClickableButtons("left","true",null,null);
            //hk - edit 비활성화 되시 원상태로 복귀
            left_edit_button.setStyle("-fx-background-color:transparent");
        }
        else{
            //수정이 불가능 할 때 = 누르면 수정이 가능. 로드 불가능, 저장 가능, compare 불가능
            Model.ModelInterface model = ModelRealize.getInstance();
            left_text_area.setText(arrayListToString(model.getText(tab_num,0)));
            if(!right_text_area.isEditable()) right_text_area.setText(arrayListToString(model.getText(tab_num,1)));
            left_text_area.setEditable(true);
            checkCompareButton();
            invisibleListViewVisibleTextArea();
            disableAllButtonInToolBarAndMenuItem();
            setClickableButtons("left","false",null,"true");
            //hk - edit이 활성화 될 시 하이라이팅 (이미지바꿀려면 저 setStyle메소드 이용하면됨)
            left_edit_button.setStyle("-fx-background-color: #a6a6a6");

            left_status.addStatusWithName("Editable");
        }
    }
    /**
     * right edit
     * 기본은 text area 수정 불가
     * edit 버튼을 클릭하면 수정 가능하게 바꾸고 text area 를 visible 하게 바꾼다.
     * edit 이 가능할 때는 edit 와 save 버튼 말고는 모두 비활성화
     * 수정 가능한 상황에서 한 번 더 버튼을 누르면 다시 수정 불가
     * 그 후 비활성화 된 load 버튼을 활성화로
     * */
    @FXML
    public void rightEditButtonOnAction() {
        boolean edit_flag = right_text_area.isEditable();
        checkTabNumAndCompareButtonAndMenuBar();
        initStatusControl();
        if(edit_flag){
            /*
            * 수정이 가능할 때 - 누르면 수정이 불가능해짐. 로드는 가능해짐. 모델에 있는 정보를 바꿈,
            * 두 file pane 이 모두 수정 불가능 == save 가 비활성화 되있으면 compare 가능
            */
            try {
                Model.ModelInterface model = ModelRealize.getInstance();
                model.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);

                right_status.addStatusWithName("nonEditable");
            }catch (Exception e){
                e.printStackTrace();

                right_status.addStatusWithName("ERR - "+e.getMessage());
            }
            right_text_area.setEditable(false);
            checkCompareButton();
            setClickableButtons("right","true",null,null);
            // hk - edit 비활성화 되시 원상태로 복귀
            right_edit_button.setStyle("-fx-background-color:transparent");
        }
        else{
            //수정이 불가능 할 때 - 누르면 수정 가능, 로드 불가능, 저장 가능
            Model.ModelInterface model = ModelRealize.getInstance();
            if(!left_text_area.isEditable()) left_text_area.setText(arrayListToString(model.getText(tab_num,0)));
            right_text_area.setText(arrayListToString(model.getText(tab_num,1)));
            right_text_area.setEditable(true);
            invisibleListViewVisibleTextArea();
            checkCompareButton();
            disableAllButtonInToolBarAndMenuItem();
            setClickableButtons("right","false",null,"true");
            // hk - edit이 활성화 될 시 하이라이팅 (이미지바꿀려면 저 setStyle메소드 이용하면됨)
            right_edit_button.setStyle("-fx-background-color: #a6a6a6");

            right_status.addStatusWithName("Editable");
        }
    }

    /**
     * left save
     * edit 버튼을 눌러서 수정이 가해진 상황 일 경우 save 버튼이 활성화 된다.
     * 수정 사항을 저장한 후에는 로드 가능, 수정 가늗, 저장 불가능으로 된다.
     * */
    @FXML
    public void leftSaveButtonOnAction(){
        checkTabNumAndCompareButtonAndMenuBar();
        initStatusControl();
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            try {
                Model.ModelInterface model = ModelRealize.getInstance();
                model.setText(tab_num, stringToArrayList(left_text_area.getText()), 0);
                model.writeTextOuter(tab_num, 0);
                left_text_area.setEditable(false);
                checkCompareButton();
                setClickableButtons("left", "true", null, "false");
                left_edit_button.setStyle("-fx-background-color:transparent");

                left_status.addStatusWithName("File save");
            }catch (Exception e){
                e.printStackTrace();

                left_status.addStatusWithName("ERR - "+e.getMessage());
            }
        }
    }
    /**
     * right save
     * edit 버튼을 눌러서 수정이 가해진 상황 일 경우 save 버튼이 활성화 된다.
     * 수정 사항을 저장한 후에는 로드 가능, 수정 가늗, 저장 불가능으로 된다.
     * */
    @FXML
    public void rightSaveButtonOnAction(){
        checkTabNumAndCompareButtonAndMenuBar();
        initStatusControl();
        AlarmWindow saveAlarmWindow = new AlarmWindow("Save File Alarm","Would you Save this file?");
        saveAlarmWindow.showAndWait();

        if((boolean)saveAlarmWindow.getUserData()) {
            try {
                ModelInterface modelInterface = ModelRealize.getInstance();
                modelInterface.setText(tab_num, stringToArrayList(right_text_area.getText()), 1);
                modelInterface.writeTextOuter(tab_num, 1);
                right_text_area.setEditable(false);
                checkCompareButton();
                setClickableButtons("right", "true", null, "false");
                right_edit_button.setStyle("-fx-background-color:transparent");

                right_status.addStatusWithName("File save");
            }catch (Exception e){
                e.printStackTrace();

                right_status.addStatusWithName("ERR - "+e.getMessage());
            }
        }
    }

    /**
     * left
     * 리스트 뷰를 클릭했을 때 일어나는 일
     * */
    @FXML
    public void onLeftListViewMouseClicked(){
        int index = left_text_list.getSelectionModel().getSelectedIndex();
        right_text_list.getSelectionModel().select(index);

        changeToolbarButtonByClickList(index);
        changeScrollbar(index);
    }
    /**
     * right
     * 리스트 뷰를 클릭했을 때 일어나는 일
     * */
    @FXML
    public void onRightListViewMouseClicked(){
        int index = right_text_list.getSelectionModel().getSelectedIndex();
        left_text_list.getSelectionModel().select(index);

        changeToolbarButtonByClickList(index);
        changeScrollbar(index);
    }

    /**
     * init Status.*/
    private void initStatusControl(){
        if (!left_edit_button.isDisable()) {
            Model.ModelInterface model = ModelRealize.getInstance();

            File left_file = new File(model.getUnit(tab_num).filepath(0));

            left_status.setFileName(left_file.getName());
        }
        if(!right_edit_button.isDisable()){
            Model.ModelInterface model = ModelRealize.getInstance();

            File right_file = new File(model.getUnit(tab_num).filepath(1));

            right_status.setFileName(right_file.getName());
        }
    }

    /**
    * change ListView's scrollbar to index
    * @param index block index that want to change scrollbar
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
            }
            else {
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
    * listView 를 선택한 위치에 대한
    * previous 버튼과 next 버튼의 able / disable
    * @param index 0 : left 1 right */
    private void changeToolbarButtonByClickList(int index){
        checkTabNumAndCompareButtonAndMenuBar();

        Model.ModelInterface model = ModelRealize.getInstance();
        ArrayList<Integer> text_index = model.getArrangedGroupSpace(tab_num);
        int text_index_size = text_index.size() - 1;

        if(text_index.get(0) == 0) {
            if(index % 2 == 1 ){
                disableCopyButtonInToolBarAndMenuItem();
            }
            else {
                ableCopyButtonInToolBarAndMenuItem();
                if (index == 0) {
                    preDifferenceButtonAndMenuItem(false);
                    if (text_index_size < 2) nextDifferenceButtonAndMenuItem(false);
                    else nextDifferenceButtonAndMenuItem(true);
                } else if (index == text_index_size - 1 || index == text_index_size - 2) {
                    preDifferenceButtonAndMenuItem(true);
                    nextDifferenceButtonAndMenuItem(false);
                } else {
                    preDifferenceButtonAndMenuItem(true);
                    nextDifferenceButtonAndMenuItem(true);
                }
            }
        }
        else{
            if(index % 2 == 0 ){
                disableCopyButtonInToolBarAndMenuItem();
            }
            else {
                ableCopyButtonInToolBarAndMenuItem();
                if (index == 1) {
                    preDifferenceButtonAndMenuItem(false);
                    if (text_index_size < 3) nextDifferenceButtonAndMenuItem(false);
                    else nextDifferenceButtonAndMenuItem(true);
                } else if (index == text_index_size || index == text_index_size - 1) {
                    preDifferenceButtonAndMenuItem(true);
                    nextDifferenceButtonAndMenuItem(false);
                } else {
                    preDifferenceButtonAndMenuItem(true);
                    nextDifferenceButtonAndMenuItem(true);
                }
            }
        }
    }

    /**
     *  custome FileChooser 를 만든다.
     *  @param title load / save
     * */
    FileChooser customFileChooser(String title){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files","*.txt", "*.java", "*.c", "*.cpp"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Java Files", "*.java"),
                new FileChooser.ExtensionFilter("C Files", "*.c","*.cpp")
        );

        File currentDirFile = new File(".");
        String init_path = currentDirFile.getAbsolutePath();
        init_path = init_path.substring(0, init_path.length() - 2);

        //String init_path = System.getProperty("user.home")+File.separator+"Documents";

        fileChooser.setInitialDirectory(new File(init_path));
        return fileChooser;
    }

    /**
     * control preDiffButton's able / disable condition
     * @param pre preDiff Button's able / disable condition
     * */
    private void preDifferenceButtonAndMenuItem(boolean pre){
        previous_menu_item.setDisable(!pre);
        previous_difference_button.setDisable(!pre);
    }

    /**
     * control nextdiff Button's able / disable condition
     * @param next nextDiff Button's able / disable condition
     * */
    private void nextDifferenceButtonAndMenuItem(boolean next){
        next_menu_item.setDisable(!next);
        next_difference_button.setDisable(!next);
    }

    /**
    * file pane 버튼의 able 과 disable 을 해준다.
    * position 에 left 와 right 를 통해서 위치를 선택
    * 각각 load, edit, save에 true || false 를 통해서 able 과 disable 을 한다.
    * null 일 경우 그 버튼은 현상 유지
    * 버튼들과 연관된 menu item 의 활성화와 비활성화를 조절
    * @param position left?right?
     * @param load load
     * @param edit save
     * @param save edit
     */
    private void setClickableButtons(String position, String load, String edit, String save){
        boolean f_load, f_edit, f_save;
        f_load = load == "true" ? true : false;
        f_edit = edit == "true" ? true : false;
        f_save = save == "true" ? true : false;

        if(position == "left"){
            if(load != null) {
                System.out.println(f_load);
                left_load_button.setDisable(!f_load);
                if(!f_load) {
                    open_menu_item.setDisable(true);
                }
            }
            if(edit != null) {
                left_edit_button.setDisable(!f_edit);
                if(!f_edit) left_edit_button.setStyle("-fx-background-color:transparent");
            }
            if(save != null) {
                left_save_button.setDisable(!f_save);
                save_left_file_menu_item.setDisable(!f_save);
                if(!f_save) save_menu_item.setDisable(true);
            }
        }
        else{
            if(load != null) {
                right_load_button.setDisable(!f_load);
                if(!f_load) open_menu_item.setDisable(true);
            }
            if(edit != null) {
                right_edit_button.setDisable(!f_edit);
                if(!f_edit) right_edit_button.setStyle("-fx-background-color:transparent");

            }
            if(save != null) {
                right_save_button.setDisable(!f_save);
                save_right_file_menu_item.setDisable(!f_save);
                if(!f_save) save_menu_item.setDisable(true);
            }
        }
        Model.ModelInterface model = ModelRealize.getInstance();
        if(!left_load_button.isDisable() && !right_load_button.isDisable() ) {
             open_menu_item.setDisable(false);
        }
        if(!left_save_button.isDisable() && !right_save_button.isDisable() && model.isOpen(tab_num,0) && model.isOpen(tab_num,1)) save_menu_item.setDisable(false);
    }

    /**
    * compare 버튼이 활성화 되도 되는지 체크
    * edit 버튼이 눌렸을 때 두 text area 가 수정 불가능 한 경우 활성화
    * save 버튼이 눌렸을 때 두 text area 가 수정 불가능 한 경우 활성화
    * */
    private void checkCompareButton(){
        Model.ModelInterface model = ModelRealize.getInstance();
        try{
            if(model.isOpen(tab_num,0) && model.isOpen(tab_num,1)) {
                if (!right_text_area.isEditable() && !left_text_area.isEditable()) {
                    compare_button.setDisable(false);
                    compare_menu_item.setDisable(false);
                }
            }
        }catch (Exception e){
                e.printStackTrace();
        }
    }

    /**
    * Tab num 값을 확인한다.
    * -1 일 경우 초기값이므로 현재 tab 의 번호를 넣어준다.
    * */
    private void checkTabNumAndCompareButtonAndMenuBar(){
        if(tab_num == -1){
            tab_num = (int)getSelectedTab().getUserData();
            System.out.println(tab_num);
        }
        if(compare_button == null) {
            ToolBar toolBar = (ToolBar)((BorderPane)((BorderPane)split_pane.getScene().getRoot()).getCenter()).getTop();
            compare_button = (Button)toolBar.getItems().get(11);
            next_difference_button = (Button)toolBar.getItems().get(0);
            previous_difference_button = (Button)toolBar.getItems().get(1);

        }
        if(menu_bar == null){
            menu_bar = (MenuBar)((BorderPane)compare_button.getScene().getRoot()).getTop();
            Menu menu = menu_bar.getMenus().get(1);

            next_menu_item = menu.getItems().get(0);
            previous_menu_item = menu.getItems().get(1);
            compare_menu_item = menu.getItems().get(9);

            menu = menu_bar.getMenus().get(0);
            open_menu_item = menu.getItems().get(1);
            save_menu_item = menu.getItems().get(2);
            save_left_file_menu_item = menu.getItems().get(3);
            save_right_file_menu_item = menu.getItems().get(4);
        }
    }

    /**
    * copy 에 관련된 버튼과 menu item 을 disable 한다.
    * */
    private void disableCopyButtonInToolBarAndMenuItem(){
        ToolBar toolBar = (ToolBar)compare_button.getParent().getParent();
        for(int i=0, n=toolBar.getItems().size(); i<n; i++){
            if( i == 0 || i == 1 || i == 6 || i == 7 || i == 8 || i == 9) {
                ((Button) toolBar.getItems().get(i)).setDisable(true);
            }
        }
        Menu merge_menu = menu_bar.getMenus().get(1);
        for(int i=0,n = merge_menu.getItems().size(); i<n; i++){
            if( i == 0 || i == 1 || i == 5 || i == 6 || i == 7 || i == 8) {
                merge_menu.getItems().get(i).setDisable(true);
            }
        }
    }

    /**
    * copy 에 관련된 버튼과 menu item 을 disable 한다.
    * */
    private void ableCopyButtonInToolBarAndMenuItem(){
        ToolBar toolBar = (ToolBar)compare_button.getParent().getParent();
        for(int i=0, n=toolBar.getItems().size(); i<n; i++){
            if( i == 0 || i == 1 || i == 6 || i == 7 || i == 8 || i == 9) {
                ((Button) toolBar.getItems().get(i)).setDisable(false);
            }
        }
        Menu merge_menu = menu_bar.getMenus().get(1);
        for(int i=0,n = merge_menu.getItems().size(); i<n; i++){
            if( i == 0 || i == 1 || i == 5 || i == 6 || i == 7 || i == 8) {
                merge_menu.getItems().get(i).setDisable(false);
            }
        }
    }

    /**
    * Toolbar 에 있는 모든 버튼을 비활성화 시킨다.
    * file 을 수정할 경우 compare 를 다시 해야하기에 모든 버튼을 비활성화
    * MenuItem 중 필요 없는 것을 비활성화
    * */
    private void disableAllButtonInToolBarAndMenuItem(){
        ToolBar toolBar = (ToolBar)compare_button.getParent().getParent();
        for(int i=0, n=toolBar.getItems().size(); i<n; i++){
            if( i != 5 && i != 10) {
                ((Button) toolBar.getItems().get(i)).setDisable(true);
            }
        }
        Menu merge_menu = menu_bar.getMenus().get(1);
        for(int i=0,n = merge_menu.getItems().size(); i<n; i++){
            merge_menu.getItems().get(i).setDisable(true);
        }
    }

    /**
    * TabPane 에서 현재 선택 된 tab 을 가져온다.
    * */
    private Tab getSelectedTab(){
        Tab tab = null;
        TabPane tabPane = (TabPane)split_pane.getParent().getParent();
        for(int i=0,n=tabPane.getTabs().size(); i<n;i++){
            if(tabPane.getTabs().get(i).isSelected()){
                tab = tabPane.getTabs().get(i);
            }
        }
        return tab;
    }

    /**
    * ArrayList 로 들어온 문자를 \n 을 붙여 String 하나로 만든다.
    * @param arrayList want to change string
     * @return String result */
    private String arrayListToString(ArrayList<String> arrayList){
        String s = new String();
        for (String s1 : arrayList) {
            s += s1 + "\n";
        }
        return s;
    }

    /**
    * String 을 받아와서 \n 로 split 한 후 ArrayList 에 저장한다.
    * @param s want to change ArrayList
     * @return arrayList result*/
    private ArrayList<String> stringToArrayList(String s){
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] strings = s.split("\n");
        for (String string : strings) {
            arrayList.add(string);
        }
        return arrayList;
    }

    /**
    * 모든 List view 를 invisible 하게
    * 모든 Text view 를 visible 하게 바꾼다.
    * */
    private void invisibleListViewVisibleTextArea(){
        right_text_area.setVisible(true);
        right_text_list.setVisible(false);
        right_text_list.setDisable(true);
        left_text_area.setVisible(true);
        left_text_list.setVisible(false);
        left_text_list.setDisable(true);
    }

    /**
     * 파일 이름을 받아서 tab name 을 바꿔준다.
     * @param position left,right
     * @param name file_name
     */
    private void changeTabName(String name, String position){
        String tab_name = getSelectedTab().getText();
        String left_file_name = null;
        String right_file_name = null;

        ModelInterface model = ModelRealize.getInstance();

        if(position.equals("left")) {
            try{
                right_file_name = new File(model.getUnit(tab_num).filepath(1)).getName();
            }catch (Exception e){}

            if(right_file_name != null){
                tab_name = name + " - " + right_file_name;
            }
            else tab_name = name + " - ";

            getSelectedTab().setText(tab_name);
        }
        else {
            try {
                left_file_name = new File(model.getUnit(tab_num).filepath(0)).getName();

            }catch (Exception e){}

            if(left_file_name != null){
                tab_name = left_file_name + " - " + name;
            }
            else tab_name = " - " + name;

            getSelectedTab().setText(tab_name);
        }
    }

    /**
     * check Load state
     * @param position left pane right pane
     * @throws IllegalAccessException
     * @return true when disable state
     */
    public boolean isDisableLoad(String position) throws IllegalAccessException {
        if (position.equals("left")) {
           return left_load_button.isDisable();
             }
        else if (position.equals("right")) {
            return right_load_button.isDisable();
        }
        else {
            throw new IllegalAccessException();
        }
    }

    /**
     * check Edit state
     * @param position left pane right pane
     * @throws IllegalAccessException
     * @return true when disable state
     */
    public boolean isDisableEdit(String position) throws IllegalAccessException {
        if (position.equals("left")) {
            return left_edit_button.isDisable();

        }
        else if (position.equals("right")) {
            return right_edit_button.isDisable();
            }
        else {
            throw new IllegalAccessException();
        }
    }

    /**
     * check Save state
     * @param position left pane right pane
     * @throws IllegalAccessException
     * @return true when disable state
     */
    public boolean isDisableSave(String position) throws IllegalAccessException {
        if (position.equals("left")) {
            return left_save_button.isDisable();
            }
        else if (position.equals("right")) {
            return right_save_button.isDisable();
            }
        else {
            throw new IllegalAccessException();
        }
    }
}
