package Strategies;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Strategies.Bag;

class Strategy1Comparator implements Comparator<Bag> {
    /*
     * We set this comparator so that the priority queue is arranged according to
     * the minimum number of working devices.
     * If the no of working devices are same, then we arrange the order according to
     * the least index value.
     */
    @Override
    public int compare(Bag b1, Bag b2) {
        // TODO Auto-generated method stub
        if (b1.workingDevices == b2.workingDevices) {
            return Integer.compare(b1.index, b2.index);
        }
        return Integer.compare(b1.workingDevices, b2.workingDevices);
    }
}

public class Strategy1 {

    public static double timeForExecution;
    public int n;
    public int k;
    public Bag[] bags;
    private static final DecimalFormat decimalFormatter = new DecimalFormat("0.000000000");
    PriorityQueue<Bag> priorityQueue;

    public Strategy1(int n, int k, Bag[] bags) {
        this.n = n;
        this.k = k;
        this.bags = bags;
        this.priorityQueue = new PriorityQueue<Bag>(n, new Strategy1Comparator());
        for (int itr = 0; itr < bags.length; itr++) {
            priorityQueue.add(bags[itr]);
        }
    }

    public double execute() {
        long x = System.nanoTime();
        // System.out.println(startTime);
        

        while (k > 0) {
            /*
             * While there are extra devices to be added to the bags
             * according to strategy-1 we pick the bag with the least amount of devices
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
            k--;
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
        Scanner scanner = new Scanner(System.in);
        // Reading the input for n and k
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        // Initializing the arrays to store the data
        Bag[] bags = new Bag[n];
        double startTime = System.currentTimeMillis();
        long x = System.nanoTime();
        PriorityQueue<Bag> pq = new PriorityQueue<Bag>(n, new Strategy1Comparator());
        List<Integer> pickedBags = new ArrayList<>();
        for (int iterator = 0; iterator < n; iterator++) {
            int inputWorkingDevices = scanner.nextInt();
            int inputTotalDevices = scanner.nextInt();
            Bag inputBag = new Bag(inputWorkingDevices, inputTotalDevices, iterator);
            bags[iterator] = inputBag;
            pq.add(inputBag);
        }
        scanner.close();

        while (k > 0) {
            /*
             * While there are extra devices to be added to the bags
             * according to strategy-1 we pick the bag with the least amount of devices
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
        double stopTime = System.currentTimeMillis();
        long y = System.nanoTime();

        System.out.println(pickedBags.toString().replace(",", " ")
                .replace("[", "")
                .replace("]", ""));

        timeForExecution = Double.parseDouble(decimalFormatter.format(stopTime))
                - Double.parseDouble(decimalFormatter.format(startTime));
        timeForExecution = Double.parseDouble(decimalFormatter.format(timeForExecution));
    }

}