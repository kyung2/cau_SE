package Controller.ControllerTest;

import Controller.MainController;
import Model.ModelInterface;
import Model.ModelRealize;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-06-01.
 */

//사이즈에 문제있으면 left_list_item size 얻어오는 get함수 이용할 것


public class JMainControllerTest {

    private ModelInterface model;
    private MainController controller;

    private int size;
    private int selectedIndex;

    @Before
    public void setUp() throws Exception {

        try {
            model = ModelRealize.getInstance();
            model.newModel(0);
            controller = new MainController();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @After
    public void tearDown() throws Exception {
        model.closeModelAll();
    }

    public void setCopyTestModel(int testCase){
        ArrayList<String> a1 = new  ArrayList<String>(), a2 = new  ArrayList<String>();
        ArrayList<String> a3 = new  ArrayList<String>(), a4 = new  ArrayList<String>();
        ArrayList<String> a5 = new  ArrayList<String>(), a6 = new  ArrayList<String>();
        ArrayList<String> a7 = new  ArrayList<String>(), a8 = new  ArrayList<String>();
        ArrayList<String> a9 = new  ArrayList<String>();

        //사이즈 짝수, 처음부터 틀린 경우
        String[] left1_1 = {"aaa","bbb","ccc","ddd","ggg","hhh"};
        String[] right1_1 = {"eee","bbb","fff","ddd","iii","hhh"};

        //사이즈 짝수, 두번째부터 틀린 경우
        String[] left1_2 = {"aaa","bbb","ccc","ddd","ggg,","hhh"};
        String[] right1_2 = {"aaa","eee","ccc","fff","ggg","iii"};

        //사이즈 홀수, 처음부터 틀린 경우
        String[] left2_1 = {"aaa","bbb","ccc","ddd","ggg","jjj","kkk"};
        String[] right2_1 = {"eee","bbb","fff","ddd","hhh","jjj","iii"};

        //사이즈 홀수, 두번째부터 틀린 경우
        String[] left2_2 = {"aaa","bbb","ccc","ddd","ggg","jjj","kkk"};
        String[] right2_2 = {"aaa","eee","ccc","fff","ggg","iii","kkk"};

        //동일한 경우
        String[] sameText = {"aaa","bbb","ccc"};


        for(int i=0;i<left1_1.length;i++)
            a1.add(left1_1[i]);
        for(int i=0;i<right1_1.length;i++)
            a2.add(right1_1[i]);

        for(int i=0;i<left1_2.length;i++)
            a3.add(left1_2[i]);
        for(int i=0;i<right1_2.length;i++)
            a4.add(right1_2[i]);

        for(int i=0;i<left2_1.length;i++)
            a5.add(left1_1[i]);
        for(int i=0;i<right2_1.length;i++)
            a6.add(right1_1[i]);

        for(int i=0;i<left2_2.length;i++)
            a7.add(left1_1[i]);
        for(int i=0;i<right2_2.length;i++)
            a8.add(right1_1[i]);

        for(int i=0;i<right2_2.length;i++)
            a9.add(sameText[i]);

        switch(testCase){
            case 0 :
                model.getUnit(0).textSend(0, a1);
                model.getUnit(0).textSend(1, a2);
                size = 6;
                break;
            case 1 :
                model.getUnit(0).textSend(0, a3);
                model.getUnit(0).textSend(1, a4);
                size = 6;
                break;
            case 2 :
                model.getUnit(0).textSend(0, a5);
                model.getUnit(0).textSend(1, a6);
                size = 7;
                break;
            case 3 :
                model.getUnit(0).textSend(0, a7);
                model.getUnit(0).textSend(1, a8);
                size = 7;
                break;
            case 4 :
                model.getUnit(0).textSend(0, a9);
                model.getUnit(0).textSend(1, a9);
                size = 1;
                break;
        }

    }

    @Test
    public void testCompareOnActionSame(){
        //동일한 경우
        //리스트 뷰에 담긴 내용은 동일, 버튼의 활성화
        setCopyTestModel(4);
        controller.compareOnAction();
        Assert.assertEquals(new boolean[]{false, false, false, false, false, false, false, false, false, true},controller.getButtonDisabled());
        Assert.assertEquals(1,model.getArrangedText(0,0).size()); // 블럭사이즈가 1인지 확인

    }

    @Test
    public void testCompareOnActionDiff(){

        //차이가 있는 경우
        //리스트 뷰에 담긴 내용이 차이남, 버튼의 활성화
        setCopyTestModel(0);
        controller.compareOnAction();
        Assert.assertEquals(new boolean[]{true, false, true, true, true, true, true, true, true, true},controller.getButtonDisabled());
        Assert.assertEquals(6,model.getArrangedText(0,0).size()); // 블럭사이즈가 6으로 나누어졌는지 확인
        Assert.assertEquals(0,model.getArrangedText(0,0).get(0)); // 처음부터 틀린대로 나누어졌는지 확인

    }
    /*
    * differenceAction test
    * */
    //인덱스 부분이 넘겨주어야 할 것과 동일한지 확인하면 됨

    @Test
    public void testNextDifferenceOnActionSizeEven_1() {
        //블럭사이즈가 짝수(6)일 때
                setCopyTestModel(0); // 홀수번째가 틀렸을 때
                controller.setTextBlockIndex(4); //맨 끝인 경우
                controller.nextDifferenceOnAction();
                Assert.assertEquals(4,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeEven_2() {
        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(0); // 홀수번째가 틀렸을 때
        controller.setTextBlockIndex(0); // 처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(2,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeEven_3() {
        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(0); // 홀수번째가 틀렸을 때
        controller.setTextBlockIndex(2); // 중간인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(4,controller.getTextBlockIndex());
    }


    @Test
    public void testNextDifferenceOnActionSizeEven_4() {
        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(1); // 짝수번째가 틀렸을 때
        controller.setTextBlockIndex(5); //맨 끝인 경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeEven_5() {
        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(1); // 짝수번째가 틀렸을 때
        controller.setTextBlockIndex(1); // 처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(3,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeEven_6() {
        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(1); // 짝수번째가 틀렸을 때
        controller.setTextBlockIndex(3); // 중간인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeOdd_1() {
        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(2); // 홀수번째가 틀렸을 때
        controller.setTextBlockIndex(6); //맨 끝인 경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(6,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeOdd_2() {
        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(2); // 홀수번째가 틀렸을 때
        controller.setTextBlockIndex(0); // 처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(2,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeOdd_3() {
        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(2); // 홀수번째가 틀렸을 때
        controller.setTextBlockIndex(2); // 중간인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(4,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeOdd_4() {
        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(3); // 짝수번째가 틀렸을 때
        controller.setTextBlockIndex(5); //맨 끝인 경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeOdd_5() {
        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(3); // 짝수번째가 틀렸을 때
        controller.setTextBlockIndex(1); // 처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(3,controller.getTextBlockIndex());
    }

    @Test
    public void testNextDifferenceOnActionSizeOdd_6() {
        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(3); // 짝수번째가 틀렸을 때
        controller.setTextBlockIndex(3); // 중간인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());
    }



    @Test
    public void testPreviousDifferenceOnActionSideEven_1(){

        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(0); // 홀수번째가 틀렸을 때

        controller.setTextBlockIndex(4); //맨 끝이나 중간 경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(2,controller.getTextBlockIndex());

    }


    @Test
    public void testPreviousDifferenceOnActionSideEven_2(){

        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(0); // 홀수번째가 틀렸을 때

        controller.setTextBlockIndex(0); //처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(0,controller.getTextBlockIndex());

    }

    @Test
    public void testPreviousDifferenceOnActionSideEven_3(){

        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(1); // 짝수번째가 틀렸을 때

        controller.setTextBlockIndex(1); // 처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(1,controller.getTextBlockIndex());

    }

    @Test
    public void testPreviousDifferenceOnActionSideEven_4(){

        //블럭사이즈가 짝수(6)일 때
        setCopyTestModel(1); // 짝수번째가 틀렸을 때

        controller.setTextBlockIndex(5); //맨 끝이나 중간 경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(3,controller.getTextBlockIndex());

    }


    @Test
    public void testPreviousDifferenceOnActionSideOdd_1(){

        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(2); // 홀수번째가 틀렸을 때
        controller.setTextBlockIndex(6); //맨 끝이나 중간인 경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(4,controller.getTextBlockIndex());

    }


    @Test
    public void testPreviousDifferenceOnActionSideOdd_2(){

        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(2); // 홀수번째가 틀렸을 때

        controller.setTextBlockIndex(0); // 처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(0,controller.getTextBlockIndex());
    }


    @Test
    public void testPreviousDifferenceOnActionSideOdd_3(){

        //블럭사이즈가 홀수(7)일 때
        controller.setTextBlockIndex(5); //맨 끝이나 중간인 경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(3,controller.getTextBlockIndex());

    }


    @Test
    public void testPreviousDifferenceOnActionSideOdd_4(){

        //블럭사이즈가 홀수(7)일 때
        setCopyTestModel(3); // 짝수번째가 틀렸을 때
        controller.setTextBlockIndex(1); // 처음인경우
        controller.nextDifferenceOnAction();
        Assert.assertEquals(1,controller.getTextBlockIndex());

    }



    @Test
    public void testFirstDifferenceOnAction_1(){
        //홀수번째가 틀렸을 경우, 처음 틀린 블럭의 index는 0
        setCopyTestModel(0);
        controller.firstDifferenceOnAction();
        Assert.assertEquals(0,controller.getTextBlockIndex());

    }

    @Test
    public void testFirstDifferenceOnAction_2(){

        //짝수번째가 틀렸을 경우, 처음 틀린 블럭의 index는 1
        setCopyTestModel(1);
        controller.firstDifferenceOnAction();
        Assert.assertEquals(1,controller.getTextBlockIndex());
    }


    @Test
    public void testLastDifferenceOnAction_1(){

        // 짝수사이즈의 짝수번째 틀린경우 size-1
        setCopyTestModel(0);
        controller.lastDifferenceOnAction();
        Assert.assertEquals(4,controller.getTextBlockIndex());

    }

    @Test
    public void testLastDifferenceOnAction_2(){

        // 짝수사이즈의 짝수번째 틀린경우 size-2
        setCopyTestModel(1);
        controller.lastDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());

    }

    @Test
    public void testLastDifferenceOnAction_3(){

        // 홀수사이즈의 짝수번째 틀린경우 size-1
        setCopyTestModel(3);
        controller.lastDifferenceOnAction();
        Assert.assertEquals(6,controller.getTextBlockIndex());

    }

    @Test
    public void testLastDifferenceOnAction_4(){

        // 홀수사이즈의 홀수번째 틀린경우 size-2
        setCopyTestModel(3);
        controller.lastDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());

    }

    @Test
    public void testNowDifferenceOnAction_1() {
        // 홀수번째가 틀렸을 때, 현재 위치가 짝수번째 인덱스에 위치할 경우 다음 틀린 위치로
        // 다음이 맨 끝을 넘어갈 경우 이전 위치로
        // 그 외의 경우 다음 틀린 위치로

        setCopyTestModel(1);
        controller.setTextBlockIndex(5);
        controller.nowDifferenceOnAction();
        Assert.assertEquals(4,controller.getTextBlockIndex());

    }


    @Test
    public void testNowDifferenceOnAction_2() {
        // 홀수번째가 틀렸을 때, 현재 위치가 짝수번째 인덱스에 위치할 경우 다음 틀린 위치로
        // 다음이 맨 끝을 넘어갈 경우 이전 위치로
        // 그 외의 경우 다음 틀린 위치로


        setCopyTestModel(1);
        controller.setTextBlockIndex(3);
        controller.nowDifferenceOnAction();
        Assert.assertEquals(4,controller.getTextBlockIndex());

    }


    @Test
    public void testNowDifferenceOnAction_3() {
        // 짝수번째가 틀렸을 때, 현재 위치가 홀수번째 인덱스에 위치할 경우
        // 다음이 맨 끝을 넘어갈 경우 이전 위치로
        // 그 외의 경우 다음 틀린 위치로

        setCopyTestModel(2);
        controller.setTextBlockIndex(6);
        controller.nowDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());

    }


    @Test
    public void testNowDifferenceOnAction_4() {
        // 짝수번째가 틀렸을 때, 현재 위치가 홀수번째 인덱스에 위치할 경우
        // 다음이 맨 끝을 넘어갈 경우 이전 위치로
        // 그 외의 경우 다음 틀린 위치로

        setCopyTestModel(2);
        controller.setTextBlockIndex(4);
        controller.nowDifferenceOnAction();
        Assert.assertEquals(5,controller.getTextBlockIndex());

    }


    /*
    * copyToAction test
    * */
    //내용이 같은지 확인
    @Test
    public void testCopyToLeftOnAction(){
        setCopyTestModel(0);
        controller.setTextBlockIndex(2);
        controller.copyToLeftOnAction();
        Assert.assertEquals("fff",model.getText(0,0).get(2));
    }

    @Test
    public void testCopyToRightOnAction(){
        setCopyTestModel(0);
        controller.setTextBlockIndex(2);
        controller.copyToRightOnAction();
        Assert.assertEquals("ccc",model.getText(0,1).get(2));

    }

    @Test
    public void testCopyToRightAllOnAction(){

        String[] left1_1 = {"aaa","bbb","ccc","ddd","ggg","hhh"};
        setCopyTestModel(0);
        controller.setTextBlockIndex(0);
        controller.copyToRightAllOnAction();
        Assert.assertEquals(left1_1,model.getArrangedText(0,1));


    }

    @Test
    public void testCopyToLeftAllOnAction(){
        String[] right1_1 = {"eee","bbb","fff","ddd","iii","hhh"};
        setCopyTestModel(0);
        controller.setTextBlockIndex(0);
        controller.copyToRightAllOnAction();
        Assert.assertEquals(right1_1,model.getArrangedText(0,0));
    }

    /*
    * tabMenuItem test
    * */

    @Test
    public void testNewTabMenuItemOnAction() {
        // 버튼이 비활성화되었는가
        // 탭번호가 증가했는가
        int tNum = controller.getTabNum();
        controller.newTabMenuItemOnAction();
        Assert.assertEquals(new boolean[]{true, true, true, true, true, true, true, true, true, true},controller.getButtonDisabled());
        Assert.assertEquals(tNum+1,controller.getTabNum());

    }


    @Test
    public void testTabCloseAction() {
        int tNum = controller.getTabNum();
        controller.tabCloseAction();
        Assert.assertEquals(tNum-1,controller.getTabNum());
    }

    @Test
    public void testCloseTabMenuItemOnAction() {
        model.newModel(1);
        model.newModel(2);
        controller.closeTabMenuItemOnAction();


    }

    @Test
    public void testCloseTabAllMenuItemOnAction() {
        model.newModel(1);
        model.newModel(2);
        model.newModel(3);
        controller.closeTabAllMenuItemOnAction();
        // 0번째 탭의 모델이 없다면 아무런 것도 켜져있지않다
        Assert.assertEquals(false,model.isOpen(0,0));
        Assert.assertEquals(false,model.isOpen(0,1));

    }




}
