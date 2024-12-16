import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class PartTwo{
    public static void main(String[] args) throws IOException {
        final String INPUT_FILE = "bigData.txt";

        Scanner in = new Scanner(new File(INPUT_FILE));

        int numSafe = 0;

        while(in.hasNextLine()){

            String[] line = in.nextLine().split(" ");
            int[] nums = new int[line.length];

            for(int i = 0; i < line.length; i ++){
                nums[i] = Integer.parseInt(line[i]);
            }

            if(safe(nums) || worksIfRemoved(nums)){
                numSafe ++;
            }

        }

        System.out.println(numSafe);
    }

    private static boolean safe(int[] nums){
        int incOrDec = nums[1] - nums[0];

        for(int i = 1; i < nums.length; i ++){
            int temp = nums[i] - nums[i - 1];
            if(temp * incOrDec <= 0 || Math.abs(temp) > 3){
                return false;
            }
        }
        return true;
    }

    private static boolean worksIfRemoved(int[] arr){
        for(int i = 0; i < arr.length; i ++){
            int[] modArray = new int[arr.length - 1];
            int counter = 0;
            int oCounter = 0;

            while(counter < modArray.length){
                if(oCounter == i){
                    oCounter ++;
                }
                modArray[counter] = arr[oCounter];
                counter ++;
                oCounter ++;
            }

            if(safe(modArray)){
                return true;
            }
        }
        return false;
    }
}