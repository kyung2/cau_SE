package View.Test;

import View.OpenFileWindow;
import com.google.common.util.concurrent.SettableFuture;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assume.assumeTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.controls.Commons.nodeLabeledBy;

/**
 * Created by woojin on 2016-05-16.
 */
public class OpenFileWindowTest extends GuiTest {
    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

    protected static class TestOpenFileWindow extends OpenFileWindow {
        public TestOpenFileWindow() {
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

        FXTestUtils.launchApp(TestOpenFileWindow.class); // You can add start parameters here
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
    public void testLeftFileFindButton()
    {
        click("#left_file_find_button");
        assertNodeExists("Click");
    }
    @Test
    public void testRightFileFindButton()
    {
        click("#right_file_find_button");
        assertNodeExists("Click");
    }
    @Test
    public void testOkButton()
    {
        click("#ok_button");
        assertNodeExists("Click");
    }
    @Test
    public void testCancelButton()
    {
        click("#cancel_button");
        assertNodeExists("Click");
    }

}
