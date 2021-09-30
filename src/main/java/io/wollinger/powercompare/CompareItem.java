package io.wollinger.powercompare;

import java.util.ArrayList;
import java.util.HashMap;

public class CompareItem {
    HashMap<String, Data> nameList = new HashMap<>();
    HashMap<String, Data> displayNameList = new HashMap<>();

    public CompareItem(ArrayList<Data> list) {
        for(Data data : list) {
            nameList.put(data.getName(), data);
            displayNameList.put(data.getDisplayName(), data);
        }
    }

    public void compare(CompareItem other) {

    }
}
