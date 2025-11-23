package J04003;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        long a = cin.nextLong();
        long b = cin.nextLong();
        System.out.println((new Fraction(a, b)).toString());
    }
}
