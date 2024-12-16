import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.HashMap;

public class PartOne{
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

                    int[] newCoord1 = new int[] {coord1[0] + dR, coord1[1] + dC};
                    int[] newCoord2 = new int[] {coord2[0] - dR, coord2[1] - dC};

                    if(newCoord1[0] >= 0 && newCoord1[0] < map.length && newCoord1[1]  >= 0 && newCoord1[1]  < map.length){
                        if(map[newCoord1[0]][newCoord1[1]] != '#'){
                            map[newCoord1[0]][newCoord1[1]] = '#';
                            total ++;
                        }
                    }
                    if(newCoord2[0] >= 0 && newCoord2[0] < map.length && newCoord2[1]  >= 0 && newCoord2[1]  < map.length){
                        if(map[newCoord2[0] ][newCoord2[1] ] != '#'){
                            map[newCoord2[0] ][newCoord2[1] ] = '#';
                            total ++;
                        }
                    }
                }
            }
            
        }
        System.out.println(total);

    }
}