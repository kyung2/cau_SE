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

    //*********테스트용 모델 만들기********
    //처음부터 틀린 경우 / 두번째부터 틀린 경우
    //사이즈가 아주 작은 경우(한개만 틀림)
    //보통 사이즈 인 경우
    //text_block_index가 맨 처음에 있는 경우/ 중간에 있는 경우/ 맨 마지막에 있는 경우


    public void testInitialize(){

    }

    @Test
    public void testCompareOnAction(){

    }

    /*
    * differenceAction test
    * */
    //인덱스 부분이 넘겨주어야 할 것과 동일한지 확인하면 됨
    public void testNextDifferenceOnAction() {
        int size = model.getArrangedGroupSpace(0).size();
        // 테스트 케이스.. 사이즈가 n이고 현재 인덱스가 x일때 비교하기


    }
    public void testPreviousDifferenceOnAction(){
        int rightCase = model.getArrangedGroupNum(0).get(0);
        // 테스트 케이스.. 사이즈가 n이고 현재 인덱스가 x일때 비교하기

        Assert.assertEquals(rightCase,controller.getTextBlockIndex());

    }

    //firstTest와 lastTest가 필요한지?
    public void testFirstDifferenceOnAction(){
        int rightCase = model.getArrangedGroupNum(0).get(0);
        if(rightCase == 0 ) //짝수번째가 틀렸을 경우, 처음 틀린 블럭의 index는 1
            Assert.assertEquals(1,controller.getTextBlockIndex());
        else // 아닐 경우 0이 되어야 함
            Assert.assertEquals(0,controller.getTextBlockIndex());

    }
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

    public void testNowDifferenceOnAction() {


    }

    /*
    * copyToAction test
    * */
    //내용이 같은지 확인
    public void testCopyToLeftOnAction(){

    }
    public void testCopyToRightOnAction(){

    }
    public void testCopyToRightAllOnAction(){

    }
    public void testCopyToLeftAllOnAction(){

    }

    /*
    * tabMenuItem test
    * */

    public void testNewTabMenuItemOnAction() {

    }
    public void testOpenMenuItemOnAction() {

    }
    public void testSaveMenuItemOnAction() {

    }
    public void testSaveRightFileMenuItemOnAction() {

    }
    public void testSaveLeftFileMenuItemOnAction() {

    }

    public void testCloseMenuItemOnAction() {

    }

    public void testHelpMenuItemOnAction() {

    }

    public void testProgramInformationMenuItemOnAction() {

    }

    /*
    *  tabMenuItem test
    * */

    public void testTabCloseAction() {

    }
    public void testCloseTabMenuItemOnAction() {

    }
    public void testCloseTabAllMenuItemOnAction() {

    }
    public void testTabMenuItemOnAction(int index) {

    }
    public void testCompareButtonAndMenuItem(boolean compare) {

    }
    public void testPreDifferenceButtonAndMenuItem(boolean pre) {

    }
    public void testNextDifferenceButtonAndMenuItem(boolean next){

    }


    /*
    * 타기능 구현용 함수 test
    * */
    public void testChangeScrollbar(int index){

    }
    public String[] testMakeToolbarStage(){
        return new String[1];
    }
    public void testInitTextAreaAndListOnTab(){

    }

    /*
    public String[] testMakeStringsForList(ArrayList<String> arrayList, ArrayList<Integer> index) {

    }
    public String testArrayListToString(ArrayList<String> arrayList){

    }
    public ArrayList<String> testStringToArrayList(java.lang.String s){

    }

    리턴 값 문제로 잠시 주석표시
    */

    public void testInit(){

    }


}
