import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class PartOne{
    public static void main(String[] args) throws IOException {
        final String INPUT_FILE = "bigData.txt";

        Scanner in = new Scanner(new File(INPUT_FILE));

        int numSafe = 0;

        outerLoop:
        while(in.hasNextLine()){

            String[] line = in.nextLine().split(" ");
            int[] nums = new int[line.length];

            for(int i = 0; i < line.length; i ++){
                nums[i] = Integer.parseInt(line[i]);
            }

            int incOrDec = nums[1] - nums[0];

            for(int i = 1; i < nums.length; i ++){
                int temp = nums[i] - nums[i - 1];
                if(temp * incOrDec <= 0 || Math.abs(temp) > 3){
                    continue outerLoop;
                }
            }
            numSafe ++;

        }

        System.out.println(numSafe);
    }
}