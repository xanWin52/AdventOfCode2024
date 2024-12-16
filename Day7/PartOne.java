import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class PartOne{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bigData.txt"));
        long total = 0;

        while(in.hasNextLine()){
            String[] line = in.nextLine().split(" ");
            long target = Long.parseLong(line[0].substring(0, line[0].length() - 1));
            long[] nums = new long[line.length - 1];
            for(int i = 1; i < line.length; i ++){
                nums[i-1] = Long.parseLong(line[i]);
            }

            if(works(target, nums[0], nums, 1)){
                total += target;
            }
        }
        System.out.println(total);
    }

    private static boolean works(long target, long runningTotal, long[] nums, int ind){
        if(ind == nums.length){
            return target == runningTotal;
        }

        return works(target, runningTotal * nums[ind], nums, ind + 1) || works(target, runningTotal + nums[ind], nums, ind + 1);

    }

}