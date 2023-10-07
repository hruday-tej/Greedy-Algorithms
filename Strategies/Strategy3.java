package Strategies;

import java.util.*;
import java.util.concurrent.TimeUnit;

class Strategy3Comparator implements Comparator<Bag> {
    @Override
    public int compare(Bag bag1, Bag bag2) {

        // If totalDevices is same pick bag with minimum index.
        if (bag1.totalDevices == bag2.totalDevices) {
            return Integer.compare(bag1.index, bag2.index);
        }
        // If two different totalDevicess pick minimum totalDevices.
        return Integer.compare(bag1.totalDevices, bag2.totalDevices);
    }
}

public class Strategy3 {
    public int n;
    public int k;
    public Bag[] bags;
    PriorityQueue<Bag> priorityQueue;
    public Strategy3(int n, int k, Bag[] bags) {
        this.n = n;
        this.k = k;
        this.bags = bags;
        priorityQueue = new PriorityQueue<>(new Strategy3Comparator());
        for (int itr = 0; itr < bags.length; itr++) {
            priorityQueue.add(bags[itr]);
        }
    }
    

    public double CalculateTimetaken() {
        long startTime = System.nanoTime();

        // Run a loop k times.
        while (k-- > 0) {
            // Pick the bag with minimum workingDevices/totalDevices ratio.
            Bag bagWithMinRatio = priorityQueue.poll();
            // Add 1 workingDevices device to the bag
            bagWithMinRatio.workingDevices += 1;
            bagWithMinRatio.totalDevices += 1;
            // Re-insert the bag to the minHeap so that it can be picked later.
            priorityQueue.add(bagWithMinRatio);
        }
        long endTime = System.nanoTime();
        // System.out.println(y-x);
        return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);

    }

    public double execute(){
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
        PriorityQueue<Bag> bags = new PriorityQueue<>(new Strategy3Comparator());
        // Iterate to take input.
        for (int i = 0; i < n; i++) {
            String[] counts = sc.nextLine().trim().split(" ");
            bags.add(new Bag(i, Integer.parseInt(counts[0]), Integer.parseInt(counts[1])));
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
        String output = pickedBags.toString().replace(",", " ")
                .replace("[", "")
                .replace("]", "");
        System.out.println(output);
    }
}