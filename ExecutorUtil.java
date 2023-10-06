import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import Strategies.Bag;
import Strategies.Strategy1;
import Strategies.Strategy2;
import Strategies.Strategy3;

public class ExecutorUtil{
    public static void main(String[] args) {
        String inputFilePath = "./Inputs/Input.txt";
        try{
            File file = new File(inputFilePath);
            if (!file.exists()) {
                System.out.println("File does not exist.");
                System.exit(1);
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int n,k;
            Bag [] bags;
            line = bufferedReader.readLine();
            String[] lineArray = line.split(" ");
            n = Integer.parseInt(lineArray[0]);
            k = Integer.parseInt(lineArray[1]);
            bags = new Bag[n];
            for(int itr=0;itr<n;itr++){
                String currentInputLine[] = bufferedReader.readLine().split(" ");
                Bag inputBag = new Bag(Integer.parseInt(currentInputLine[0]), Integer.parseInt(currentInputLine[1]), itr);
                bags[itr] = inputBag;
            }
            // Close the BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();
            Strategy1 s1 = new Strategy1(n, k, bags);
            System.out.println("Strategy 1 ->" + s1.CalculateTimetaken());
            Strategy1 s2 = new Strategy1(n, k, bags);
            System.out.println("Strategy 2 ->" + s2.CalculateTimetaken());
            Strategy1 s3 = new Strategy1(n, k, bags);
            System.out.println("Strategy 3 ->" + s3.CalculateTimetaken());
            Strategy1 s4 = new Strategy1(n, k, bags);
            System.out.println("Strategy 4 ->" + s4.CalculateTimetaken());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}