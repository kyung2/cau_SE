package View;

import Controller.OpenFileWindowController;
import javafx.scene.control.Tab;

/**
 * Open FileWindow
 * this class is related on OpenFileWindowController in /Controller class
 * Created by woojin on 2016-05-16.
 * @author Woonjin Jang
 */

public class OpenFileWindow extends AbstractFileWindow {
    private static boolean file_num = true;

    public OpenFileWindow(Tab tab) {
        super("Open Files", new OpenFileWindowController());
        if(file_num) {
            this.getScene().setUserData(tab);
            initLabel();
            file_num = false;
            this.showAndWait();
            file_num = true;
        }
        else{
            System.out.println("Open file window is already open.");
        }
    }

    @Override
    protected void initLabel(){
        getWindowLabel().setText("Select Directory / File");
    }
}