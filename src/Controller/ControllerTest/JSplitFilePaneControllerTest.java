package Controller.ControllerTest;

import Model.*;
import Controller.*;

import static org.easymock.EasyMock.*;

import org.easymock.*;
import org.junit.*;
import java.io.*;



import junit.framework.Assert;

/*

createMock : 인터페이스나 클래스의 mock객체를 생성한다.
replay : mock객체의 인터페이스를 호출하는 것을 기록한 것을 다시 처음으로 돌린다.
verify : 기록한 내용의 behavior(행위)들을 다 실행을 했는지 체크를 한다.
reset : createMock으로 생성한 mock을 초기화 한다.
중간에 assert 메소드들을 넣어서 비즈니스 로직을 확인하면 되겠다.
*/
/**
 * Created by hyunkyung on 2016-06-04.
 */
public class JSplitFilePaneControllerTest {
    private SplitFilePaneController mocksplitFilePaneController; //Idepency.
    private ModelInterface model;
    private MainController controller;
    @Before
    public void setUp() {
        mocksplitFilePaneController = EasyMock.createMock(SplitFilePaneController.class);
    }

    @Test
    public void testleftLaodButtonOnAction() {
        /**
         * 파일이 제대로 불러왔는지 파일입출력을 통해서
         * ㅜㅠ  lefttest가 제대로 불러왔는지 비교 /
         *
         */
    }


    @Test
    public void testRightLoagButtonOnAction(){

    }
    @Test
    public void testleftEditButtonOnAction(){
        /*
        *
        *
        *
        * */

    }
    @Test
    public void testrightEditButtonOnAction() {

    }
    @Test
    public void testleftSaveButtonOnAction(){

    }
    @Test
    public void testrightSaveButtonOnAction(){

    }
    @Test
    public void testonLeftListViewMouseClicked(){

    }
    @Test
    public void testonRightListViewMouseClicked(){

    }

    @After
    public void tearDown() {
        verify(mocksplitFilePaneController); //run test
    }

    /*
   * load 버튼을 눌렀을 때
   * 파일을 불러와서 text area 에 적고
   * edit 버튼을 활성화한다.
   * 두 text area 가 모두 파일이 있고 edit 상태가 아닐 때 compare 버튼의 활성화한다.
   * compare 하여 list view 가 있을 때 load 하면 text area 를 visible 시키고 list view 를 invisible 시킨다.
   *
    void leftLoadButtonOnAction();
    void rightLoadButtonOnAction();
    /*
    * edit 버튼을 눌렀을 때
    * text area 가 edit 상태가 아니라면
    * text area 를 editable 하게 하고
    * load 버튼을 비활성화하고 save 버튼을 활성화한다.
    * compare 하여 list view 가 있을 때 load 하면 text area 를 visible 시키고 list view 를 invisible 시킨다.
    * ////////////////////////////////////
    * text area 가 edit 상태 라면
    * edit 버튼을 비활성화 하고
    * 두 text area 가 모두 파일이 있고 edit 상태가 아닐 때 compare 버튼의 활성화한다.
    *

    void leftEditButtonOnAction();
    void rightEditButtonOnAction();
    /*
    * save 버튼을 눌렀을 때
    * text area 의 내용을 저장한다.
    * load 버튼과 edit 버튼을 활성화한다.
    * 두 text area 가 모두 파일이 있고 edit 상태가 아닐 때 compare 버튼의 활성화한다.
    *
    void leftSaveButtonOnAction();
    void rightSaveButtonOnAction();

    * list view 를 클릭했을 때
    * 양쪽의 클릭을 동기화 시키고
    * 위치에 맞게 버튼과 menu item 을 활성화 한다.
    *
    void onLeftListViewMouseClicked();
    void onRightListViewMouseClicked();
*/
}




    /*
    *       checkTabNumAndCompareButtonAndMenuBar();
        File file = loadFileChooser();
        Model.ModelInterface model = ModelRealize.getInstance();
        if(file != null){
            try {
                model.readTextOuter(tab_num, file.getAbsolutePath(), 0);
                left_text_area.setVisible(true);
                left_text_area.setText(arrayListToString(model.getText(tab_num,0)));
                setClickableButtons("left","true","true","false");
                changeTabName(file.getName(),"left");
                disableAllButtonInToolBarAndMenuItem();
                left_file_label.setText(file.getName());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        invisibleListViewVisibleTextArea();
        checkCompareButton();
    }
    */

    /*
        @Test
        public void add(){
            //recoding

            //verify
        }
        */
