package View;

import Controller.SaveFileWindowController;

/**
 * Created by woojin on 2016-05-18.
 */
public class SaveFileWindow extends AbstractFileWindow {

    public SaveFileWindow() {
        super("SaveFileWindow",new SaveFileWindowController());
        initLabel();
    }
    @Override
    protected void initLabel() {
        getWindowLabel().setText("Select Directory / File Name");
    }
}