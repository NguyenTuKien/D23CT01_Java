package J04008;

import java.util.Scanner;

public class Main {
    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            Point p1 = new Point(cin.nextDouble(), cin.nextDouble());
            Point p2 = new Point(cin.nextDouble(), cin.nextDouble());
            Point p3 = new Point(cin.nextDouble(), cin.nextDouble());
            Triangle triangle = new Triangle(p1, p2, p3);
            if (triangle.isTriangle()) {
                System.out.println(String.format("%.03f",triangle.getPerimeter()));
            } else {
                System.out.println("INVALID");
            }
        }
    }
}
