package Controller.ControllerTest;
import static org.easymock.EasyMock.*;

import Controller.SplitFilePaneController;
import org.junit.*;
import Model.ModelInterface;
import Model.ModelRealize;
import junit.framework.Assert;

/**
 * Created by hyunkyung on 2016-06-04.
 */
public class JSplitFilePaneControllerTest {
    SplitFilePaneController mocksplitFilePaneController;

    @Before
    public void setup() {
        mocksplitFilePaneController = createMock(SplitFilePaneController.class);

    }
    /*
        @Test
        public void add(){
            //recoding

            //verify
        }
        */
}
