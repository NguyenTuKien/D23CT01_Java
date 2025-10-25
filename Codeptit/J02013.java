import java.util.Scanner;

public class J02013 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        boolean order = true;
        int count = 1;
        do {
            order = true;
            for (int i = 0; i < n - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    order = false;
                }
            }
            if (!order) {
                System.out.print("Buoc " + count++ + ": ");
                for (int i = 0; i < n; i++) {
                    System.out.print(a[i] + " ");
                }
                System.out.println();
            }
        } 
        while (!order);

    }
    
}
