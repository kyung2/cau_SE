package Controller;

import java.io.File;
import java.io.IOException;

import Model.Model;
import Model.ModelRealize;
import View.MyListView;
import View.OpenFileWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by woojin on 2016-05-16.
 */

public class OpenFileWindowController {
	@FXML
	private AnchorPane file_anchor_pane;
	@FXML
	private Button cancel_button,ok_button;
	@FXML
	private TextField right_file_text_area, left_file_text_area;
	
    private Button compare_button_file;
    
	private Tab tab;
    
	private Label left_file_label, right_file_label;
    
	private Button left_load, left_edit, left_save, right_load, right_edit, right_save;
    
	private TextArea left_text_area, right_text_area;
    
	private TextArea left_file_bottom_text_area, right_file_bottom_text_area;
    
	private MyListView left_list_view, right_list_view;

    Model modelLeft;
    Model modelRight;
    
    String fileRightname="";
    String fileLeftname="";


    @FXML
    private void leftFileFindButtonOnAction(){
        System.out.println("Lclick");
        int tab_num;
    	getTabContent();
        File file = loadFileChooser(left_load);

    	tab_num = (int)tab.getUserData();
        modelLeft = ModelRealize.getInstance();
        
        //�뙆�씪 �뿴由щ㈃
    	if(file != null){
            try {
               modelLeft.readTextOuter(tab_num, file.getAbsolutePath(), 0); //�꺆�꽆踰� �꽔湲�
               left_file_text_area.setText(file.getName()); // �뙣�뒪�씠由� �닔�젙�븯怨� �뿴�뼱留� �몺
               fileLeftname = file.getName();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    @FXML
    private void rightFileFindButtonOnAction(){
        System.out.println("Rclick");
    	int tab_num;
    	getTabContent();
        File file = loadFileChooser(right_load);
        
    	tab_num = (int)tab.getUserData();
        modelRight = ModelRealize.getInstance();
        //�뙆�씪 �뿴由щ㈃
    	if(file != null){
            try {
               modelRight.readTextOuter(tab_num, file.getAbsolutePath(), 1); //�꺆�꽆踰� �꽔湲�
               right_file_text_area.setText(file.getName()); // �뙣�뒪�씠由� �닔�젙�븯怨� �뿴�뼱留� �몺
               fileRightname = file.getName();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void okButtonOnAction() throws IndexOutOfBoundsException, IllegalAccessException, IOException{
       
        System.out.println("�뙆�씪遺덈윭�삤湲�");
        if(!fileRightname.equals("") && !fileLeftname.equals("")){
        //Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        //compare_button_file = (Button) root.lookup("#compare_button");
    	getTabContent();
        //�늻瑜대㈃ 硫붿씤 �쐢�룄�슦 踰꾪듉�쓽 �솢�룞�솕 �맂 遺�遺꾩쓣 蹂��솚�떆耳� 二쇨퀬 �뙆�씪�쓣 遺덈윭�삩�떎
        int tab_num = (int)tab.getUserData();
        //�뼇履� �뀓�뒪�듃 �쁺�뿭 蹂�寃�
        left_text_area.setVisible(true);
        right_text_area.setVisible(true);
        left_text_area.setText(arrayListToString(modelLeft.getText(tab_num,0)));
        right_text_area.setText(arrayListToString(modelRight.getText(tab_num,1)));
        
        //踰꾪듉 �솢�꽦�솕 議곗젅 tab.setClickableButtons("left","true","true","false"); 
        left_load.setDisable(false);
        left_edit.setDisable(false);
        left_save.setDisable(true);
        
        right_load.setDisable(false);
        right_edit.setDisable(false);
        right_save.setDisable(true);

        changeTabName(fileLeftname,"left"); 
        changeTabName(fileRightname,"right");
        left_file_label.setText(fileLeftname);
        right_file_label.setText(fileRightname);
        
        //compare_button_file.setDisable(false);
        }
        Stage stage = (Stage) ok_button.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    @FXML
    private void cancelButtonOnAction(){
        System.out.println("click");
        Stage stage = (Stage) cancel_button.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private File loadFileChooser(Button position){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        File selectedFile = fileChooser.showOpenDialog(null);
        //�꺆�꽆踰� 泥댄겕
        if(selectedFile == null) {
            System.out.println("No Select File");
        }
        return selectedFile;
    }
   
    private void getTabContent(){
        tab = (Tab)file_anchor_pane.getScene().getUserData();
        System.out.println(file_anchor_pane.getScene());
        System.out.println(tab);
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

    private String arrayListToString(ArrayList<String> arrayList){
        String s = new String();
        for (String s1 : arrayList) {
            s += s1 + "\n";
        }
        return s;
    }
    
    private void changeTabName(String name, String position){
        String tab_name = tab.getText();
        if(position.equals("left")) {
            if (tab_name.equals("File")) {
                tab_name = name + " - ";
                tab.setText(tab_name);
            }
            else{
                tab_name = name + " -" + tab_name.split("-")[1];
                tab.setText(tab_name);
            }
        }
        else {
            if (tab_name.equals("File")) {
                tab_name = " - " + name;
                tab.setText(tab_name);
            }
            else{
                tab_name = tab_name.split("-")[0] + "- " + name;
                tab.setText(tab_name);
            }
        }
    }
}
