import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("bigData.txt"));

        ArrayList<char[]> map = new ArrayList<char[]>();

        while(in.hasNextLine()){
            map.add(in.nextLine().toCharArray());
        }

        int total = 0;

        for(int r = 0; r < map.size(); r ++){
            for(int c = 0; c < map.get(0).length; c ++){
                if(map.get(r)[c] == 'A' && check(map, r, c)){
                    total ++;
                }
            }
        }
        System.out.println(total);
    }

    private static boolean check(ArrayList<char[]> map, int r, int c){
        int[][] dirs = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for(int[] dir : dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(newR < 0 || newR >= map.size() || newC < 0 || newC >= map.get(0).length){
                return false;
            }
            char curChar = map.get(newR)[newC];
            if(curChar != 'M' && curChar != 'S'){
                return false;
            }
        }

        return (map.get(r - 1)[c - 1] != map.get(r + 1)[c + 1] && map.get(r + 1)[c - 1] != map.get(r - 1)[c + 1]);

    }
}
