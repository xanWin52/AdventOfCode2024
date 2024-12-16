import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class PartOne{
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

        outerloop:
        while(in.hasNextLine()){
            String[] lineArr = in.nextLine().split(",");

            int[] print = new int[lineArr.length];
            for(int i = 0; i < print.length; i ++){
                print[i] = Integer.parseInt(lineArr[i]);
            }

            for(int i = 0; i < print.length; i ++){
                ArrayList<Integer> curComesAfter = comesAfter.get(print[i]);
                if(curComesAfter != null){
                    for(int j = i + 1; j < print.length; j ++){
                        if(curComesAfter.contains(print[j])){
                            continue outerloop;
                        }
                    }
                }
            }
            total += print[print.length/2];

        }
        System.out.println(total);
    }
}