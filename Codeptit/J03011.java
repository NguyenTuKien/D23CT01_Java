import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.Buffer;
import java.util.Scanner;

public class J03011 {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if(a.equals(BigInteger.ZERO)) return b;
        else return gcd(b.mod(a), a);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(cin.readLine());
        while (t-- > 0) {
            BigInteger num1 = new BigInteger(cin.readLine());
            BigInteger num2 = new BigInteger(cin.readLine());
            System.out.println(gcd(num1, num2));
        }
    }
}
