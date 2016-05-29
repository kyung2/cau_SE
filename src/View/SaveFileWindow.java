package View;

import Controller.SaveFileWindowController;
import javafx.scene.control.Tab;

/**
 * Created by woojin on 2016-05-18.
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