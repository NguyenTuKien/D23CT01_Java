import java.util.Scanner;

public class J03025 {
    public static String kiemTra(String s) {
        int n = s.length();
        int soSaiKhac = 0;
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                soSaiKhac++;
            }
        }
        if (soSaiKhac > 1) {
            return "NO";
        }
        if (soSaiKhac == 1) {
            return "YES";
        }
        if (n % 2 == 1) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int t = Integer.parseInt(cin.nextLine());
        while (t-- > 0) {
            String s = cin.nextLine();
            System.out.println(kiemTra(s));
        }
    }
}