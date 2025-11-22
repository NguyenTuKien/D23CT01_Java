package J07051;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(new File("KHACHHANG.in"));
        int n = Integer.parseInt(cin.nextLine().trim());
        ArrayList<Customer> customers = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 1; i <= n; i++) {
            String name = cin.nextLine().trim();
            String roomId = cin.nextLine().trim();
            String s1 = cin.nextLine().trim();
            String s2 = cin.nextLine().trim();
            int serviceFee = Integer.parseInt(cin.nextLine().trim());
            Date arrivalDate = sdf.parse(s1);
            Date returnDate = sdf.parse(s2);
            customers.add(new Customer(i, name, roomId, arrivalDate, returnDate, serviceFee));
        }
        Collections.sort(customers);
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
        cin.close();
    }
}
