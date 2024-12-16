import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class PartOne{
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("bigData.txt"));

        ArrayList<char[]> map = new ArrayList<char[]>();

        while(in.hasNextLine()){
            map.add(in.nextLine().toCharArray());
        }

        int total = 0;

        for(int r = 0; r < map.size(); r ++){
            for(int c = 0; c < map.get(0).length; c ++){
                if(map.get(r)[c] == 'X'){
                    total += check(map, r, c);
                }
            }
        }
        System.out.println(total);
    }

    private static int check(ArrayList<char[]> map, int r, int c){
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        String XMAS = "XMAS";
        int total = 0;
        outerloop:
        for(int[] dir : dirs){
            int indOfXMAS = 1;
            int newR = r;
            int newC = c;
            for(int i = 0; i < 3; i ++){
                newR += dir[0];
                newC += dir[1];
                if(!(newR >=0 && newR < map.size() && newC >= 0 && newC < map.get(0).length) || map.get(newR)[newC] != XMAS.charAt(indOfXMAS)){
                    continue outerloop;
                }
                indOfXMAS ++;
            }
            total ++;
        }
        return total;
    }

}