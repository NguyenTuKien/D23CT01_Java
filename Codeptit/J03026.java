import java.util.Scanner;

public class J03026 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int t = cin.nextInt();
        while(t-- > 0){
            String a = cin.next();
            String b = cin.next();
            if(a.equals(b)) System.out.println(-1);
            else {
                System.out.println(Math.max(a.length(), b.length()));
            }
        }
    }
}
