import java.util.Scanner;
import java.lang.Math;

public class J02022 {
    static int count = 0;
    static int n, tmp[], mark[];
    static public boolean check(){
        for (int i = 1; i < n; ++i){
           if(Math.abs(tmp[i] - tmp[i + 1]) <= 1) return false;
        }
        return true;
    }

    static public void backtrack(int i, int n) {
        if(i > n){
            if(check()){
                for (int j = 1; j <= n; ++j) {
                    System.out.print(tmp[j]);
                }
                System.out.println();
            }
        }
        for (int j = 1; j <= n; j++) {
            if(mark[j] == 0){
                mark[j] = 1;
                tmp[i] = j;
                backtrack(i + 1, n);
                mark[j] = 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            n = sc.nextInt();
            tmp = new int[n + 1];
            mark = new int[n + 1];
            backtrack(1, n);
        }
    }
}