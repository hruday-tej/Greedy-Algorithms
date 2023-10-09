package Strategies;
// package Greedy-Algorithms.Strategy-4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Strategy4Comparator implements Comparator<Bag> {
    /*
     * We set this comparator so that the priority queue is arranged according to
     * the minimum number of working devices.
     * If the no of working devices are same, then we arrange the order according to
     * the least index value.
     */
    @Override
    public int compare(Bag b1, Bag b2) {
        /*
         * Here we calculate the ratios for the increase in the percentage and then
         * perform heapify
         * It will place the ratio of the greatest at the top of the priority queue
         */
        // TODO Auto-generated method stub
        // System.out.println((double)((double)b1.workingDevices/(double)b1.totalDevices));
        // System.out.println("working 1 -> "+b1.workingDevices+" - "+b1.totalDevices);
        // System.out.println("working 2 -> "+b2.workingDevices+" - "+b2.totalDevices);
        double ratio1 = ((double) ((double) (b1.workingDevices + 1) / (double) (b1.totalDevices + 1))
                - (double) ((double) (b1.workingDevices) / (double) (b1.totalDevices)));
        double ratio2 = ((double) ((double) (b2.workingDevices + 1) / (double) (b2.totalDevices + 1))
                - (double) ((double) (b2.workingDevices) / (double) (b2.totalDevices)));
        // System.out.println("ratio 1 -> "+ratio1+" ratio 2 -> "+ratio2);
        if (ratio1 != ratio2) {
            return Double.compare(ratio2, ratio1);
        }
        
        return Integer.compare(b1.index, b2.index);
    }
}

public class Strategy4 {
    public int n;
    public int k;
    public Bag[] bags;
    PriorityQueue<Bag> priorityQueue;

    public Strategy4(int n, int k, Bag[] bags) {
        this.n = n;
        this.k = k;
        this.bags = bags;
        priorityQueue = new PriorityQueue<Bag>(n, new Strategy4Comparator());
                for (int iterator = 0; iterator < n; iterator++) {

            // System.out.println("BAGSSS -> "+bags[iterator].totalDevices + " " +
            // bags[iterator].workingDevices);
            priorityQueue.add(bags[iterator]);
        }

    }

    public double execute() {
        long startTime = System.nanoTime();

        List<Integer> pickedBags = new ArrayList<>();
        while (k > 0) {
            /*
             * While there are extra devices to be added to the bags
             * according to strategy-4 we pick the bag with the greatest increase in
             * percentage
             * This will be automatically achieved using priority queue polling
             * then we add one device and then re-insert the bag to the priority queue
             */
            Bag minimumBag = priorityQueue.poll();
            // System.out.println(minimumBag.index+" ---> index <---
            // "+minimumBag.workingDevices+" --> total devices <--" +
            // minimumBag.totalDevices);
            minimumBag.workingDevices += 1;
            minimumBag.totalDevices += 1;
            priorityQueue.add(minimumBag);
            pickedBags.add(minimumBag.index);
            k--;
        }
        long endTime = System.nanoTime();
        // System.out.println(y-x);
        return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);

    }
    public double CalculatePercentage(){
        System.out.println("in 44");
        PriorityQueue <Bag> pqDup = priorityQueue;
        double percentage = 0;
        while(pqDup.size()!=0){
            Bag b = pqDup.poll();
            percentage += b.calculatePercentage();
        }

        return percentage/n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Reading the input for n and k
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        // Initializing the arrays to store the data
        Bag[] bags = new Bag[n];
        PriorityQueue<Bag> pq = new PriorityQueue<Bag>(n, new Strategy4Comparator());
        List<Integer> pickedBags = new ArrayList<>();
        for (int iterator = 0; iterator < n; iterator++) {
            int inputWorkingDevices = scanner.nextInt();
            int inputTotalDevices = scanner.nextInt();
            Bag inputBag = new Bag(inputWorkingDevices, inputTotalDevices, iterator);
            bags[iterator] = inputBag;
            // System.out.println("BAGSSS -> "+bags[iterator].totalDevices + " " +
            // bags[iterator].workingDevices);
            pq.add(inputBag);
        }
        scanner.close();

        while (k > 0) {
            /*
             * While there are extra devices to be added to the bags
             * according to strategy-4 we pick the bag with the greatest increase in
             * percentage
             * This will be automatically achieved using priority queue polling
             * then we add one device and then re-insert the bag to the priority queue
             */
            Bag minimumBag = pq.poll();
            // System.out.println(minimumBag.index+" ---> index <---
            // "+minimumBag.workingDevices+" --> total devices <--" +
            // minimumBag.totalDevices);
            minimumBag.workingDevices += 1;
            minimumBag.totalDevices += 1;
            pq.add(minimumBag);
            pickedBags.add(minimumBag.index);
            k--;
        }

        System.out.println(pickedBags.toString().replace(",", "")
                .replace("[", "")
                .replace("]", "").replace("-n", ""));

    }
}
