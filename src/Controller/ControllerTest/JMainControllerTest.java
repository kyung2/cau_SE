package Controller.ControllerTest;

import Controller.MainController;
import Model.ModelInterface;
import Model.ModelRealize;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016-06-01.
 */
public class JMainControllerTest {

    ModelInterface model;
    MainController controller;
    @Before
    public void setUp() throws Exception {
        model = ModelRealize.getInstance();
        model.newModel(0);
        controller = new MainController();
    }

    @After
    public void tearDown() throws Exception {
        model.closeModelAll();
    }

    //*********테스트용 모델 만들기*********
    //처음부터 틀린 경우 / 두번째부터 틀린 경우
    //사이즈가 아주 작은 경우(한개만 틀림)
    //보통 사이즈 인 경우(두개 이상 틀림)
    //text_block_index가 맨 처음에 있는 경우/ 중간에 있는 경우/ 맨 마지막에 있는 경우

    // case 0 : 처음부터 틀림, 작은 사이즈(블럭단위 사이즈 2), 인덱스 0
    // case 1 : 두번째부터 틀림, 작은 사이즈(블럭단위 사이즈 2), 인덱스 1
    // case 2 : 다틀림 -> 작은 사이즈(단일블럭), 인덱스 0
    // case 2-2 : 두번째부터 틀림, 약간 작은 사이즈 (블럭단위 사이즈 3), 현재 인덱스 1

    // case 3 : 처음부터 틀림, 보통 사이즈 (블럭단위 사이즈 7), 현재 블럭 인덱스 2 (중간)
    // case 4 : 처음부터 틀림, 보통 사이즈 (블럭단위 사이즈 7), 현재 블럭 인덱스 0 (처음)
    // case 5 : 처음부터 틀림, 보통 사이즈 (블럭단위 사이즈 7), 현재 블럭 인덱스 6 (끝)

    // case 6 : 처음부터 틀림, 보통 사이즈 (블럭단위 사이즈 8), 현재 블럭 인덱스 2 (중간)
    // case 7 : 처음부터 틀림, 보통 사이즈 (블럭단위 사이즈 8), 현재 블럭 인덱스 0 (처음)
    // case 8 : 처음부터 틀림, 보통 사이즈 (블럭단위 사이즈 8), 현재 블럭 인덱스 6 (끝)

    // case 9 : 두번째부터 틀림, 보통 사이즈 (블럭단위 사이즈 7), 현재 인덱스 3 (중간)
    // case 10 : 두번째부터 틀림, 보통 사이즈 (블럭단위 사이즈 7), 현재 인덱스 1 (처음)
    // case 11 : 두번째부터 틀림, 보통 사이즈 (블럭단위 사이즈 7), 현재 인덱스 5 (끝)

    // case 12 : 두번째부터 틀림, 보통 사이즈 (블럭단위 사이즈 8), 현재 인덱스 3 (중간)
    // case 13 : 두번째부터 틀림, 보통 사이즈 (블럭단위 사이즈 8), 현재 인덱스 1 (처음)
    // case 14 : 두번째부터 틀림, 보통 사이즈 (블럭단위 사이즈 8), 현재 인덱스 7 (끝)

    public void setTestModel(){

    }

    @Test
    public void testInitialize(){

    }

    @Test
    public void testCompareOnAction(){

    }

    /*
    * differenceAction test
    * */
    //인덱스 부분이 넘겨주어야 할 것과 동일한지 확인하면 됨
    @Test
    public void testNextDifferenceOnAction() {
        int size = model.getArrangedGroupSpace(0).size();
        // 테스트 케이스.. 사이즈가 n이고 현재 인덱스가 x일때 비교하기


    }

    @Test
    public void testPreviousDifferenceOnAction(){
        int rightCase = model.getArrangedGroupNum(0).get(0);
        // 테스트 케이스.. 사이즈가 n이고 현재 인덱스가 x일때 비교하기

        Assert.assertEquals(rightCase,controller.getTextBlockIndex());

    }

    //firstTest와 lastTest가 필요한지?
    @Test
    public void testFirstDifferenceOnAction(){
        int rightCase = model.getArrangedGroupNum(0).get(0);
        if(rightCase == 0 ) //짝수번째가 틀렸을 경우, 처음 틀린 블럭의 index는 1
            Assert.assertEquals(1,controller.getTextBlockIndex());
        else // 아닐 경우 0이 되어야 함
            Assert.assertEquals(0,controller.getTextBlockIndex());

    }

    @Test
    public void testLastDifferenceOnAction(){
        int rightCase;
        int size = model.getArrangedGroupSpace(0).size();
        if(model.getArrangedGroupNum(0).get(0) == 0) {    // 처음이 서로 같을 때
            if(size % 2 == 0) {
                rightCase = size - 2;
            }
            else{
                rightCase = size - 3;
            }
        }
        else {  // 처음부터 서로 다를 때
            if(size % 2 == 0) {
                rightCase = size - 1 ;
            }
            else{
                rightCase = size - 2;
            }
        }

        Assert.assertEquals(rightCase,controller.getTextBlockIndex());
    }

    @Test
    public void testNowDifferenceOnAction() {


    }

    /*
    * copyToAction test
    * */
    //내용이 같은지 확인
    @Test
    public void testCopyToLeftOnAction(){

    }

    @Test
    public void testCopyToRightOnAction(){

    }

    @Test
    public void testCopyToRightAllOnAction(){

    }

    @Test
    public void testCopyToLeftAllOnAction(){

    }

    /*
    * tabMenuItem test
    * */

    @Test
    public void testNewTabMenuItemOnAction() {

    }

    @Test
    public void testOpenMenuItemOnAction() {

    }

    @Test
    public void testSaveMenuItemOnAction() {

    }

    @Test
    public void testSaveRightFileMenuItemOnAction() {

    }

    @Test
    public void testSaveLeftFileMenuItemOnAction() {

    }


    @Test
    public void testCloseMenuItemOnAction() {

    }

    @Test
    public void testHelpMenuItemOnAction() {

    }

    @Test
    public void testProgramInformationMenuItemOnAction() {

    }

    /*
    *  tabMenuItem test
    * */

    @Test
    public void testTabCloseAction() {

    }

    @Test
    public void testCloseTabMenuItemOnAction() {

    }

    @Test
    public void testCloseTabAllMenuItemOnAction() {

    }

    @Test
    public void testTabMenuItemOnAction(int index) {

    }

    @Test
    public void testCompareButtonAndMenuItem(boolean compare) {

    }

    @Test
    public void testPreDifferenceButtonAndMenuItem(boolean pre) {

    }

    @Test
    public void testNextDifferenceButtonAndMenuItem(boolean next){

    }


    /*
    * 타기능 구현용 함수 test
    * */

    @Test
    public void testChangeScrollbar(int index){

    }

    @Test
    public String[] testMakeToolbarStage(){
        return new String[1];
    }

    @Test
    public void testInitTextAreaAndListOnTab(){

    }

    /*

    @Test
    public String[] testMakeStringsForList(ArrayList<String> arrayList, ArrayList<Integer> index) {

    }

    @Test
    public String testArrayListToString(ArrayList<String> arrayList){

    }

    @Test
    public ArrayList<String> testStringToArrayList(java.lang.String s){

    }

    리턴 값 문제로 잠시 주석표시
    */


    @Test
    public void testInit(){

    }


}
