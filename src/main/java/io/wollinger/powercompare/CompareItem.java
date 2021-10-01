package io.wollinger.powercompare;

import java.util.ArrayList;
import java.util.HashMap;

public class CompareItem {
    private String filename;
    private final HashMap<String, Data> displayNameList = new HashMap<>();

    public CompareItem(ArrayList<Data> list, String filename) {
        this.filename = filename;
        for(Data data : list) {
            displayNameList.put(data.getDisplayName(), data);
        }
    }

    ArrayList<Data> running = new ArrayList<>();
    ArrayList<Data> stopped = new ArrayList<>();
    ArrayList<Data> removed = new ArrayList<>();
    ArrayList<Data> added = new ArrayList<>();

    public String compare(CompareItem other) {
        String output = "";
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

        for(String key : other.displayNameList.keySet()) {
            if(!displayNameList.containsKey(key))
                added.add(other.displayNameList.get(key));
        }

        output += "Old file: " + filename + "\n";
        output += "New file: " + other.filename + "\n";
        output += "\nChanged items:\n";
        output += "Running:\n";
        for(Data data : running)
            output += data.getStatus().replaceAll(" ", "") + ": " + data.nd() + "\n";
        output += "\nStopped:\n";
        for(Data data : stopped)
            output += data.getStatus().replaceAll(" ", "") + ": " + data.nd() + "\n";
        output += "\nRemoved:\n";
        for(Data data : removed)
            output += "Removed: " + data.nd() + "\n";
        output += "\nAdded:\n";
        for(Data data : added)
            output += data.getStatus().replaceAll(" ", "") + ": " + data.nd() + "\n";
        System.out.println(output);
        return output;
    }
}
