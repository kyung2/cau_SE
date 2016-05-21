package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.ListAdapter;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by woojin on 2016-05-21.
 */
public class MyListView extends ListView<String> {
    public ObservableList<String> list = FXCollections.observableArrayList();

    public MyListView(){
        super();
    }

    public MyListView(String[] s){
        super();
        list = FXCollections.observableArrayList(s);
        this.setItems(list);
    }

    public MyListView(ArrayList<ArrayList<String>> blocks){
        super();
        for(int i=0, n=blocks.size();i<n;i++) {
            ArrayList<String> lines = blocks.get(i);
            String one_block = new String();
            for(int j = 0, m=lines.size(); j<m;j++){
                one_block += lines.get(j);
            }
            list.add(i,one_block);
        }
        super.setItems(list);
    }
    public void setBlocks(ArrayList<ArrayList<String>> blocks){
        for(int i=0, n=blocks.size();i<n;i++) {
            ArrayList<String> lines = blocks.get(i);
            String one_block = new String();
            for(int j = 0, m=lines.size(); j<m;j++){
                one_block += lines.get(j);
            }
            list.add(i,one_block);
        }
        super.setItems(list);
    }
    public void setColorsOnBlock(int index){

    }
}
