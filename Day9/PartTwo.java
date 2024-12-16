import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class PartTwo{

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("bigData.txt"));
        String[] line = in.nextLine().split("");

        ArrayList<Chunk> memory = new ArrayList<Chunk>();
        int ID = 0;

        for(int i = 0; i < line.length; i ++){
            int curNum = Integer.parseInt(line[i]);
            if(i % 2 == 0){
                memory.add(new Chunk(ID++, curNum));
            } else {
                memory.add(new Chunk(-1, curNum));
            }
        }

        for(int i = memory.size() - 1; i >= 0; i --){
            Chunk curChunk = memory.get(i);
            if(curChunk.ID != -1){
                for(int j = 0; j < i; j ++){
                    Chunk posSwap = memory.get(j);
                    if(posSwap.ID == -1 && posSwap.length >= curChunk.length){
                        int remainingLength = posSwap.length - curChunk.length;
                        memory.remove(j);
                        memory.add(j, new Chunk(curChunk.ID, curChunk.length));
                        if(remainingLength > 0 ){
                            memory.add(j + 1, new Chunk(-1, remainingLength));
                            i ++;
                        }
                        curChunk.ID = -1;
                        break;
                    }
                }
            }
        }

        int pos = 0;
        long result = 0;
        for(Chunk c : memory){
            for(int i = 0; i < c.length; i ++){
                result += (pos++) * (c.ID == -1 ? 0 : c.ID);
            }
        }
        System.out.println(result);
        
    }

    static class Chunk{
        private int ID;
        private int length;

        public Chunk(int ID, int length){
            this.ID = ID;
            this.length = length;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < length; i ++){
                sb.append(ID == -1 ? "." : ID);
            }
            return sb.toString();
        }
    }
}