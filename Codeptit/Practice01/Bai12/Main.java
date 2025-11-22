package Practice01.Bai12;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Map<String, String> tenSVMap = new HashMap<>();
        Map<String, String> sdtMap = new HashMap<>();

        Scanner scDS = new Scanner(new File("DANHSACH.in"));
        
        // Đọc đến cuối file
        while (scDS.hasNextLine()) {
            String maSV = scDS.nextLine();
            String tenSV = scDS.nextLine();
            String lop = scDS.nextLine();   
            String email = scDS.nextLine(); 
            String sdt = scDS.nextLine();
            
            tenSVMap.put(maSV, tenSV);
            sdtMap.put(maSV, sdt);
        }
        scDS.close();

        List<DoAn> danhSachDoAn = new ArrayList<>();
        
        Scanner scHD = new Scanner(new File("HUONGDAN.in"));
        
        int soGV = Integer.parseInt(scHD.nextLine());
        
        for (int i = 0; i < soGV; i++) {
            // Đọc dòng thông tin giảng viên
            String gvLine = scHD.nextLine();
            
            // Tách tên GV và số lượng đồ án
            int lastSpaceIndex = gvLine.lastIndexOf(' ');
            String tenGV = gvLine.substring(0, lastSpaceIndex);
            int soDoAn = Integer.parseInt(gvLine.substring(lastSpaceIndex + 1));
            
            // Đọc N đồ án của giảng viên này
            for (int j = 0; j < soDoAn; j++) {
                String doAnLine = scHD.nextLine();
                
                int firstSpaceIndex = doAnLine.indexOf(' ');
                String maSV = doAnLine.substring(0, firstSpaceIndex);
                String tenDoAn = doAnLine.substring(firstSpaceIndex + 1);
                

                String tenSV = tenSVMap.get(maSV);
                String sdt = sdtMap.get(maSV);
                
                danhSachDoAn.add(new DoAn(maSV, tenSV, tenGV, tenDoAn, sdt));
            }
        }
        scHD.close();

        Collections.sort(danhSachDoAn);
        
        for (DoAn da : danhSachDoAn) {
            System.out.println(da);
        }
    }
}