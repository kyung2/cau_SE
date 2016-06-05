package View;

import Controller.SaveFileWindowController;
import javafx.scene.control.Tab;

/**
 * SaveFileWindow
 * Created by woojin on 2016-05-16.
 * @author Woonjin Jang
 */
public class SaveFileWindow extends AbstractFileWindow {

    public SaveFileWindow(Tab tab) {
        super("Save Files",new SaveFileWindowController());
        this.getScene().setUserData(tab);
        initLabel();
    }
    @Override
    protected void initLabel() {
        getWindowLabel().setText("Select Directory / File Name");
    }
}