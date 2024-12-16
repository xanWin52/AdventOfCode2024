import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class PartTwoGPT {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("smallData.txt"));

        ArrayList<char[]> map = new ArrayList<>();
        while (in.hasNextLine()) {
            map.add(in.nextLine().toCharArray());
        }

        int curR = 0, curC = 0, startR = 0, startC = 0;

        // Find the guard's starting position
        outerloop:
        for (int r = 0; r < map.size(); r++) {
            for (int c = 0; c < map.get(r).length; c++) {
                if (map.get(r)[c] == '^') {
                    curR = startR = r;
                    curC = startC = c;
                    break outerloop;
                }
            }
        }

        // Guard directions: Up, Right, Down, Left
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int dirInd = 0;

        // Simulate the guard's initial path
        Path originalPath = simulatePath(map, curR, curC, dir, dirInd);

        Path validObstructions = new Path();
        for (Spot s : originalPath.path) {
            if (s.r == startR && s.c == startC) continue; // Can't place obstruction at starting position

            // Temporarily place obstruction
            char original = map.get(s.r)[s.c];
            map.get(s.r)[s.c] = '#';

            // Simulate guard's path with the obstruction
            Path newPath = simulatePath(map, startR, startC, dir, 0);

            // Check if the new path causes a loop
            if (isLoop(newPath)) {
                validObstructions.path.add(s);
            }

            // Restore map
            map.get(s.r)[s.c] = original;
        }

        System.out.println(validObstructions.path.size());
    }

    static Path simulatePath(ArrayList<char[]> map, int startR, int startC, int[][] dir, int dirInd) {
        Path path = new Path();
        int curR = startR, curC = startC;

        while (curC >= 0 && curC < map.get(0).length && curR >= 0 && curR < map.size()) {
            if (!path.path.add(new Spot(curR, curC, dirInd))) break; // Revisit detected

            int newR = curR + dir[dirInd][0];
            int newC = curC + dir[dirInd][1];

            // Turn if blocked
            if (newC < 0 || newC >= map.get(0).length || newR < 0 || newR >= map.size() || map.get(newR)[newC] == '#') {
                dirInd = (dirInd + 1) % 4;
                newR = curR + dir[dirInd][0];
                newC = curC + dir[dirInd][1];
            }

            curR = newR;
            curC = newC;
        }
        return path;
    }

    static boolean isLoop(Path path) {
        return path.path.size() > 1 && !path.path.containsAll(path.path); // Simple loop check
    }

    static class Path {
        Set<Spot> path = new HashSet<>();
    }

    static class Spot {
        int r, c, dir;

        Spot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Spot spot = (Spot) o;
            return r == spot.r && c == spot.c && dir == spot.dir;
        }
    }
}
