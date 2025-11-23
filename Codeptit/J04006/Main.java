package J04006;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String clss = sc.nextLine();
        String dob = sc.nextLine();
        float gpa = sc.nextFloat();
        System.out.println((new Student(name, clss, dob, gpa)).toString());
    }
}
