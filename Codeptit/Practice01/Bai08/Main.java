package src.core.ThucHanh2.BaiG;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("MUAHANG.in"));

        int n = Integer.parseInt(sc.nextLine().trim());  // số sản phẩm
        Map<String, SanPham> products = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String code = sc.nextLine().trim();
            String name = sc.nextLine().trim();
            int price = Integer.parseInt(sc.nextLine().trim());
            int warranty = Integer.parseInt(sc.nextLine().trim());
            products.put(code, new SanPham(code, name, price, warranty));
        }

        int m = Integer.parseInt(sc.nextLine().trim());  // số khách hàng
        List<Purchase> list = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            String cusName = sc.nextLine().trim();
            String addr = sc.nextLine().trim();
            String pCode = sc.nextLine().trim();
            int qty = Integer.parseInt(sc.nextLine().trim());
            String buyDate = sc.nextLine().trim();

            list.add(new Purchase(i, cusName, addr, pCode, qty, buyDate, products));
        }

        Collections.sort(list);
        for (Purchase x : list) System.out.println(x);
    }
}