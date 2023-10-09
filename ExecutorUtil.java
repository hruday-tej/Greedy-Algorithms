import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import Strategies.Bag;
import Strategies.Strategy1;
import Strategies.Strategy2;
import Strategies.Strategy3;
import Strategies.Strategy4;


public class ExecutorUtil {

    public static void generateInput() {
        String inp = "";
        Random random = new Random();
        // int n = random.nextInt(5000) + 5000; // Random integer between 20 and 30
        int n = 10000;
        // int k = random.nextInt(300)+300;
        int k = 1500;
        inp = n + " " + k + "\n";

        for (int i = 0; i < n; i++) {
            int total = random.nextInt(101) + 100; // Random integer between 100 and 200
            // double perc = (random.nextInt(100) + 40) / 100.0; // Random double between 0.40 and 0.90
            int working = random.nextInt(total);
            inp = inp + working + " " + total + "\n";
        }

        try (FileWriter writer = new FileWriter("Inputs/Inputxx.txt")) {
            writer.write(inp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateInput();
        String inputFilePath = "./Inputs/Inputxx.txt";
        try {
            File file = new File(inputFilePath);
            if (!file.exists()) {
                System.out.println("File does not exist.");
                System.exit(1);
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int n, k;
            Bag[] bags;
            line = bufferedReader.readLine();
            String[] lineArray = line.split(" ");
            n = Integer.parseInt(lineArray[0]);
            k = Integer.parseInt(lineArray[1]);
            bags = new Bag[n];
            for (int itr = 0; itr < n; itr++) {
                String currentInputLine[] = bufferedReader.readLine().split(" ");
                Bag inputBag = new Bag(Integer.parseInt(currentInputLine[0]), Integer.parseInt(currentInputLine[1]),
                        itr);
                bags[itr] = inputBag;
            }
            // Close the BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();
            Strategy1 s1 = new Strategy1(n, k, bags);
            s1.execute();
            System.out.println("Strategy 1 ->" + s1.CalculatePercentage());
            Strategy2 s2 = new Strategy2(n, k, bags);
            s2.execute();
            System.out.println("Strategy 2 ->" + s2.CalculatePercentage());
            Strategy3 s3 = new Strategy3(n, k, bags);
            s3.execute();
            System.out.println("Strategy 3 ->" + s3.CalculatePercentage());
            Strategy4 s4 = new Strategy4(n, k, bags);
            s4.execute();
            System.out.println("Strategy 4 ->" + s4.CalculatePercentage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}