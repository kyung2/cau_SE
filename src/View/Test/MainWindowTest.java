package View.Test;

import View.MainWindow;
import View.OpenFileWindow;
import com.google.common.util.concurrent.SettableFuture;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;

/**
 * Created by woojin on 2016-05-17.
 */
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
    public void initButtonTest(){
        assertTrue(((Button)GuiTest.find("#compare_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#next_difference_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#previous_difference_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#first_difference_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#now_difference_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#last_difference_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#copy_to_left_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#copy_to_left_all_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#copy_to_right_button")).isDisable());
        assertTrue(((Button)GuiTest.find("#copy_to_right_all_button")).isDisable());
        //버튼이 모두 disable 인지 test
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
