package model;

public class SinhVien {
    private int maSV;
    private String hoTen;
    private String sdt;
    private String lop;

    public SinhVien(int maSV, String hoTen, String sdt, String lop) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.lop = lop;
    }

    public int getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public String getSdt() { return sdt; }
    public String getLop() { return lop; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public void setLop(String lop) { this.lop = lop; }

    @Override
    public String toString() {
        return maSV + " - " + hoTen;
    }
}