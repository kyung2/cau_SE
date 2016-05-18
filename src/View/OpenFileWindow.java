package View;

import Controller.OpenFileWindowController;

import javax.swing.*;

/**
 * Created by woojin on 2016-05-16.
 */
public class OpenFileWindow extends AbstractFileWindow {


    public OpenFileWindow() {
        super("OpenFileWindow");
        addButtonAction();
    }

    @Override
    protected void addButtonAction() {
        OpenFileWindowController openFileWindowController = new OpenFileWindowController();

        getOkButton().setOnAction(event -> openFileWindowController.clickOkButton());
        getRightFileFindButton().setOnAction(event -> openFileWindowController.clickRightFileFindButton());
        getLeftFileFindButton().setOnAction(event -> openFileWindowController.clickLeftFileFindButton());
        getCancelButton().setOnAction(event -> openFileWindowController.clickCancelButton());
    }
}