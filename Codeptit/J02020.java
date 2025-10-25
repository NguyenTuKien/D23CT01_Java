import java.util.Scanner;

class J02020 {
    static int count = 0;
    static int n, k;
    static int a[];
    static void backtrack(int i, int n) {
        if(i > k){
            count++;
            for(int j = 1; j <= k; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();
            return;
        }
        for (int j = a[i - 1] + 1; j <= n - k + i; j++) {
            a[i] = j;
            backtrack(i + 1, n);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();   
        a = new int[n];
        backtrack(1, n);
        System.out.printf("Tong cong co %d to hop", count);
    }
}