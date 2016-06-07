package Controller;

import javafx.scene.control.TextArea;

/**
 * Created by haro21 on 2016. 6. 4..
 */
public class StatusController {
    private TextArea area;
    private String name;
    private boolean meue_flag = false;


    public void addStatus(String str){
        if(meue_flag) {
            meue_flag = false;
            return;
        }
        String txt = area.getText();
        txt += str + '\n';

        area.setText(txt);
    }

    public void setMeueFlag(){
        this.meue_flag = true;
    }

    public void addStatusWithName(String str){
        if(meue_flag) {
            meue_flag = false;
            return;
        }
        String txt = area.getText();
        txt += str + ": " + name + '\n';

        area.setText(txt);
    }

    public void setFileName(String str){
        this.name = str;
    }

    public StatusController(TextArea area){
        this.area = area;
    }
}
