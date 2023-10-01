import java.util.*;

class Bag {
        int index;
        int working;
        int total;
        Bag(int index,int working,int total) {
            this.index = index;
            this.working = working;
            this.total = total;
        }
        @Override
        public String toString() {
            return "index: "+this.index + " working: "+this.working+" total: "+this.total;
        }
    }

class BagComparator implements Comparator<Bag> {
    @Override
    public int compare(Bag bag1,Bag bag2) {

        // If total is same pick bag with minimum index.
        if(bag1.total==bag2.total) {
            return Integer.compare(bag1.index, bag2.index);
        }
        // If two different totals pick minimum total.
        return Integer.compare(bag1.total, bag2.total);
    }
}

public class STRAT3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int k = Integer.parseInt(input[1]);
        int n= Integer.parseInt(input[0]);
        // Intialize minHeap of type Bag with BagComparator.
        PriorityQueue<Bag> bags = new PriorityQueue<>(new BagComparator());
        // Iterate to take input.
        for(int i=0;i<n;i++) {
            String[] counts = sc.nextLine().trim().split(" ");
            bags.add(new Bag(i,Integer.parseInt(counts[0]),Integer.parseInt(counts[1])));
        }
        // close input scanner
        sc.close();
        // Initialize a list to store the bags that are picked in order.
        List<Integer> pickedBags = new ArrayList<>();
        // Run a loop k times.
        while(k-- > 0) {
            // Pick the bag with minimum working/total ratio.
            Bag bagWithMinRatio = bags.poll();
            // Add 1 working device to the bag
            bagWithMinRatio.working +=1;
            bagWithMinRatio.total +=1;
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