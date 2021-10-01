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

    ArrayList<Data> running = new ArrayList<>();
    ArrayList<Data> stopped = new ArrayList<>();
    ArrayList<Data> removed = new ArrayList<>();
    ArrayList<Data> added = new ArrayList<>();

    public void compare(CompareItem other) {
        for(String key : displayNameList.keySet()) {
            if(!other.displayNameList.containsKey(key)) {
                removed.add(displayNameList.get(key));
            } else {
                Data ourData = displayNameList.get(key);
                Data otherData = other.displayNameList.get(key);
                if(!ourData.compareStatus(otherData)) {
                    if(otherData.getStatus().toLowerCase().contains("running"))
                        running.add(otherData);
                    else
                        stopped.add(otherData);
                 }
            }
        }

        System.out.println("Changed items:\n");
        System.out.println("Running:");
        for(Data data : running)
            System.out.println(data.getStatus().replaceAll(" ", "") + ": " + data.nd());
        System.out.println("\nStopped:");
        for(Data data : stopped)
            System.out.println(data.getStatus().replaceAll(" ", "") + ": " + data.nd());
        System.out.println("\nRemoved:");
        for(Data data : removed)
            System.out.println("Removed: " + data.nd());
        System.out.println("\nAdded:");
        for(Data data : added)
            System.out.println(data.getStatus().replaceAll(" ", "") + ": " + data.nd());
    }
}
