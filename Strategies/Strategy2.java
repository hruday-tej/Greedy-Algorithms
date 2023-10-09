package Strategies;

import java.util.*;
import java.util.concurrent.TimeUnit;

class Strategy2Comparator implements Comparator<Bag> {
    @Override
    public int compare(Bag bag1, Bag bag2) {
        // Floor or ceil
        double ratio1 = (double) bag1.workingDevices / bag1.totalDevices;
        double ratio2 = (double) bag2.workingDevices / bag2.totalDevices;

        // If ratio is same pick bag with minimum index.
        if (ratio1 == ratio2) {
            return Integer.compare(bag1.index, bag2.index);
        }
        // If two different rations pick minimum ratio.
        return Double.compare(ratio1, ratio2);
    }
}

public class Strategy2 {

    public static double timeForExecution;
    public int n;
    public int k;
    public Bag[] bags;
    PriorityQueue<Bag> priorityQueue;

    public Strategy2(int n, int k, Bag[] bags) {
        this.n = n;
        this.k = k;
        this.bags = bags;
        priorityQueue = new PriorityQueue<>(new Strategy2Comparator());
        for (int itr = 0; itr < bags.length; itr++) {
            priorityQueue.add(bags[itr]);
        }
    }
    public double execute(){
        long x = System.nanoTime();
        int m = k;
        while (m-- > 0) {
            // Pick the bag with minimum workingDevices/totalDevices ratio.
            Bag bagWithMinRatio = priorityQueue.poll();
            // Add 1 workingDevices device to the bag
            bagWithMinRatio.workingDevices += 1;
            bagWithMinRatio.totalDevices += 1;
            // Re-insert the bag to the minHeap so that it can be picked later.
            priorityQueue.add(bagWithMinRatio);
        }
        long y = System.nanoTime();
        return TimeUnit.MILLISECONDS.convert(y - x, TimeUnit.NANOSECONDS);
    }
    public double CalculatePercentage(){
        PriorityQueue <Bag> pqDup = priorityQueue;
        double percentage = 0;
        while(pqDup.size()!=0){
            Bag b = pqDup.poll();
            percentage += b.calculatePercentage();
        }

        return percentage/n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int k = Integer.parseInt(input[1]);
        int n = Integer.parseInt(input[0]);
        // Intialize minHeap of type Bag with BagComparator.
        PriorityQueue<Bag> bags = new PriorityQueue<>(new Strategy2Comparator());
        // Iterate to take input.
        for (int i = 0; i < n; i++) {
            String[] counts = sc.nextLine().trim().split(" ");
            bags.add(new Bag(Integer.parseInt(counts[0]), Integer.parseInt(counts[1]), i));
        }
        // close input scanner
        sc.close();
        // Initialize a list to store the bags that are picked in order.
        List<Integer> pickedBags = new ArrayList<>();
        // Run a loop k times.
        while (k-- > 0) {
            // Pick the bag with minimum workingDevices/totalDevices ratio.
            Bag bagWithMinRatio = bags.poll();
            // Add 1 workingDevices device to the bag
            bagWithMinRatio.workingDevices += 1;
            bagWithMinRatio.totalDevices += 1;
            // Re-insert the bag to the minHeap so that it can be picked later.
            bags.add(bagWithMinRatio);
            // add the picked bag index to result.
            pickedBags.add(bagWithMinRatio.index);
        }
        // print out result.
        String output = pickedBags.toString().replace(",", "")
                .replace("[", "")
                .replace("]", "");
        System.out.println(output);
    }
}