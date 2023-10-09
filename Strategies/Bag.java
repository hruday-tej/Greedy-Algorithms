package Strategies;
public class Bag {
    int workingDevices;
    int totalDevices;
    int index;

    public Bag(int workingDevices, int totalDevices, int index) {
        this.workingDevices = workingDevices;
        this.totalDevices = totalDevices;
        this.index = index;
    }
    public int calculatePercentage() {
        return (int) ((workingDevices * 100) / totalDevices);
    }
    public String toString() {
        return "index: "+this.index + " working: "+this.workingDevices+" total: "+this.totalDevices;
    }
}