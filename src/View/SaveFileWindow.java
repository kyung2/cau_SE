package View;

import Controller.SaveFileWindowController;
import javafx.scene.control.Tab;

/**
 * SaveFileWindow
 * this class is related on SaveFileWindowController in /Controller package
 * Created by woojin on 2016-05-16.
 * @author Woonjin Jang
 */
public class SaveFileWindow extends AbstractFileWindow {
    private static boolean file_num = true;
    public SaveFileWindow(Tab tab) {
        super("Save Files", new SaveFileWindowController());

        if(file_num ) { // 한번에 하나의 창만 열리도록 함
            this.getScene().setUserData(tab);
            initLabel();
            file_num = false;
            this.showAndWait();
            file_num = true;
        }
        else{
            System.out.println("Save file window is already open!");
        }
    }
    @Override
    protected void initLabel() {
        getWindowLabel().setText("Select Directory / File Name");
    }

    public void setFileNum(){
        file_num = false;
    }
}