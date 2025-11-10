import java.util.Scanner;

public class J03038 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String s = cin.next();
        boolean[] flag = new boolean[26];
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if(!flag[c - 'a']) {
                ++cnt;
                flag[c - 'a'] = true;
            }
        }
        System.out.println(cnt);
    }
}
