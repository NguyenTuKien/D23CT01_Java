// java
package Practice01.Bai01;

import java.util.Arrays;

public class Matrix {
    private final int n;
    private final int m;
    private final int[][] data;

    public Matrix(int n, int m, int[][] data) {
        this.n = n;
        this.m = m;
        this.data = data;
    }

    public void setElement(int row, int col, int value) {
        this.data[row][col] = value;
    }

    public int getElement(int row, int col) {
        return this.data[row][col];
    }

    // Sorts column given as 1-based index in ascending order
    public void sortColumn(int col1Based) {
        int c = col1Based - 1;
        int[] col = new int[n];
        for (int i = 0; i < n; i++) {
            col[i] = data[i][c];
        }
        Arrays.sort(col);
        for (int i = 0; i < n; i++) {
            data[i][c] = col[i];
        }
    }
}
