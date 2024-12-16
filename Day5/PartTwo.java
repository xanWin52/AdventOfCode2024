import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class PartTwo{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bigData.txt"));

        HashMap<Integer, ArrayList<Integer>> comesAfter = new HashMap<Integer,ArrayList<Integer>>();

        String curLine = in.nextLine();
        while(!curLine.equals("")){
            String[] lineArr = curLine.split("\\|");
            int first = Integer.parseInt(lineArr[0]);
            int second = Integer.parseInt(lineArr[1]);
            if(!comesAfter.containsKey(second)){
                comesAfter.put(second, new ArrayList<Integer>());
            }
            comesAfter.get(second).add(first);
            curLine = in.nextLine();
        }

        int total = 0;

        while(in.hasNextLine()){
            String[] lineArr = in.nextLine().split(",");

            int[] print = new int[lineArr.length];
            for(int i = 0; i < print.length; i ++){
                print[i] = Integer.parseInt(lineArr[i]);
            }

            for(int i = 0; i < print.length; i ++){
                for(int j = i + 1; j < print.length; j ++){
                    if(comesAfter.get(print[i]) != null && comesAfter.get(print[i]).contains(print[j])){
                        // System.out.println(Arrays.toString(print));
                        int temp = print[i];
                        print[i] = print[j];
                        print[j] = temp;
                        total += check(print, comesAfter);
                    }
                }
            }
        }
        System.out.println(total);
    }

    private static int check(int[] print, HashMap<Integer, ArrayList<Integer>> comesAfter){
        // System.out.println(Arrays.toString(print));
        for(int i = 0; i < print.length; i ++){
            for(int j = i + 1; j < print.length; j ++){
                if(comesAfter.get(print[i]) != null && comesAfter.get(print[i]).contains(print[j])){
                    int temp = print[i];
                    print[i] = print[j];
                    print[j] = temp;
                    return check(print, comesAfter);
                }
            }
        }
        int temp =  print[print.length / 2];
        // System.out.println(temp);
        return temp;
    }
}