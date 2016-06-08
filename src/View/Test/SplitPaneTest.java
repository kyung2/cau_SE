package View.Test;

import View.MainWindow;
import com.google.common.util.concurrent.SettableFuture;

import com.sun.javafx.collections.UnmodifiableListSet;
import com.sun.javafx.scene.control.skin.VirtualScrollBar;
import javafx.geometry.Orientation;
import javafx.geometry.VerticalDirection;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesVisibleException;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import java.awt.*;
import java.awt.Label;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;

/**
 * Gui Test for split pane
 * Created by woojin on 2016-05-17.
 *
 * @author woojin Jang
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SplitPaneTest extends GuiTest {
    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

    /**
     * The type Test split pane.
     */
    protected static class TestSplitPane extends MainWindow {
        /**
         * Instantiates a new Test split pane.
         */
        public TestSplitPane() {
            super();
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            super.start(primaryStage);
            stageFuture.set(primaryStage);
        }
    }

    @Before
    @Override
    public void setupStage() throws Throwable {
        assumeTrue(!UserInputDetector.instance.hasDetectedUserInput());

        FXTestUtils.launchApp(TestSplitPane.class); // You can add start parameters here
        try {
            stage = targetWindow(stageFuture.get(25, TimeUnit.SECONDS));
            FXTestUtils.bringToFront(stage);
        } catch (Exception e) {
            throw new RuntimeException("Unable to show stage", e);
        }
    }

    @Override
    protected Parent getRootNode() {
        return stage.getScene().getRoot();
    }

    /**
     * Stage 0 test init button test.
     */
    @Test
    /**
     * test for button initial stage
     * load button is able
     * edit and save button is disable
     * */
    public void stage0_testInitButtonTest(){
        assertFalse(GuiTest.find("#left_load_button").isDisable());
        assertFalse(GuiTest.find("#right_load_button").isDisable());
        assertTrue(GuiTest.find("#left_edit_button").isDisable());
        assertTrue(GuiTest.find("#right_edit_button").isDisable());
        assertTrue(GuiTest.find("#left_save_button").isDisable());
        assertTrue(GuiTest.find("#right_save_button").isDisable());
    }

    /**
     * Stage 0 test init text area and list view.
     */
    @Test
    /**
     * test for text area and list view initial stage
     * text area is visible
     * list view is invisible
     * */
    public void stage0_testInitTextAreaAndListView(){
        assertTrue(GuiTest.find("#left_text_area").isVisible());
        assertFalse(((TextArea)GuiTest.find("#left_text_area")).isEditable());
        assertTrue(GuiTest.find("#right_text_area").isVisible());
        assertFalse(((TextArea)GuiTest.find("#right_text_area")).isEditable());
        assertTrue(checkLeftListViewIsVisible());
        assertTrue(checkRightListViewIsVisible());
    }
    private boolean checkLeftListViewIsVisible(){
        try{
            GuiTest.find("#left_list_view");
        }catch (NoNodesVisibleException e){
            return true;
        }
        return false;
    }
    private boolean checkRightListViewIsVisible(){
        try{
            GuiTest.find("#right_list_view");
        }catch (NoNodesVisibleException e){
            return true;
        }
        return false;
    }

    /**
     * Stage 1 test left load button.
     */
    @Test
    /**
     * test left load button
     * get test-load.txt file and compare the value
     * testing label change to file name
     * */
    public void stage1_testLeftLoadButton () {
        click("#left_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-load.txt").type(KeyCode.ENTER);
        assertEquals("Testing load button!\n",((TextArea)GuiTest.find("#left_text_area")).getText());
        assertTrue(((javafx.scene.control.Label)GuiTest.find("#left_file_label")).getText().equals("test-load.txt"));
        assertFalse(GuiTest.find("#left_edit_button").isDisable());
    }

    /**
     * Stage 1 test right load button.
     */
    @Test
    /**
     * test right load button
     * get test-load.txt file and compare the value
     * testing label change to file name
     * */
    public void stage1_testRightLoadButton() {
        click("#right_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-load.txt").type(KeyCode.ENTER);
        assertEquals("Testing load button!\n",((TextArea)GuiTest.find("#right_text_area")).getText());
        assertTrue(((javafx.scene.control.Label)GuiTest.find("#right_file_label")).getText().equals("test-load.txt"));
        assertFalse(GuiTest.find("#right_edit_button").isDisable());
    }

    /**
     * Stage 2 test left edit button.
     */
    @Test
    /**
     * test left edit button
     * testing text area is editable
     * */
    public void stage2_testLeftEditButton() {
        if (GuiTest.find("#left_edit_button").isDisable()) {
            stage1_testLeftLoadButton();
        }
        click("#left_edit_button");
        assertFalse(GuiTest.find("#left_text_area").isDisable());
        assertTrue(GuiTest.find("#left_load_button").isDisable());
        assertFalse(GuiTest.find("#left_edit_button").isDisable());
        assertFalse(GuiTest.find("#left_save_button").isDisable());
    }

    /**
     * Stage 2 test right edit button.
     */
    @Test
    /**
     * test right edit button
     * testing text area is editable
     * */
    public void stage2_testRightEditButton() {
        if (GuiTest.find("#right_edit_button").isDisable()) {
            stage1_testRightLoadButton();
        }
        click("#right_edit_button");
        assertFalse(GuiTest.find("#right_text_area").isDisable());
        assertTrue(GuiTest.find("#right_load_button").isDisable());
        assertFalse(GuiTest.find("#right_edit_button").isDisable());
        assertFalse(GuiTest.find("#right_save_button").isDisable());
    }

    /**
     * Stage 3 test left save button.
     */
    @Test
    /**
     * test left save button
     * testing alarm window is open
     * */
    public void stage3_testLeftSaveButton() {
        if(GuiTest.find("#left_edit_button").isDisable()){
            stage1_testLeftLoadButton();
        }
        if(GuiTest.find("#left_save_button").isDisable()){
            stage2_testLeftEditButton();
        }
        click("#left_save_button");
        assertNodeExists("#alarm_window");
        click("#no_button");
        assertTrue(((TextArea)GuiTest.find("#left_text_area")).isEditable());
        assertTrue(GuiTest.find("#left_load_button").isDisable());
        assertFalse(GuiTest.find("#left_save_button").isDisable());

        click("#left_save_button");
        click("#yes_button");
        assertFalse(((TextArea)GuiTest.find("#left_text_area")).isEditable());
        assertFalse(GuiTest.find("#left_load_button").isDisable());
        assertTrue(GuiTest.find("#left_save_button").isDisable());
    }

    /**
     * Stage 3 test right save button.
     */
    @Test
    /**
     * test right save button
     * testing alarm window is open
     * */
    public void stage3_testRightSaveButton() {
        if(GuiTest.find("#right_edit_button").isDisable()) {
            stage1_testRightLoadButton();
        }
        if(GuiTest.find("#right_save_button").isDisable()){
            stage2_testRightEditButton();
        }
        click("#right_save_button");
        assertNodeExists("#alarm_window");
        click("#no_button");
        assertTrue(((TextArea)GuiTest.find("#right_text_area")).isEditable());
        assertTrue(GuiTest.find("#right_load_button").isDisable());
        assertFalse(GuiTest.find("#right_save_button").isDisable());

        click("#right_save_button");
        click("#yes_button");
        assertFalse(((TextArea)GuiTest.find("#right_text_area")).isEditable());
        assertFalse(GuiTest.find("#right_load_button").isDisable());
        assertTrue(GuiTest.find("#right_save_button").isDisable());
    }

    /**
     * Stage 4 test binding list view scroll bar.
     */
    @Test
    /**
     * test list view scroll
     * testing if one list view's scroll bar is down then also down the right list view's scroll
     * */
    public void stage4_testBindingListViewScrollBar(){
        ListView left_list = null, right_list;
        try{
            left_list = GuiTest.find("#left_list_view");
            right_list = GuiTest.find("#right_list_view");
        }catch(NoNodesVisibleException e){
            initForListView();
            left_list = GuiTest.find("#left_list_view");
            right_list = GuiTest.find("#right_list_view");
        }
        ScrollBar bar1 = null;
        ScrollBar bar2 = null;

        for (Node node : left_list.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
                bar1 = (ScrollBar)node;
            }
        }
        for (Node node : right_list.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
                bar2 = (ScrollBar)node;
            }
        }
        click(bar1);
        scroll(5, VerticalDirection.DOWN);
        assertTrue(bar1.getValue() == bar2.getValue());

        click(bar2);
        scroll(4,VerticalDirection.UP);
        assertTrue(bar1.getValue() == bar2.getValue());
    }
    private void initForListView(){
        click("#left_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-compare1.txt").type(KeyCode.ENTER);

        click("#right_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-compare2.txt").type(KeyCode.ENTER);

        click("#compare_button");
    }

    /**
     * Stage 4 test list view clicked.
     */
    @Test
    /**
     * test list view click
     * testing left list view's selected item's index is same with right list view's selected item's index
     * */
    public void stage4_testListViewClicked(){
        ListView left_list = null, right_list;
        try{
            left_list = GuiTest.find("#left_list_view");
            right_list = GuiTest.find("#right_list_view");
        }catch(NoNodesVisibleException e){
            initForListView();
            left_list = GuiTest.find("#left_list_view");
            right_list = GuiTest.find("#right_list_view");
        }
        click(left_list);
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0)==right_list.getSelectionModel().getSelectedIndices().get(0));

        click(right_list);
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0)==right_list.getSelectionModel().getSelectedIndices().get(0));
    }
}
