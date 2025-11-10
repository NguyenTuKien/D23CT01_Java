import java.util.ArrayList;
import java.util.Scanner;

public class J03032 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int t = cin.nextInt();
        cin.nextLine();
        while(t-- > 0){
            String line = cin.nextLine();
            ArrayList<StringBuilder> ans = new ArrayList<>();
            for (String s : line.split(" ")){
                StringBuilder sb = new StringBuilder(s);
                sb.reverse();
                ans.add(sb);
            }
            for (StringBuilder s : ans){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
