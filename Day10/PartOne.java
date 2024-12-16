import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.IOException;
import java.io.File;

public class PartOne{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bigData.txt"));
        ArrayList<char[]> lines = new ArrayList<char[]>();

        while(in.hasNextLine()){
            lines.add(in.nextLine().toCharArray());
        }

        char[][] map = new char[lines.size()][];
        for(int i = 0; i < map.length; i ++){
            map[i] = lines.get(i);
        }

        int res = 0;
        for(int r = 0; r < map.length; r ++){
            for(int c = 0; c < map[0].length; c++){
                if(map[r][c] == '0'){
                    Set<Coordinate> found = new TreeSet<>();
                    res += findTrails(map, r, c, '0', found);
                }
            }
        }
        System.out.println(res);
    }

    private static int findTrails(char[][] map, int r, int c, char curLevel, Set<Coordinate> found){
        Coordinate cur = new Coordinate(r, c);
        if(curLevel == '9' && !found.contains(cur)){
            found.add(cur);
            return 1;
        }

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int res = 0;

        for(int[] dir : dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];

            if(inBounds(newR, newC, map) && map[newR][newC] == curLevel + 1){
                res += findTrails(map, newR, newC, map[newR][newC], found);
            }
        }
        return res;
    }

    private static boolean inBounds(int r, int c, char[][] map){
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }

    static class Coordinate implements Comparable<Coordinate>{
        private int r;
        private int c;

        public  Coordinate(int r, int c){
            this.r = r;
            this.c = c;
        }

        public int compareTo(Coordinate o){
            if(this.r != o.r){
                return this.r - o.r;
            } else {
                return this.c - o.c;
            }
        }

    }
}