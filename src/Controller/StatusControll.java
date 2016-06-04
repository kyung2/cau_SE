package Controller;

import javafx.scene.control.TextArea;

/**
 * Created by haro21 on 2016. 6. 4..
 */
public class StatusControll  {
    private TextArea area;
    private String name;


    public void addStatus(String str){
        String txt = area.getText();

        area.setText(txt);
//        area.setScrollTop(area.getMaxHeight());
    }

    public void addStatusWithName(String str){
        String txt = area.getText();
        txt += str + ": " + name + '\n';



        area.setText(txt);
//        area.setScrollTop(0.0);
//        area.setScrollTop(area.getMaxHeight());
    }

    public void setFileName(String str){
        this.name = str;
    }

    public StatusControll (TextArea area){
        this.area = area;
    }
}
