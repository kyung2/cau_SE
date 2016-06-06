package Controller;

import Model.*;
import View.*;
import Controller.*;

import static org.easymock.EasyMock.*;
import org.easymock.*;
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

    @Before
    public void setUp() {
        mocksplitFilePaneController = EasyMock.createMock(SplitFilePaneController.class);
    }

    @Test
    public void testleftLaodButtonOnAction() {
        File left = new File("/TestLeft/left.txt");
        if( !left.exists() ) System.out.println("fail"); //실패 메세지 보내기 fail("File is not Open!!");

        //left 파일이 제대로 읽혀왓나 테스트 assert이용
        /*
        *  checkTabNumAndCompareButtonAndMenuBar();
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
                left_status.setFileName(file.getName());

                left_status.addStatusWithName("File open");
            }catch(Exception e){
                e.printStackTrace();

                left_status.addStatusWithName("ERR - "+e.getMessage());
            }
        }
        invisibleListViewVisibleTextArea();
        checkCompareButton();*/

    }


    @Test
    public void testRightLoagButtonOnAction(){

    }
    @Test
    public void testleftEditButtonOnAction(){
        /*
        여기서 열려있ㅇ야할듯 먼저
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
        /*위에 left 모의객체로 만들면 sace가 작동더ㅣㄴㄴ상황 save button 눌럿을시 제대로 save되냐 */

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
* package Data;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;

import View.Drawable;


public class FileComparerTest extends TestCase{


	@Test
	public void testFactory(){

		File f1 = new File("./june2.txt");
		File f2 = new File("./june.txt");

		if( !f1.exists() || !f2.exists()  ) fail("File is not Open!!");

		FileComparer compare1 = FileComparer.Factory.create(f1);
		FileComparer compare2 = FileComparer.Factory.create(f2);

		assertTrue(compare1 instanceof ASCIIFileComparer);
		assertTrue(compare2 instanceof ASCIIFileComparer);


		File d1 = new File("./a");
		File d2 = new File("./b");

		if( !d1.exists() || !d2.exists()  ) fail("File is not Open!!");

		FileComparer compare3 = FileComparer.Factory.create(d1);
		FileComparer compare4 = FileComparer.Factory.create(d2);

		assertTrue(compare3 instanceof DirectoryComparer);
		assertTrue(compare4 instanceof DirectoryComparer);
	}
	@Test
	public void testDiff() {

		File f1 = new File("./june2.txt");
		File f2 = new File("./june.txt");

		if( !f1.exists() || !f2.exists()  ) fail("File is not Open!!");

		FileComparer compare1 = FileComparer.Factory.create(f1);
		FileComparer compare2 = FileComparer.Factory.create(f2);

		assertTrue(compare1.diff(compare2) instanceof Drawable);
		assertTrue(compare2.diff(compare1) instanceof Drawable);



		File d1 = new File("./a");
		File d2 = new File("./b");

		if( !d1.exists() || !d2.exists()  ) fail("File is not Open!!");

		FileComparer compare3 = FileComparer.Factory.create(d1);
		FileComparer compare4 = FileComparer.Factory.create(d2);

		assertTrue(compare3.diff(compare4) instanceof Drawable);
		assertTrue(compare4.diff(compare3) instanceof Drawable);

	}

	@Test
	public void testEquals() {

		File f1 = new File("./june2.txt");
		File f2 = new File("./june2.txt");
		File f3 = new File("./june.txt");

		if( !f1.exists() || !f2.exists() || !f3.exists() ) fail("File is not Open!!");


		FileComparer compare1 = FileComparer.Factory.create(f1);
		FileComparer compare2 = FileComparer.Factory.create(f2);
		FileComparer compare3 = FileComparer.Factory.create(f3);

		assertEquals(true,compare1.equals(compare2));
		assertEquals(false,compare1.equals(compare3));

		File d1 = new File("./a");
		File d2 = new File("./a");
		File d3 = new File("./b");


		if( !d1.exists() || !d2.exists() || !d3.exists() ) fail("File is not Open!!");

		FileComparer dircompare1 = FileComparer.Factory.create(d1);
		FileComparer dircompare2 = FileComparer.Factory.create(d2);
		FileComparer dircompare3 = FileComparer.Factory.create(d3);

		assertEquals(true,dircompare1.equals(dircompare2));
		assertEquals(false,dircompare1.equals(dircompare3));


	}

}
*/

/*package Contoller;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.junit.Test;

import Data.FileComparer;
import View.Drawable;

public class ControllerTest extends TestCase{

	private Controller testContoller;
	private FileComparer mock;
	private FileComparer mock2;

	private Drawable draw;
	private Drawable draw2;

/*
@Override
/*protected void etUp() throws Exception {
    super.setUp();


    mock = EasyMock.createMock(FileComparer.class);
    mock2 = EasyMock.createMock(FileComparer.class);

    draw = EasyMock.createMock(Drawable.class);
    draw2 = EasyMock.createMock(Drawable.class);

    if(mock == null || mock2 == null) fail("Null!!!");
    testContoller = new Controller();
    testContoller.setFileComparer(mock, mock2);


}

    @Test
    public void testSimpleTest(){
        EasyMock.expect(mock.diff(mock2)).andReturn(draw);
        EasyMock.expect(mock2.diff(mock)).andReturn(draw2);
        EasyMock.replay(mock,mock2);

        assertTrue(testContoller.initMerge());
        assertTrue(testContoller.resultDraw());
    }


    @Test
    public void testInitMerge() {
        Controller c = new Controller();

        assertTrue(c.initMerge());
    }

    @Test
    public void testSelectCharge() {
        Controller c = new Controller();

        assertTrue(c.selectCharge("./june2.txt", "./june.txt"));
    }


}
*/