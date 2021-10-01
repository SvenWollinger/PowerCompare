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
        for(String key : displayNameList.keySet()) {
            if(!other.displayNameList.containsKey(key)) {
                System.out.println("Removed entry: " + displayNameList.get(key).nd());
            }
            //System.out.println("Comparing: " + key + ". Exists in other: " + other.displayNameList.containsKey(key));
        }
    }
}
