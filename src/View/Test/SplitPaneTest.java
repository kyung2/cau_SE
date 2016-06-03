package View.Test;

import View.MainWindow;
import com.google.common.util.concurrent.SettableFuture;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesVisibleException;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;
import static javafx.scene.input.KeyCode.R;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;

/**
 * Created by woojin on 2016-05-17.
 */
public class SplitPaneTest extends GuiTest {
    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

    protected static class TestTabPane extends MainWindow {
        public TestTabPane() {
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

        FXTestUtils.launchApp(SplitPaneTest.TestTabPane.class); // You can add start parameters here
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
    public void testInitButtonTest(){
        assertFalse(GuiTest.find("#left_load_button").isDisable());
        assertFalse(GuiTest.find("#right_load_button").isDisable());
        assertTrue(GuiTest.find("#left_edit_button").isDisable());
        assertTrue(GuiTest.find("#right_edit_button").isDisable());
        assertTrue(GuiTest.find("#left_save_button").isDisable());
        assertTrue(GuiTest.find("#right_save_button").isDisable());
    }

    @Test
    public void testInitTextAreaAndListView(){
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

    private int left_phase = 0;
    private int right_phase = 0;
    @Test
    public void testLeftLoadButton () {
        click("#left_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-load.txt").type(KeyCode.ENTER);
        assertEquals("Testing load button!\n",((TextArea)GuiTest.find("#left_text_area")).getText());
        left_phase++;
    }
    @Test
    public void testRightLoadButton() {
        click("#right_load_button");
        type("src").type(KeyCode.ENTER);
        type("View").type(KeyCode.ENTER);
        type("Test").type(KeyCode.ENTER);
        type("test-load.txt").type(KeyCode.ENTER);
        assertEquals("Testing load button!\n",((TextArea)GuiTest.find("#right_text_area")).getText());
        right_phase++;
    }
    @Test
    public void testLeftEditButton() {
        if (left_phase == 0) {
            testLeftLoadButton();
        }
        assertFalse(GuiTest.find("#left_edit_button").isDisable());
        click("#left_edit_button");
        assertFalse(GuiTest.find("#left_text_area").isDisable());
        assertFalse(GuiTest.find("#left_save_button").isDisable());
        assertTrue(GuiTest.find("#left_load_button").isDisable());
        left_phase++;
    }
    @Test
    public void testRightEditButton()
    {
        if (right_phase == 0) {
            testRightLoadButton();
        }
        assertFalse(GuiTest.find("#right_edit_button").isDisable());
        click("#right_edit_button");
        assertFalse(GuiTest.find("#right_text_area").isDisable());
        assertFalse(GuiTest.find("#right_save_button").isDisable());
        assertTrue(GuiTest.find("#right_load_button").isDisable());
        left_phase++;
    }
    @Test
    public void testLeftSaveButton()
    {
        if(left_phase == 0){
            testLeftLoadButton();
        }
        if(left_phase == 1){
            testLeftEditButton();
        }
        click("#left_save_button");
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

    @Test
    public void testRightSaveButton() {
        if(right_phase == 0) {
            testRightLoadButton();
        }
        if(right_phase == 1){
            testRightEditButton();
        }
        click("#right_save_button");
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
}
