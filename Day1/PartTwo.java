import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

public class PartTwo {
    public static void main(String[] args) throws IOException{
        final String INPUT_FILE = "bigData.txt";
        Scanner in = new Scanner(new File(INPUT_FILE));

        HashMap<Integer, Integer> left = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> right = new HashMap<Integer, Integer>();

        while(in.hasNextLine()){
            int curL = in.nextInt();
            int curR = in.nextInt();
            if(!left.containsKey(curL)){
                left.put(curL, 0);
            }
            if(!right.containsKey(curR)){
                right.put(curR, 0);
            }

            left.put(curL, left.get(curL) + 1);
            right.put(curR, right.get(curR) + 1);
        }
        in.close();

        int total = 0;
        for(Integer L : left.keySet()){
            if(right.containsKey(L)){
                total += left.get(L) * (L * right.get(L));
            }
        }
        System.out.println(total);
    }
}
