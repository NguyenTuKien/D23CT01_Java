import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class J03014 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger A = new BigInteger(br.readLine());
        BigInteger B = new BigInteger(br.readLine());
        System.out.println(A.subtract(B));
    }
}
