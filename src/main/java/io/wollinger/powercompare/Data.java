package io.wollinger.powercompare;

public class Data {
    private final String status;
    private final String name;
    private final String displayName;

    public Data(String line) {
        status = line.substring(0, 8);
        name = line.substring(9, 28);
        displayName = line.substring(28);
    }

    public String nd() {
        return displayName + " " + name;
    }

    public String getStatus() {
        return status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean compareStatus(Data other) {
        return status.equals(other.status);
    }

    public String toString() {
        return "Status: " + status + " Name: " + name + " DisplayName: " + displayName;
    }
}
