package Controller;

import Model.ModelRealize;
import View.MyListView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import Model.Model;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by woojin on 2016-05-18.
 */
public class SaveFileWindowController {
    private boolean item_flag = false;
    private Tab tab;
    private Label left_file_label, right_file_label;
    private Button left_load, left_edit, left_save, right_load, right_edit, right_save;
    private TextArea left_text_area, right_text_area;
    private TextArea left_file_bottom_text_area, right_file_bottom_text_area;
    private MyListView left_list_view, right_list_view;

    @FXML
    private AnchorPane file_anchor_pane;

    Model model = ModelRealize.getInstance();
    @FXML
    private void rightFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        loadFileChooser();
        System.out.println("click");
    }
    @FXML
    private void leftFileFindButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        System.out.println("click");
    }
    @FXML
    private void okButtonOnAction(){
        if(!item_flag){
            getTabContent();
            item_flag = true;
        }
        System.out.println("click");
    }
    @FXML
    private void cancelButtonOnAction(){
                System.out.println("click");
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

        right_file_label = (Label)right_file_button_tab.getChildren().get(3);
        right_load = (Button)right_file_button_tab.getChildren().get(0);
        right_edit = (Button)right_file_button_tab.getChildren().get(1);
        right_save = (Button)right_file_button_tab.getChildren().get(2);

        left_file_bottom_text_area = (TextArea)((SplitPane)left_pane.getChildren().get(1)).getItems().get(1);
        right_file_bottom_text_area = (TextArea)((SplitPane)right_pane.getChildren().get(1)).getItems().get(1);

        AnchorPane left_file_pane = (AnchorPane)((SplitPane)left_pane.getChildren().get(1)).getItems().get(0);
        AnchorPane right_file_pane = (AnchorPane)((SplitPane)right_pane.getChildren().get(1)).getItems().get(0);

        left_text_area = (TextArea)left_file_pane.getChildren().get(0);
        left_list_view = (MyListView)left_file_pane.getChildren().get(1);
        right_text_area = (TextArea)right_file_pane.getChildren().get(0);
        right_list_view = (MyListView)right_file_pane.getChildren().get(1);
    }
    /*
    * file chooser 를 연다.
    * */
    private File loadFileChooser(Button position){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile == null) {
            System.out.println("No Select FIle");
        }
        return selectedFile;
    }
}
