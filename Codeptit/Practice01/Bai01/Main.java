package Practice01.Bai01;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
//        Scanner cin = new Scanner(new File("MATRIX.in"));
        Scanner cin = new Scanner(System.in);
        int t = cin.nextInt();
        for (int tc = 0; tc < t; tc++) {
            int n = cin.nextInt();
            int m = cin.nextInt();
            int colIndex = cin.nextInt(); // 1-based
            int[][] data = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    data[i][j] = cin.nextInt();
                }
            }

            Matrix mat = new Matrix(n, m, data);
            mat.sortColumn(colIndex);

            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    if (j > 0) sb.append(" ");
                    sb.append(mat.getElement(i, j));
                }
                System.out.println(sb.toString());
            }
        }
        cin.close();
    }
}
