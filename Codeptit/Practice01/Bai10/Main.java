package Practice01.Bai10;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner scMon = new Scanner(new File("MONTHI.in"));
        Map<String, String> monHocMap = new HashMap<>();
        int nMon = Integer.parseInt(scMon.nextLine());
        for (int i = 0; i < nMon; i++) {
            String maMon = scMon.nextLine();
            String tenMon = scMon.nextLine();
            String hinhThuc = scMon.nextLine(); 
            monHocMap.put(maMon, tenMon);
        }
        scMon.close();

        Scanner scCa = new Scanner(new File("CATHI.in"));
        Map<String, CaThi> caThiMap = new HashMap<>();
        int nCa = Integer.parseInt(scCa.nextLine());
        for (int i = 1; i <= nCa; i++) {
            // Tự động gán mã ca thi
            String maCa = String.format("C%03d", i);
            String ngay = scCa.nextLine();
            String gio = scCa.nextLine();
            String phong = scCa.nextLine();
            
            caThiMap.put(maCa, new CaThi(maCa, ngay, gio, phong));
        }
        scCa.close();

        Scanner scLich = new Scanner(new File("LICHTHI.in"));
        List<LichThi> danhSachLichThi = new ArrayList<>();
        int nLich = Integer.parseInt(scLich.nextLine());
        
        for (int i = 0; i < nLich; i++) {
            String[] parts = scLich.nextLine().split(" ");
            
            String maCa = parts[0];
            String maMon = parts[1];
            String nhom = parts[2];
            int soSV = Integer.parseInt(parts[3]);
            
            CaThi ca = caThiMap.get(maCa);
            String tenMon = monHocMap.get(maMon);
            
            danhSachLichThi.add(new LichThi(ca, tenMon, nhom, soSV));
        }
        scLich.close();

        Collections.sort(danhSachLichThi);

        for (LichThi lich : danhSachLichThi) {
            System.out.println(lich);
        }
    }
}