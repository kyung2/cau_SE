package View.Test;

import View.MainWindow;
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
import org.loadui.testfx.exceptions.NoNodesVisibleException;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
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

        click("#file_menu");
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

        click("#merge_menu");
    }

    @Test
    public void stage1_testOpenWindow(){
        String file = "test-load.txt";
        testOpenWindowHotKey(); // 단축키 test
        assertNodeExists("#file_window");
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
        assertTrue(((Label)GuiTest.find("#left_file_label")).getText().equals("test-load.txt"));
        assertTrue(((Label)GuiTest.find("#right_file_label")).getText().equals("test-load.txt"));

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
                loadLeftFileForSave();
            }
            click("#left_edit_button");
        }
        testSaveLeftFileHotKey();
        assertNodeExists("#alarm_window");
        click("#yes_button");
        assertTrue(GuiTest.find("#left_save_button").isDisable());

        if(GuiTest.find("#left_save_button").isDisable()){
            if(GuiTest.find("#left_edit_button").isDisable()) {
                loadLeftFileForSave();
            }
            click("#left_edit_button");
        }
        click("#file_menu").click("Save Left File");
        click("#no_button");
        assertFalse(GuiTest.find("#left_save_button").isDisable());

        click("#file_menu").click("Save Left File");
        click("#yes_button");
        assertTrue(GuiTest.find("#left_save_button").isDisable());
    }
    private void testSaveLeftFileHotKey(){
        System.out.println("Type Save left file HotKey : Ctrl + Alt + L");
        press(KeyCode.CONTROL).press(KeyCode.ALT).type(KeyCode.L).release(KeyCode.CONTROL).release(KeyCode.ALT);
    }
    private void loadLeftFileForSave(){
        click("#left_load_button");
        typingForLoadFile();
        type("test-load.txt").type(KeyCode.ENTER);
    }
    private void typingForLoadFile(){
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
    }

    @Test
    public void stage2_testRightSaveMenuItem(){
        if(GuiTest.find("#right_save_button").isDisable()){
            if(GuiTest.find("#right_edit_button").isDisable()) {
                loadRightFileForSave();
            }
            click("#right_edit_button");
        }
        testSaveRightFileHotKey();
        assertNodeExists("#alarm_window");
        click("#yes_button");
        assertTrue(GuiTest.find("#right_save_button").isDisable());

        if(GuiTest.find("#right_save_button").isDisable()){
            if(GuiTest.find("#right_edit_button").isDisable()) {
                loadRightFileForSave();
            }
            click("#right_edit_button");
        }
        click("#file_menu").click("Save Right File");
        click("#no_button");
        assertFalse(GuiTest.find("#right_save_button").isDisable());

        click("#file_menu").click("Save Right File");
        click("#yes_button");
        assertTrue(GuiTest.find("#right_save_button").isDisable());
    }
    private void testSaveRightFileHotKey(){
        System.out.println("Type Save right file HotKey : Ctrl + Alt + R");
        press(KeyCode.CONTROL).press(KeyCode.ALT).type(KeyCode.R).release(KeyCode.CONTROL).release(KeyCode.ALT);
    }
    private void loadRightFileForSave(){
        click("#right_load_button");
        typingForLoadFile();
        type("test-load.txt").type(KeyCode.ENTER);
    }

    @Test
    public void stage2_testSaveAsMenuItem(){

        initForSaveAs();
        testSaveAsHotKey();
        click("#cancel_button");

        initForSaveAs();
        click("#file_menu").click("Save As");
        assertNodeExists("#file_window");

        click("#left_find_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-save1.txt").type(KeyCode.ENTER);
        assertTrue(((TextField)GuiTest.find("#warning_info")).getText().equals("Select test-save1.txt"));
        int size = ((TextField)GuiTest.find("#left_text_field")).getText().split(Pattern.quote(File.separator)).length;
        assertTrue(((TextField)GuiTest.find("#left_text_field")).getText().split(Pattern.quote(File.separator))[size-1].equals("test-save1.txt"));

        click("#right_find_button");
        type(KeyCode.ESCAPE);
        assertTrue(((TextField)GuiTest.find("#warning_info")).getText().equals("Select no Right File"));

        click("#right_find_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-save2.txt").type(KeyCode.ENTER);

        assertTrue(((TextField)GuiTest.find("#warning_info")).getText().equals("Select test-save2.txt"));
        size = ((TextField)GuiTest.find("#right_text_field")).getText().split(Pattern.quote(File.separator)).length;
        assertTrue(((TextField)GuiTest.find("#right_text_field")).getText().split(Pattern.quote(File.separator))[size-1].equals("test-save2.txt"));

        click("#cancel_button");
        assertNodeExists("#alarm_window");
        click("#no_button");

        click("#ok_button");
        stage2_testSavedFile();
    }
    private void testSaveAsHotKey(){
        System.out.println("Type Save As HotKey : Ctrl + S");
        press(KeyCode.CONTROL).type(KeyCode.S).release(KeyCode.CONTROL);
    }
    private void initForSaveAs(){
        File path_file = new File(".");
        String path = path_file.getAbsolutePath().substring(0,path_file.getAbsolutePath().length() - 1);
        try{
            File file1 = new File(path+"src/View/Test/test-save1.txt");
            file1.delete();
        }catch(NullPointerException e){}
        try{
            File file2 = new File(path+"src/View/Test/test-save2.txt");
            file2.delete();
        }catch(NullPointerException e){}

        if(GuiTest.find("#left_save_button").isDisable()){
            if(GuiTest.find("#left_edit_button").isDisable()) {
                loadLeftFileForSave();
            }
            click("#left_edit_button");
        }
        if(GuiTest.find("#right_save_button").isDisable()){
            if(GuiTest.find("#right_edit_button").isDisable()) {
                loadRightFileForSave();
            }
            click("#right_edit_button");
        }
    }
    @Test
    public void stage2_testSavedFile(){
        assertTrue(((Label)GuiTest.find("#left_file_label")).getText().equals("test-save1.txt"));
        assertTrue(((Label)GuiTest.find("#right_file_label")).getText().equals("test-save2.txt"));
    }

    @Test
    public void stage3_testCompareAction() {
        initForCompare();
        click("#compare_button");
        testCompareHotKey();
        assertNodeExists("#left_list_view");
        assertNodeExists("#right_list_view");

        click("#left_edit_button").click("#left_edit_button").click("#compare_button");
        assertNodeExists("#left_list_view");
        assertNodeExists("#right_list_view");
    }
    private void testCompareHotKey(){
        System.out.println("Type Save As HotKey : Alt + C");
        press(KeyCode.ALT).type(KeyCode.C).release(KeyCode.ALT);
    }
    private void loadLeftFileForCompare(){
        click("#left_load_button");
        typingForLoadFile();
        type("test-compare1.txt").type(KeyCode.ENTER);
    }
    private void loadRightFileForCompare(){
        click("#right_load_button");
        typingForLoadFile();
        type("test-compare2.txt").type(KeyCode.ENTER);
    }
    private void initForCompare(){
        loadLeftFileForCompare();
        loadRightFileForCompare();
    }

    @Test
    public void stage4_testNextDifferenceAction() {
        initForDifference();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        testNextDifferenceHotKey();
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(3));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
        */
        click("#left_edit_button").click("#left_edit_button").click("#compare_button");
        click("#next_difference_button");
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(3));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
    }
    private void testNextDifferenceHotKey(){
    }
    private void initForDifference(){
        if(GuiTest.find("#compare_button").isDisable()) {
            initForCompare();
        }
        click("#compare_button");

    }

    @Test
    public void stage4_testPreviousDifferenceAction() {
        initForDifference();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        left_list.getSelectionModel().select(3);
        click(left_list.getSelectionModel().getSelectedItems());
        testPostDifferenceHotKey();
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(1));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
        */
        click("#left_edit_button").click("#left_edit_button").click("#compare_button");
        left_list.getSelectionModel().select(3);
        click(left_list.getSelectionModel().getSelectedItems());
        click("#previous_difference_button");
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(1));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
    }
    private void testPreviousDifferenceHotKey(){
    }

    @Test
    public void stage4_testFirstDifferenceAction() {initForDifference();
        initForDifference();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        left_list.getSelectionModel().select(5);
        click(left_list.getSelectionModel().getSelectedItems());
        testFirstDifferenceHotKey();
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(1));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
        */
        click("#left_edit_button").click("#left_edit_button").click("#compare_button");
        left_list.getSelectionModel().select(5);
        click(left_list.getSelectionModel().getSelectedItems());
        click("#first_difference_button");
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(1));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
    }
    private void testFirstDifferenceHotKey(){
    }

    @Test
    public void stage4_testNowDifferenceAction() {
        initForDifference();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        left_list.getSelectionModel().select(4);
        click(left_list.getSelectionModel().getSelectedItems());
        testNowDifferenceHotKey();
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(5));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
        */
        click("#left_edit_button").click("#left_edit_button").click("#compare_button");
        left_list.getSelectionModel().select(4);
        click(left_list.getSelectionModel().getSelectedItems());
        click("#now_difference_button");
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(5));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
    }
    private void testNowDifferenceHotKey(){
    }

    @Test
    public void stage4_testLastDifferenceAction() {
        initForDifference();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        testNowDifferenceHotKey();
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(5));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
        */
        click("#left_edit_button").click("#left_edit_button").click("#compare_button");
        click("#last_difference_button");
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(7));
        assertTrue(left_list.getSelectionModel().getSelectedIndices().get(0).equals(right_list.getSelectionModel().getSelectedIndices().get(0)));
    }
    private void testLastDifferenceHotKey(){
    }

    @Test
    public void stage5_testCopyToRightAction() {
        initForMerge();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        testCopyToRightHotKey();
        assertTrue(left_list.getItems().size() == 6);
        assertTrue(right_list.getItems().size() == 6);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\ncc\ndd\nee\nff\ngg\nhh"));

        initForMerge();*/
        click("#copy_to_right_button");
        assertTrue(left_list.getItems().size() == 6);
        assertTrue(right_list.getItems().size() == 6);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\ncc\ndd\nee\nff\ngg\nhh"));
    }
    private void initForMerge(){
        initForCompare();
        click("#compare_button");
    }
    private void testCopyToRightHotKey(){

    }

    @Test
    public void stage5_testCopyToLeftAction() {
        initForMerge();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        testCopyToLeftHotKey();
        assertTrue(left_list.getItems().size() == 6);
        assertTrue(right_list.getItems().size() == 6);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\ncc\ndd\nee\nff\ngg\nhh"));

        initForMerge();*/
        click("#copy_to_left_button");
        assertTrue(left_list.getItems().size() == 6);
        assertTrue(right_list.getItems().size() == 6);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\nii\njj\nkk\ncc\ndd\nee\nff\ngg\nhh"));
    }
    private void testCopyToLeftHotKey(){

    }

    @Test
    public void stage5_testCopyToRightAllAction() {
        initForMerge();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        testCopyToRightAllHotKey();
        assertTrue(left_list.getItems().size() == 6);
        assertTrue(right_list.getItems().size() == 6);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\ncc\ndd\nee\nff\ngg\nhh"));

        initForMerge();*/
        click("#copy_to_right_all_button");
        assertTrue(left_list.getItems().size() == 1);
        assertTrue(right_list.getItems().size() == 1);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\ncc\ndd\nee\nff\ngg\nhh\nii\njj\nkk\nll\nmm" +
                                                                                                    "\nnn\noo\npp\nqq\nrr\nss\ntt\nuu\nvv\nww\nxx\nyy\nzz"));
    }
    private void testCopyToRightAllHotKey(){

    }

    @Test
    public void stage5_testCopyToLeftAllAction() {
        initForMerge();
        ListView left_list = GuiTest.find("#left_list_view");
        ListView right_list = GuiTest.find("#right_list_view");
        /*
        testCopyToRightAllHotKey();
        assertTrue(left_list.getItems().size() == 6);
        assertTrue(right_list.getItems().size() == 6);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\ncc\ndd\nee\nff\ngg\nhh"));

        initForMerge();*/
        click("#copy_to_left_all_button");
        assertTrue(left_list.getItems().size() == 1);
        assertTrue(right_list.getItems().size() == 1);

        left_list.getSelectionModel().select(0);
        assertTrue(left_list.getSelectionModel().getSelectedItems().get(0).toString().trim().equals("aa\nbb\nii\njj\nkk\ncc\ndd\nee\nff\ngg\nhh\nll\nmm\n" +
                                                                                                    "vv\nww\nxx\nyy\nzz\nnn\noo\npp\nqq\nrr\nss\ntt\nuu"));
    }
    private void testCopyToLeftAllHotKey(){

    }

    @Test
    public void stage6_testNewTabMenuItem(){
        int tab_num = ((TabPane)GuiTest.find("#tab_pane")).getTabs().size();
        testNewTabHotKey();
        assertTrue(((TabPane)GuiTest.find("#tab_pane")).getTabs().size() == tab_num + 1);

        click("#file_menu").click("New tab");
        assertTrue(((TabPane)GuiTest.find("#tab_pane")).getTabs().size() == tab_num + 2);
    }
    private void testNewTabHotKey(){
        System.out.println("Type Save As HotKey : Ctrl + N");
        press(KeyCode.CONTROL).type(KeyCode.N).release(KeyCode.CONTROL);
    }

    @Test
    public void stage6_testChangTabMenuItem(){
        testNewTabHotKey();
        testNewTabHotKey();

        click("#tab_menu").click("Tab 2");

        int index = 0;
        for(int i=0,n = ((TabPane)GuiTest.find("#tab_pane")).getTabs().size(); i<n; i++){
            if(((TabPane)GuiTest.find("#tab_pane")).getTabs().get(i).isSelected()){
                index = i;
            }
        }
        assertTrue(index == 1);
    }

    @Test
    public void stage7_testCloseTabMenuItem(){
        testNewTabHotKey();
        int tab_num = ((TabPane)GuiTest.find("#tab_pane")).getTabs().size();

        testCloseTabHotKey();
        assertTrue(((TabPane)GuiTest.find("#tab_pane")).getTabs().size() == tab_num - 1);

        testNewTabHotKey();
        click("#tab_menu").click("Close Tab");
        assertTrue(((TabPane)GuiTest.find("#tab_pane")).getTabs().size() == tab_num - 1);
    }
    private void testCloseTabHotKey(){
        System.out.println("Type Save As HotKey : Ctrl + W");
        press(KeyCode.CONTROL).type(KeyCode.W).release(KeyCode.CONTROL);
    }

    @Test
    public void stage8_testCloseTabAllMenuItem(){
        testNewTabHotKey();
        testNewTabHotKey();

        testCloseTabAllHotKey();
        assertTrue(((TabPane)GuiTest.find("#tab_pane")).getTabs().size() == 0);

        testNewTabHotKey();
        testNewTabHotKey();

        click("#tab_menu").click("Close Tab All");
        assertTrue(((TabPane)GuiTest.find("#tab_pane")).getTabs().size() == 0);

    }
    private void testCloseTabAllHotKey(){
        System.out.println("Type Save As HotKey : Ctrl + Shift + W");
        press(KeyCode.CONTROL).press(KeyCode.SHIFT).type(KeyCode.W).release(KeyCode.SHIFT).release(KeyCode.CONTROL);

    }
}
