package Practice01.Bai09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class    Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc1 = new Scanner(new File("SINHVIEN.in"));
        int n = Integer.parseInt(sc1.nextLine());
        Map<String, SinhVien> mapSV = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String ma = sc1.nextLine();
            String ten = sc1.nextLine();
            String lop = sc1.nextLine();
            String email = sc1.nextLine();
            String sdt = sc1.nextLine();
            String gt = sc1.nextLine();
            mapSV.put(ma, new SinhVien(ma, ten, lop, email, sdt, gt));
        }
        Scanner sc2 = new Scanner(new File("DANGKY.in"));
        Map<String, String> mapDK = new HashMap<>();
        while (sc2.hasNextLine()) {
            String[] a = sc2.nextLine().trim().split("\\s+");
            if(a.length == 2) {
                mapDK.put(a[0], a[1]);
            }
        }
        Scanner sc3 = new Scanner(new File("TRUYVAN.in"));
        int q = Integer.parseInt(sc3.nextLine());
        while (q-- > 0) {
            String[] t = sc3.nextLine().trim().split("\\s+");
            String gioiTinh  = t[0];
            String size = t[1];
            System.out.println("DANH SACH SINH VIEN " + gioiTinh.toUpperCase() + " DANG KY SIZE " + size.toUpperCase());
            ArrayList<SinhVien> ds = new ArrayList<>();
            for(String ma : mapDK.keySet()) {
                SinhVien sv = mapSV.get(ma);
                if(sv != null && sv.getGt().equalsIgnoreCase(gioiTinh) && mapDK.get(ma).equalsIgnoreCase(size)) {
                    ds.add(sv);
                }
            }
            Collections.sort(ds);
            for(SinhVien sv : ds) {
                System.out.println(sv);
            }
        }
    }
}
