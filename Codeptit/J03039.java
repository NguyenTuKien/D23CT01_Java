import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class J03039 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            String line = br.readLine();
            BigInteger a = new BigInteger(line.split(" ")[0]);
            BigInteger b = new BigInteger(line.split(" ")[1]);
            if(a.mod(b).equals(BigInteger.ZERO)) System.out.println("YES");
            else if(b.mod(a).equals(BigInteger.ZERO)) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
