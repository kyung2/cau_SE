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

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.junit.Assume.assumeTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;

/**
 * Created by woojin on 2016-05-17.
 */
public class TabPaneTest extends GuiTest {
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

        FXTestUtils.launchApp(TabPaneTest.TestTabPane.class); // You can add start parameters here
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
    public void testSaveButton()
    {
        click("#left_save_button");
        assertNodeExists("Click");
    }
}
