import java.util.Scanner;

public class J02011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int tmp = a[i];
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < tmp) {
                    tmp = a[j];
                    idx = j;
                }
            }
            a[idx] = a[i];
            a[i] = tmp;
            System.out.print("Buoc " + (i + 1) + ": ");
            for (int k = 0; k < n; k++) {
                System.out.print(a[k] + " ");
            }
            System.out.println();
        }

    }
    
}
