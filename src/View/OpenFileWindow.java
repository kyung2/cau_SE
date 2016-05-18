package View;

import Controller.OpenFileWindowController;

/**
 * Created by woojin on 2016-05-16.
 */
public class OpenFileWindow extends abstractFileWindow {


    public OpenFileWindow() {
        super("OpenFileWindow",new OpenFileWindowController());
        initLabel();
    }

    @Override
    protected void initLabel(){
        getWindowLabel().setText("Compare Directory / File");
    }
}