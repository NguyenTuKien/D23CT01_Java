package Practice01.Bai02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Phim implements Comparable<Phim> {
    private String maPhim, tenPhim, maTheLoai;
    private Date ngayChieu;
    private int soTap;
    private TheLoai theLoai;

    public Phim(int id, TheLoai theLoai, String ngay, String ten, int soTap) throws ParseException {
        this.maPhim = String.format("P%03d", id);
        this.theLoai = theLoai;
        this.tenPhim = ten;
        this.soTap = soTap;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.ngayChieu = sdf.parse(ngay);
    }
    @Override
    public int compareTo(Phim o){
        int cmp = this.ngayChieu.compareTo(o.ngayChieu);
        if(cmp != 0) return cmp;
        cmp = this.tenPhim.compareToIgnoreCase(o.tenPhim);
        if(cmp != 0) return cmp;
        return o.soTap - this.soTap;
    }
    @Override
    public String toString(){
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return maPhim + " " + theLoai.getTenTL()+ " " + f.format(ngayChieu) + " " + tenPhim + " " + soTap;
    }
}
