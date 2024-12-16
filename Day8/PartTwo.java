import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.HashMap;

public class PartTwo{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bigData.txt"));

        char[][] map = new char[50][];
        HashMap<Character, ArrayList<int[]>> towerPos = new HashMap<Character, ArrayList<int[]>>();

        for(int i = 0; i < map.length; i ++){
            map[i] = in.nextLine().toCharArray();
            for(int j = 0; j < map[i].length; j ++){
                if(map[i][j] != '.'){
                    if(!towerPos.containsKey(map[i][j])){
                        towerPos.put(map[i][j], new ArrayList<>());
                    }
                    towerPos.get(map[i][j]).add(new int[] {i, j});
                }
            }
        }

        int total = 0;

        for(char freq : towerPos.keySet()){
            ArrayList<int[]> towers = towerPos.get(freq);

            for(int i = 0; i < towers.size(); i ++){
                for(int j = i + 1; j < towers.size(); j ++){
                    int[] coord1 = towers.get(i);
                    int[] coord2 = towers.get(j);
                    int dR = coord1[0] - coord2[0];
                    int dC = coord1[1] - coord2[1];
                    
                    int gcf = gcf(Math.abs(dR), Math.abs(dC));
                    dR = dR / gcf;
                    dC = dC / gcf;
                    
                    int curR = coord1[0];
                    int curC = coord1[1];

                    while(curR >= 0 && curR < map.length && curC >= 0 && curC < map[0].length) {
                        curR -= dR;
                        curC -= dC;
                    }

                    curR += dR;
                    curC += dC;

                    while(curR < map.length && curR >= 0 && curC < map[0].length && curC >= 0){
                        if(map[curR][curC] != '#'){
                            total ++;
                            map[curR][curC] = '#';
                        }
                        curR += dR;
                        curC += dC;
                    }

                }
            }
            
        }
        System.out.println(total);

    }

    private static int gcf(int a, int b){
        if (a == 0)
            return b;

        return gcf(b % a, a);
    }
}