package Practice01.Bai09;

public class SinhVien implements Comparable<SinhVien> {
    private String ma, ten, lop, email, sdt, gt;
    public SinhVien(String ma, String ten, String lop, String email, String sdt, String gt) {
        this.ma = ma;
        this.ten = ten;
        this.lop = lop;
        this.email = email;
        this.sdt = sdt;
        this.gt = gt;
    }
    public String getMa() {
        return ma;
    }
    public String getGt() {
        return gt;
    }
    @Override
    public int compareTo(SinhVien o) {
        return this.ma.compareTo(o.ma);
    }
    @Override
    public String toString() {
        return ma + " " + ten + " " + lop + " " + email + " " + sdt;
    }
}
