import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class PartOne{
    public static void main(String[] args) throws IOException{
        final String INPUT_FILE = "bigData.txt";

        Scanner in = new Scanner(new File(INPUT_FILE));
        PriorityQueue<Integer> a1 = new PriorityQueue<Integer>();
        PriorityQueue<Integer> a2 = new PriorityQueue<Integer>();
        while(in.hasNextLine()){
            int temp1 = in.nextInt();
            int temp2 = in.nextInt();
            a1.add(temp1);
            a2.add(temp2);
        }
        in.close();

        int total = 0;
        while(!a1.isEmpty()){
            total += Math.abs(a1.poll() - a2.poll());
        }

        System.out.println(total);
    }
}