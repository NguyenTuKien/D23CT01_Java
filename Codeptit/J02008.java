import java.util.Scanner;

public class J02008 {
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
    public static void main(String[] args) {
        long a[] = new long[101];
        a[1] = 1;
        for (int i = 2; i <= 100; i++) {
            a[i] = lcm(a[i - 1], i);
        }
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            System.out.println(a[n]);
        }
        sc.close();
    }
    
}
