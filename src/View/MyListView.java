package View;


import com.sun.javafx.scene.control.skin.ListViewSkin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.ListAdapter;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by woojin on 2016-05-21.
 */
public class MyListView extends ListView<String> {
    public static String Red = "red", Yellow = "yellow", Green = "green";

    public MyListView(){
        super();
    }

    public void setColorsOnBlock(int start, int end, String color){
        for(int index = start; index < end; index++){
            ((VirtualFlow) this.getChildren().get(0)).getCell(index).setStyle("-fx-background-color: " + color);
        }
    }

    public void setColorsOnBlock(int index, String color){
        ((VirtualFlow) this.getChildren().get(0)).getCell(index).setStyle("-fx-background-color: "+color);
    }
}
