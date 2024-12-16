import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;
import java.io.IOException;

public class PartTwo{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bigData.txt"));

        ArrayList<char[]> map = new ArrayList<char[]>();
        while(in.hasNextLine()){
            map.add(in.nextLine().toCharArray());
        }

        int curR = 0;
        int curC = 0;

        int startR = 0;
        int startC = 0;

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
        startR = curR;
        startC = curC;
        Path p = new Path();
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int dirInd = 0;
        while(curC >= 0 && curC < map.get(0).length && curR >= 0 && curR < map.size()){
            p.path.add(new Spot(curR, curC, dirInd));
            if(map.get(curR)[curC] != 'X'){
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

        Path works = new Path();

        for(Spot s : p.path){
            if(s.r == startR && s.c == startC){
                continue;
            }
            char prev = map.get(s.r)[s.c];
            map.get(s.r)[s.c] = '#';
            // System.out.println(s.r + " " + s.c + " " + s.dir);
            dirInd = 0;
            curC = startC;
            curR = startR;
            Path curPath = new Path();
            while(curC >= 0 && curC < map.get(0).length && curR >= 0 && curR < map.size()){
                if(!curPath.path.add(new Spot(curR, curC, dirInd))){
                    // System.out.println(s.r + " " + s.c + " " + s.dir);
                    works.path.add(new Spot(s.r, s.c, 0));
                    break;
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
            map.get(s.r)[s.c] =     prev;
        }

        System.out.println(works.path.size());

    }

    static class Path{
        private TreeSet<Spot> path;
        
        public Path(){
            path = new TreeSet<Spot>();
        }

    }

    static class Spot implements Comparable<Spot>{
        private int r;
        private int c;
        private int dir;

        public Spot(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public int compareTo(Spot o){
            if(r == o.r && c == o.c && dir == o.dir){
                return 0;
            }
            if(r > o.r){
                return 1;
            }
            else {
               return -1;
            }
        }

    }
}

