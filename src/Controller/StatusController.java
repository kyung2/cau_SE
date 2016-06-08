package Controller;

import javafx.scene.control.TextArea;

/**
 * Logging user Actions.
 * Created by hyunkyung on 2016. 6. 4..
 * @author hyunkyung
 */

public class StatusController {
    private TextArea area;
    private String name;
    private boolean menu_flag = false;


    /**
     * 각 액션들이 호출되면 상황에 맞는 text를 받아서 상태 text 공간에 출력
     * @param str 액션에서 지정해 주는 텍스트
     */
    public void addStatus(String str){
        if(menu_flag) {
            menu_flag = false;
            return;
        }
        String txt = area.getText();
        txt += str + '\n';

        area.setText(txt);
    }

    /**
     * copy Action 경우 동작 완료시에 CompareAction 이 실행 되는데 그
     * 당시 compate 메세지 출력을 받기 위함
     */
    public void setMenuFlag(){
        this.menu_flag = true;
    }

    /**
     * 각 액션들이 호출되면 상황에 맞는 text를 받아서 상태 text 공간에 출력
     * 하지만 끝에 ":설정된 파일 이름"을 추가해줌
     * @param str 액션에서 지정해 주는 텍스트
     */
    public void addStatusWithName(String str){
        if(menu_flag) {
            menu_flag = false;
            return;
        }
        String txt = area.getText();
        txt += str + ": " + name + '\n';

        area.setText(txt);
    }

    /**
     * @param str 파일이름 변수 설정
     */
    public void setFileName(String str){
        this.name = str;
    }

    /**
     * @param area TextArea를 받아옴.
     */
    public StatusController(TextArea area){
        this.area = area;
    }
}
