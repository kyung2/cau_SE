package Controller;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.*;

import java.io.File;
import java.io.IOException;

import Model.ModelInterface;
import Model.ModelRealize;

/**
 * Created by hyunkyung on 2016-06-04.
 * @author hyunkyung
 */
public class JSplitFilePaneControllerTest {
    private SplitFilePaneInterface mockRight_area;
    private SplitFilePaneInterface mockLeft_area;
    SplitFilePaneController controller = new SplitFilePaneController();

    @Before
    public void setUp() throws Exception {
        mockLeft_area= createMock(SplitFilePaneInterface.class);
        mockRight_area = createMock(SplitFilePaneInterface.class);
        //ModelInterface modelInterface= createMock(ModelInterface.class);
        if (mockLeft_area == null || mockRight_area == null) fail("NULL");

    }

    @Test
    public void testLoad()  {
        File path_file= new File(".");

        //lath 알기 위하여 사용
        String path = path_file.getAbsolutePath().substring(0,path_file.getAbsolutePath().length() - 1);
        System.out.println(path);

        ModelInterface modelInterface = ModelRealize.getInstance();
        modelInterface.newModel(0);
        //left

        try {
            modelInterface.readTextOuter(0,path+"src/Controller/ControllerTest/lefttest.txt",0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //right
        try {
            modelInterface.readTextOuter(0,path+"src/Controller/ControllerTest/righttest.txt",1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //getText(a,b) a= tab 번호 b 0 left 1 right get(0) 첫줄

        //왼쪽
        String left, right;
        left = modelInterface.getText(0,0).get(0);
        right =modelInterface.getText(0,1).get(0);

        assertEquals("left",modelInterface.getText(0,0).get(0));

        assertEquals("righttext",right);



    }



    @Test
    public void testisDisableLoad() throws IllegalAccessException {
        //behavior of Disable
        EasyMock.expect(mockLeft_area.isDisableLoad("left")).andReturn((true));
        EasyMock.expect(mockRight_area.isDisableLoad("right")).andReturn((true));

        replay(mockLeft_area, mockRight_area);
        assertTrue(mockLeft_area.isDisableLoad("left"));
        assertTrue(mockRight_area.isDisableLoad("right"));
        verify(mockLeft_area,mockRight_area);
    }

    @Test
    public void testisDisableSave() throws IllegalAccessException {
        EasyMock.expect(mockLeft_area.isDisableSave("left")).andReturn((true));
        EasyMock.expect(mockRight_area.isDisableSave("right")).andReturn((true));

        replay(mockLeft_area, mockRight_area);
        assertTrue(mockLeft_area.isDisableSave("left"));
        assertTrue(mockRight_area.isDisableSave("right"));
        verify(mockLeft_area,mockRight_area);


    }

    @Test
    public void testisDisableEdit() throws IllegalAccessException {
        EasyMock.expect(mockLeft_area.isDisableEdit("left")).andReturn((true));
        EasyMock.expect(mockRight_area.isDisableEdit("right")).andReturn((true));

        replay(mockLeft_area, mockRight_area);
        assertTrue(mockLeft_area.isDisableEdit("left"));
        assertTrue(mockRight_area.isDisableEdit("right"));
        verify(mockLeft_area,mockRight_area);

    }

}
