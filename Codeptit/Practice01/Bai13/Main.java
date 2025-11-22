package Practice01.Bai13;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader brMon = new BufferedReader(new FileReader("MONHOC.in"))) {
            int soMon = Integer.parseInt(brMon.readLine().trim());
            for (int i = 0; i < soMon; i++) {
                brMon.readLine();
            }
        }

        List<GiangVien> danhSachGV = new ArrayList<>();
        Map<String, GiangVien> mapGV = new HashMap<>();

        try (BufferedReader brGV = new BufferedReader(new FileReader("GIANGVIEN.in"))) {
            int soGV = Integer.parseInt(brGV.readLine().trim());
            for (int i = 0; i < soGV; i++) {
                String line = brGV.readLine().trim();
                String[] parts = line.split("\\s+", 2);
                GiangVien gv = new GiangVien(parts[0], parts[1]);
                danhSachGV.add(gv);
                mapGV.put(parts[0], gv);
            }
        }

        try (BufferedReader brGio = new BufferedReader(new FileReader("GIOCHUAN.in"))) {
            int soLop = Integer.parseInt(brGio.readLine().trim());
            for (int i = 0; i < soLop; i++) {
                String[] parts = brGio.readLine().trim().split("\\s+");
                String maGV = parts[0];
                double gio = Double.parseDouble(parts[2]);
                GiangVien gv = mapGV.get(maGV);
                if (gv != null) {
                    gv.tongGio += gio;
                }
            }
        }

        for (GiangVien gv : danhSachGV) {
            System.out.printf("%s %.2f%n", gv.ten, gv.tongGio);
        }
    }
}
