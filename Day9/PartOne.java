import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class PartOne{

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("bigData.txt"));
        String[] line = in.nextLine().split("");
        int[] nums = new int[line.length];
        for(int i = 0; i < nums.length; i ++){
            nums[i] = Integer.parseInt(line[i]);
        }
        int totalSpace = 0;
        for(int x : nums){
            totalSpace += x;
        }

        int[] memory = new int[totalSpace];

        int numToFill = 0;
        int ind = 0;
        for(int i = 0; i < nums.length; i ++){
            if(i % 2 == -0){
                for(int j = 0; j < nums[i]; j ++){
                    memory[ind++] = numToFill;
                }
                numToFill ++;
            } else {
                for(int j = 0; j < nums[i]; j ++){
                    memory[ind++] = -1;
                }
            }
        }
        
        int left = 0;
        int right = memory.length - 1;
        while(left < right){
            while(memory[right] == -1){
                right --;
            }
            if(memory[left] != -1){
                left ++;
            } else {
                memory[left] = memory[right];
                memory[right] = -1;
            }
        }

        int pos = 0;
        long result = 0;
        while(memory[pos] != -1){
            result += pos * memory[pos];
            pos ++;
        }

        System.out.println(result);
    }
}