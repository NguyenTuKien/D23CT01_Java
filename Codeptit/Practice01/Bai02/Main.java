package Practice01.Bai02;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        ArrayList<TheLoai> dsTheLoai = new ArrayList<>();

        for(int i=0;i<n;i++){
            dsTheLoai.add(new TheLoai(i + 1, sc.nextLine()));
        }
        ArrayList<Phim> dsPhim = new ArrayList<>();
        for(int i=0;i<m;i++){
            String maTheLoai = sc.nextLine();
            String ngay = sc.nextLine();
            String ten = sc.nextLine();
            int soTap = Integer.parseInt(sc.nextLine());
            TheLoai tl = null;
            for(TheLoai t : dsTheLoai) {
                if(t.getMa().equals(maTheLoai)){
                    tl = t;
                    break;
                }
            }
            dsPhim.add(new Phim(i + 1, tl, ngay, ten, soTap));
        }
        Collections.sort(dsPhim);
        for(Phim p : dsPhim){
            System.out.println(p);
        }
    }
}
