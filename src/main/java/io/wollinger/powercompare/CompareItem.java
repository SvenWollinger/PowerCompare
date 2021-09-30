package io.wollinger.powercompare;

import java.util.ArrayList;
import java.util.HashMap;

public class CompareItem {
    HashMap<String, Data> displayNameList = new HashMap<>();

    public CompareItem(ArrayList<Data> list) {
        for(Data data : list) {
            displayNameList.put(data.getDisplayName(), data);
        }
    }

    public void compare(CompareItem other) {
        
    }
}
