package Controller.ControllerTest;
import static org.easymock.EasyMock.*;

import Controller.SplitFilePaneController;
import org.junit.*;
import Model.ModelInterface;
import Model.ModelRealize;
import junit.framework.Assert;

/*
easymock은 일단 학습을 하고 나면, 사용하기가 쉽다.
하지만, 처음에 접할 때 조금 난해한 API사용 방법으로 어려움을 겪는 사람들이 많다.
jdk5.0을 사용을 하면, static import가 가능해져서 그나마 과거 easymock보다는 사용하기가 쉽다.

아래 static 메소드들은 다음과 같은 의미가 있다.

createMock : 인터페이스나 클래스의 mock객체를 생성한다.

replay : mock객체의 인터페이스를 호출하는 것을 기록한 것을 다시 처음으로 돌린다.

verify : 기록한 내용의 behavior(행위)들을 다 실행을 했는지 체크를 한다.

reset : createMock으로 생성한 mock을 초기화 한다.

위와 같은 실행을 하는 동안 중간에 assert 메소드들을 넣어서 비즈니스 로직을 확인하면 되겠다.

참고로, easymock extension은 인터페이스가 아니고, Concrete클래스를 mock으로 만들수 있다.


*/
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
