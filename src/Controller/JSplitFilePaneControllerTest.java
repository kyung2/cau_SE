package Controller;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.*;


/**
 * Created by hyunkyung on 2016-06-04.
 * @author hyunkyung
 */
public class JSplitFilePaneControllerTest {
    private SplitFilePaneInterface mockLeft;
    private SplitFilePaneInterface mockRight;


    @Before
    public void setUp() throws Exception {
        mockLeft = createMock(SplitFilePaneInterface.class);
        mockRight = createMock(SplitFilePaneInterface.class);
        if (mockLeft == null || mockRight == null) fail("NULL");

    }

    @Test
    public void testisDisableLoad() throws IllegalAccessException {
        //behavior of Disable
        EasyMock.expect(mockLeft.isDisableLoad("left")).andReturn((true));
        EasyMock.expect(mockRight.isDisableLoad("right")).andReturn((true));

        replay(mockLeft, mockRight);
        assertTrue(mockLeft.isDisableLoad("left"));
        assertTrue(mockRight.isDisableLoad("right"));

    }

    @Test
    public void testisDisableSave() throws IllegalAccessException {
        EasyMock.expect(mockLeft.isDisableSave("left")).andReturn((true));
        EasyMock.expect(mockRight.isDisableSave("right")).andReturn((true));

        replay(mockLeft, mockRight);
        assertTrue(mockLeft.isDisableSave("left"));
        assertTrue(mockRight.isDisableSave("right"));

    }
    @Test
    public void testisDisableEdit() throws IllegalAccessException {
        EasyMock.expect(mockLeft.isDisableEdit("left")).andReturn((true));
        EasyMock.expect(mockRight.isDisableEdit("right")).andReturn((true));

        replay(mockLeft, mockRight);
        assertTrue(mockLeft.isDisableEdit("left"));
        assertTrue(mockRight.isDisableEdit("right"));

    }

}
