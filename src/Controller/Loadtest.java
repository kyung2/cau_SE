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
 * Created by hyunkyung on 2016-06-07.
 */
public class Loadtest {
    private SplitFilePaneInterface mockRight_area;
    private SplitFilePaneInterface mockLeft_area;

    @Before
    public void setUp() throws Exception {
        File path_file= new File(".");
        String path = path_file.getAbsolutePath().substring(0,path_file.getAbsolutePath().length() - 1);
        //path 알기 위하여 사용
        System.out.println(path);
        ModelInterface modelInterface = ModelRealize.getInstance();
        ModelInterface mock_left = ModelRealize.getInstance();
        ModelInterface mock_right = ModelRealize.getInstance();

        modelInterface.newModel(0);
        mock_left.newModel(0);
        mock_right.newModel(0);
        try {
            mock_left.readTextOuter(0,path+"src/Controller/ControllerTest/lefttest.txt",0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //right
        try {
            mock_right.readTextOuter(0,path+"src/Controller/ControllerTest/righttest.txt",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //getText(a,b) a= tab 번호 b 0 left 1 right get(0) 첫줄
        //왼쪽
        String left, right;
        left = modelInterface.getText(0,0).get(0); //left 에 무엇이있는지
        right =modelInterface.getText(0,1).get(0); //rifht에 무엇이있는지 셋팅
        mock_left= createMock(ModelInterface.class);
        mock_right = createMock(ModelInterface.class);
        if (mockLeft_area == null || mockRight_area == null) fail("NULL");

        }
    @Test
    public void testLoad() {

    }



}
