package J04004;

import java.util.Scanner;

public class Main {
    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) {
        Fraction f1 = new Fraction(cin.nextLong(), cin.nextLong());
        Fraction f2 = new Fraction(cin.nextLong(), cin.nextLong());
        System.out.println(f1.add(f2).toString());
    }
}
