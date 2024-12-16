import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

public class PartOne{
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("bigData.txt"));
        Pattern p = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        int total = 0;

        while(in.hasNextLine()){
            Matcher m = p.matcher(in.nextLine());
            while(m.find()){
                String cur = m.group();
                Pattern dig = Pattern.compile("[0-9]+");
                Matcher digM = dig.matcher(cur);
                digM.find();
                int num1 = Integer.parseInt(digM.group());
                digM.find();
                int num2 = Integer.parseInt(digM.group());
                total += num1 * num2;
            }
        }
        System.out.println(total);
    }
}