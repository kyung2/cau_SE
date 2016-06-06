package Controller;

import Model.*;
import View.*;
import Controller.*;

import static org.easymock.EasyMock.*;
import org.easymock.*;
import org.easymock.internal.matchers.Equals;
import org.junit.*;
import java.io.*;

/*
createMock : 인터페이스나 클래스의 mock객체를 생성한다.
replay : mock객체의 인터페이스를 호출하는 것을 기록한 것을 다시 처음으로 돌린다.
verify : 기록한 내용의 behavior(행위)들을 다 실행을 했는지 체크를 한다.
reset : createMock으로 생성한 mock을 초기화 한다.
중간에 assert 메소드들을 넣어서 비즈니스 로직을 확인하면 되겠다.
*/
/**
 * Created by hyunkyung on 2016-06-04.
 * @author hyunkyung
 */
public class JSplitFilePaneControllerTest {
    private SplitFilePaneController mocksplitFilePaneController; //Idepency.
    private SplitFilePaneInterface mocksplitFilePaneInterface;

    String left = new String("left");
    String right = new String("right");

    @Before
    public void setUp() throws Exception {
        mocksplitFilePaneController = createMock(SplitFilePaneController.class);
        mocksplitFilePaneInterface = createMock(SplitFilePaneInterface.class);
    }

    @Test
    public void testisDisableLeftLoad() throws IllegalAccessException {
        expect(mocksplitFilePaneInterface.isDisableLoad("left")).andReturn((true));
        replay(mocksplitFilePaneInterface); //record

    }
    @Test
    public void testisDisableSave() throws IllegalAccessException {
        expect(mocksplitFilePaneInterface.isDisableSave("left")).andReturn((true));
        replay(mocksplitFilePaneInterface); //record

    }
    @Test
    public void testisDisableLeftEdit() throws IllegalAccessException {
        expect(mocksplitFilePaneInterface.isDisableEdit("left")).andReturn((true));
        replay(mocksplitFilePaneInterface); //record

    }

}
