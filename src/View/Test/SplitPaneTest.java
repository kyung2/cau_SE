package View.Test;

import View.MainWindow;
import com.google.common.util.concurrent.SettableFuture;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.util.concurrent.TimeUnit;

import static org.junit.Assume.assumeTrue;
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
    public void testLeftLoadButton()
    {
        click("#left_load_button");
        assertNodeExists("Click");
    }
    @Test
    public void testLeftEditButton()
    {
        click("#left_edit_button");
        assertNodeExists("");
    }
    @Test
    public void testLeftSaveButton()
    {
        click("#left_save_button");
        assertNodeExists("");
    }
    @Test
    public void testRightLoadButton()
    {
        click("#right_load_button");
        assertNodeExists("");
    }
    @Test
    public void testRightEditButton()
    {
        click("#right_edit_button");
        assertNodeExists("Click");
    }
    @Test
    public void testRightSaveButton()
    {
        click("#right_save_button");
        assertNodeExists("Click");
    }
}
