package View.Test;

import View.MainWindow;
import View.OpenFileWindow;
import com.google.common.util.concurrent.SettableFuture;
import com.sun.javafx.scene.control.skin.ContextMenuContent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;

/**
 * Created by woojin on 2016-05-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainWindowTest extends GuiTest {
    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

    protected static class TestMainWindow extends MainWindow {
        public TestMainWindow() {
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

        FXTestUtils.launchApp(MainWindowTest.TestMainWindow.class); // You can add start parameters here
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

    @Test
    public void stage0_testInitToolBarButtonSetting(){
        assertTrue(GuiTest.find("#compare_button").isDisable());
        assertTrue(GuiTest.find("#next_difference_button").isDisable());
        assertTrue(GuiTest.find("#previous_difference_button").isDisable());
        assertTrue(GuiTest.find("#first_difference_button").isDisable());
        assertTrue(GuiTest.find("#now_difference_button").isDisable());
        assertTrue(GuiTest.find("#last_difference_button").isDisable());
        assertTrue(GuiTest.find("#copy_to_left_button").isDisable());
        assertTrue(GuiTest.find("#copy_to_left_all_button").isDisable());
        assertTrue(GuiTest.find("#copy_to_right_button").isDisable());
        assertTrue(GuiTest.find("#copy_to_right_all_button").isDisable());
        System.out.println("Check all toolbar button is disabled");
        //버튼이 모두 disable 인지 test
    }
    @Test
    public void stage0_testInitFileMenuTest(){
        click("#file_menu");
        MenuItem new_tab = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#new_tab_menu_item")).getItem();
        MenuItem open = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#open_menu_item")).getItem();
        MenuItem close = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#close_menu_item")).getItem();
        MenuItem save = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#save_menu_item")).getItem();
        MenuItem save_right = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#save_right_file_menu_item")).getItem();
        MenuItem save_left = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#save_left_file_menu_item")).getItem();

        assertFalse(new_tab.isDisable());
        assertFalse(open.isDisable());
        assertFalse(close.isDisable());
        assertTrue(save.isDisable());
        assertTrue(save_left.isDisable());
        assertTrue(save_right.isDisable());
    }
    @Test
    public void stage0_testInitMergeMenuTest(){
        click("#merge_menu");
        MenuItem next = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#next_difference_menu_item")).getItem();
        MenuItem previous = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#previous_difference_menu_item")).getItem();
        MenuItem first = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#first_difference_menu_item")).getItem();
        MenuItem now = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#now_difference_menu_item")).getItem();
        MenuItem last = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#last_difference_menu_item")).getItem();
        MenuItem copy_left = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#copy_to_left_menu_item")).getItem();
        MenuItem copy_right = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#copy_to_right_menu_item")).getItem();
        MenuItem copy_left_all = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#copy_to_left_all_menu_item")).getItem();
        MenuItem copy_right_all = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#copy_to_right_all_menu_item")).getItem();
        MenuItem compare = ((ContextMenuContent.MenuItemContainer)GuiTest.find("#compare_menu_item")).getItem();

        assertTrue(next.isDisable());
        assertTrue(previous.isDisable());
        assertTrue(first.isDisable());
        assertTrue(now.isDisable());
        assertTrue(last.isDisable());
        assertTrue(copy_left.isDisable());
        assertTrue(copy_right.isDisable());
        assertTrue(copy_left_all.isDisable());
        assertTrue(copy_right_all.isDisable());
        assertTrue(compare.isDisable());
    }
    @Test
    public void stage1_testOpenWindow(){
        String file = "test-load.txt";
        testOpenWindowHotKey(); // 단축키 test
        click("#cancel_button");

        click("#file_menu").click("Open");
        click("#left_find_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type(file).type(KeyCode.ENTER);
        assertTrue(((TextField)GuiTest.find("#warning_info")).getText().equals("Select " + file));
        int size = ((TextField)GuiTest.find("#left_text_field")).getText().split(Pattern.quote(File.separator)).length;
        assertTrue(((TextField)GuiTest.find("#left_text_field")).getText().split(Pattern.quote(File.separator))[size-1].equals(file));

        click("#right_find_button");
        type(KeyCode.ESCAPE);
        assertTrue(((TextField)GuiTest.find("#warning_info")).getText().equals("Select No Right File"));

        click("#right_find_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type(file).type(KeyCode.ENTER);
        size = ((TextField)GuiTest.find("#right_text_field")).getText().split(Pattern.quote(File.separator)).length;
        assertTrue(((TextField)GuiTest.find("#right_text_field")).getText().split(Pattern.quote(File.separator))[size-1].equals(file));
        assertTrue(((TextField)GuiTest.find("#warning_info")).getText().equals("Select " + file));

        click("#cancel_button");
        assertNodeExists("#alarm_window");
        assertTrue(((Label)GuiTest.find("#alarm_label")).getText().equals("Wouldn't you open this file?"));
        click("#no_button");
        click("#ok_button");

        assertEquals("Testing load button!\n",((TextArea)GuiTest.find("#left_text_area")).getText());
        assertEquals("Testing load button!\n",((TextArea)GuiTest.find("#right_text_area")).getText());
    }
    private void testOpenWindowHotKey(){
        System.out.println("Type HotKey : Ctrl + L");
        press(KeyCode.CONTROL).type(KeyCode.L).release(KeyCode.CONTROL);
    }
    @Test
    public void stage2_testLeftSaveMenuItem(){
        if(GuiTest.find("#left_save_button").isDisable()){
            if(GuiTest.find("#left_edit_button").isDisable()) {
                loadLeftFile();
            }
            click("#left_edit_button");
        }
        testSaveLeftFileHotKey();
        assertNodeExists("#alarm_window");
        click("#yes_button");
        assertTrue(GuiTest.find("#left_edit_button").isDisable());

        if(GuiTest.find("#left_save_button").isDisable()){
            if(GuiTest.find("#left_edit_button").isDisable()) {
                loadLeftFile();
            }
            click("#left_edit_button");
        }
        click("#file_menu").click("Save Left File");
        click("#no_button");
        assertFalse(GuiTest.find("#left_edit_button").isDisable());

        click("#file_menu").click("Save Left File");
        click("#yes_button");
        assertTrue(GuiTest.find("#left_edit_button").isDisable());
    }
    private void testSaveLeftFileHotKey(){
        System.out.println("Type Save left file HotKey : Ctrl + Alt + L");
        press(KeyCode.CONTROL).press(KeyCode.ALT).type(KeyCode.L).release(KeyCode.CONTROL).release(KeyCode.ALT);
    }
    private void loadLeftFile(){
        click("#left_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-compare1.txt").type(KeyCode.ENTER);
    }

    @Test
    public void stage2_testRightSaveMenuItem(){

    }
    private void loadRightFile(){
        click("#right_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-compare2.txt").type(KeyCode.ENTER);
    }
    @Test
    public void testCompareButton()
    {
        click("#compare_button");
        assertNodeExists("Click");
    }
    @Test
    public void testNextDifferenceButton()
    {
        click("#next_difference_button");
        assertNodeExists("Click");
    }
    @Test
    public void testPostDifferenceButton()
    {
        click("#post_difference_button");
        assertNodeExists("Click");
    }
    @Test
    public void testFirstDifferenceButton()
    {
        click("#first_difference_button");
        assertNodeExists("Click");
    }
    @Test
    public void testNowDifferenceButton()
    {
        click("#now_difference_button");
        assertNodeExists("Click");
    }
    @Test
    public void testLastDifferenceButton()
    {
        click("#last_difference_button");
        assertNodeExists("Click");
    }
    @Test
    public void testCopyToRightButton()
    {
        click("#copy_to_right_button");
        assertNodeExists("Click");
    }
    @Test
    public void testCopyToLeftButton()
    {
        click("#copy_to_left_button");
        assertNodeExists("Click");
    }
    @Test
    public void testCopyToRightAllButton()
    {
        click("#copy_to_right_all_button");
        assertNodeExists("Click");
    }
    @Test
    public void testCopyToLeftAllButton()
    {
        click("#copy_to_left_all_button");
        assertNodeExists("Click");
    }
}
