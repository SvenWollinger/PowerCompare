package io.wollinger.powercompare;

public class Data {
    private String status;
    private String name;
    private String displayName;

    public Data(String line) {
        status = line.substring(0, 8);
        name = line.substring(9, 28);
        displayName = line.substring(28);
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String toString() {
        return "Status: " + status + " Name: " + name + " DisplayName: " + displayName;
    }
}