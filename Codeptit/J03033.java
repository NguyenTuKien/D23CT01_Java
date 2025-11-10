import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class J03033 {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if(b.equals(BigInteger.ZERO)) return a;
        else return gcd(b, a.mod(b));
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(gcd(a, b)).multiply(b);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            BigInteger a = new BigInteger(br.readLine());
            BigInteger b = new BigInteger(br.readLine());
            System.out.println(lcm(a, b));
        }
    }
}
