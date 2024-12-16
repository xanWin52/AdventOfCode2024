import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class PartOne{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bigData.txt"));

        ArrayList<char[]> map = new ArrayList<char[]>();
        while(in.hasNextLine()){
            map.add(in.nextLine().toCharArray());
        }

        int curR = 0;
        int curC = 0;

        outerloop:
        for(int r = 0; r < map.size(); r ++){
            char[] row = map.get(r);
            for(int c = 0; c < row.length; c ++){
                if(row[c] == '^'){
                    curR = r;
                    curC = c;
                    break outerloop;
                }
            }
        }

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int dirInd = 0;
        int total = 0;
        while(curC >= 0 && curC < map.get(0).length && curR >= 0 && curR < map.size()){
            if(map.get(curR)[curC] != 'X'){
                total ++;
                map.get(curR)[curC] = 'X';
            }
            int newR = curR + dir[dirInd][0];
            int newC = curC + dir[dirInd][1];
            if((newC >= 0 && newC < map.get(0).length && newR >= 0 && newR < map.size()) && map.get(newR)[newC] == '#'){
                dirInd ++;
                dirInd = dirInd % 4;
                newR = curR + dir[dirInd][0];
                newC = curC + dir[dirInd][1];
            }
            curC = newC;
            curR = newR;
        }

        System.out.println(total);

    }
}