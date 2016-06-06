package View;

import Controller.OpenFileWindowController;
import javafx.scene.control.Tab;

/**
 * Open FileWindow
 * Created by woojin on 2016-05-16.
 * @author Woonjin Jang
 */

public class OpenFileWindow extends AbstractFileWindow {


    public OpenFileWindow(Tab tab) {
        super("Open Files",new OpenFileWindowController());
        this.getScene().setUserData(tab);
        initLabel();
    }

    @Override
    protected void initLabel(){
        getWindowLabel().setText("Select Directory / File");
    }
}