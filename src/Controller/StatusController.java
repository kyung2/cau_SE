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
     * add status
     * @param str action component name
     * */
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
     * setting menuFlag
     */
    public void setMenuFlag(){
        this.menu_flag = true;
    }

    /**
     * addStatuswithname
     * @param str action component name*/
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
     * @param str */
    public void setFileName(String str){
        this.name = str;
    }

    public StatusController(TextArea area){
        this.area = area;
    }
}
