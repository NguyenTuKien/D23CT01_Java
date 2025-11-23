package J04007;

import java.util.Scanner;

public class Main {
    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String name = cin.nextLine();
        String sex =  cin.nextLine();
        String dob = cin.nextLine();
        String address = cin.nextLine();
        String taxId = cin.nextLine();
        String hireDate = cin.nextLine();
        Staff staff = new Staff(name, sex, dob, address, taxId, hireDate);
        System.out.println(staff.toString());
    }
}
