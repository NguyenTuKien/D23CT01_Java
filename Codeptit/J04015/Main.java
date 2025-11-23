package J04015;

import java.util.Scanner;

public class Main {
    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) {
        String id = cin.nextLine();
        String name = cin.nextLine();
        long basic = cin.nextLong();
        Salary salary = new Salary(id, name, basic);
        System.out.println(salary.toString());
    }
}
