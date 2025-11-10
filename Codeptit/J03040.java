import java.io.BufferedReader;
import java.io.InputStreamReader;

public class J03040 {

    public static boolean check1(String number){
        char d1 = number.charAt(0);
        char d2 = number.charAt(1);
        char d3 = number.charAt(2);
        char d4 = number.charAt(4);
        char d5 = number.charAt(5);
        if (d1 >= d2) return false;
        if (d2 >= d3) return false;
        if (d3 >= d4) return false;
        if (d4 >= d5) return false;
        return true;
    }

    public static boolean check2(String number){
        String[] parts = number.split("\\.");
        if (parts.length < 2) return false;
        String begin = parts[0];
        String end = parts[1];
        for (int i = 1; i < begin.length(); ++i){
            if(begin.charAt(i) != begin.charAt(0)) return false;
        }
        for (int i = 1; i < end.length(); ++i){
            if(end.charAt(i) != end.charAt(0)) return false;
        }
        return true;
    }

    public static boolean check3(String number){
        for (char c : number.toCharArray()){
            if(c == '.') continue;
            else if(c != '6' && c != '8') return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(cin.readLine());
        while(t-- > 0){
            String number = cin.readLine().split("-")[1];
            if(check1(number) || check2(number) || check3(number)) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}